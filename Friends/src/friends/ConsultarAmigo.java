package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class ConsultarAmigo {

    public static void consultarAmigo(String searchName) {
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

                if (name.equals(searchName)) {
                    found = true;
                    mostrarDialogo("Amigo encontrado: " + name + " - " + number);
                    break;
                }
            }

            if (!found) {
                mostrarDialogo("Amigo no encontrado.");
            }

            raf.close();
        } catch (IOException ioe) {
            mostrarDialogo("Error al consultar amigo: " + ioe.getMessage());
        } catch (NumberFormatException nef) {
            mostrarDialogo("Error al consultar amigo: " + nef.getMessage());
        }
    }

    private static void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
