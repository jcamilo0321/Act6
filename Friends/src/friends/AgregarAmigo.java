package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class AgregarAmigo {

    public static void agregarAmigo(String newName, long newNumber) {
        try {
            File file = new File("D:\\Users\\User\\Documents\\NetBeansProjects\\friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Verificar si el nombre o el número ya existe
            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            // Si no se encontró el amigo, agregarlo
            if (!found) {
                String nameNumberString = newName + "!" + String.valueOf(newNumber);
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                
                // Cerrar el archivo después de escribir
                raf.close();
                
                // Mostrar un cuadro de diálogo de éxito
                mostrarDialogo("Amigo agregado exitosamente.");
            } else {
                // Mostrar un cuadro de diálogo de error si el amigo ya existe
                mostrarDialogo("El nombre o número ya existe.");
                
                // Cerrar el archivo en caso de error
                raf.close();
            }
        } catch (IOException ioe) {
            // Mostrar un cuadro de diálogo de error si hay una excepción de IO
            mostrarDialogo("Error al agregar amigo: " + ioe.getMessage());
        } catch (NumberFormatException nef) {
            // Mostrar un cuadro de diálogo de error si hay una excepción de formato numérico
            mostrarDialogo("Error al agregar amigo: " + nef.getMessage());
        }
    }

    private static void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
