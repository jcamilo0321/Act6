package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class ActualizarAmigo {

    public static void actualizarAmigo(String oldName, String newName, long newNumber) {
        try {
            File file = new File("D:\\Users\\User\\Documents\\NetBeansProjects\\friendsContact.txt");

            if (!file.exists()) {
                mostrarDialogo("El archivo no existe.");
                return;
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                // Verificar si el nombre antiguo coincide
                if (name.equals(oldName)) {
                    found = true;

                    // Actualizar el registro
                    raf.seek(raf.getFilePointer() - nameNumberString.length() - 2); // Retroceder al inicio del registro
                    String updatedRecord = newName + "!" + String.valueOf(newNumber);
                    raf.writeBytes(updatedRecord);
                    raf.writeBytes(System.lineSeparator());

                    // Mostrar un cuadro de diálogo de éxito
                    mostrarDialogo("Amigo actualizado exitosamente.");
                    break;
                }
            }

            if (!found) {
                // Mostrar un cuadro de diálogo si el amigo no fue encontrado
                mostrarDialogo("Amigo no encontrado para actualizar.");
            }

            raf.close();
        } catch (IOException ioe) {
            // Mostrar un cuadro de diálogo de error si hay una excepción de IO
            mostrarDialogo("Error al actualizar amigo: " + ioe.getMessage());
        } catch (NumberFormatException nef) {
            // Mostrar un cuadro de diálogo de error si hay una excepción de formato numérico
            mostrarDialogo("Error al actualizar amigo: " + nef.getMessage());
        }
    }

    private static void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
