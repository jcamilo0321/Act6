package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;

public class EliminarAmigo {

    public static void eliminarAmigo(String nameToDelete) {
        try {
            File inputFile = new File("D:\\Users\\User\\Documents\\NetBeansProjects\\friendsContact.txt");
            File tempFile = new File("D:\\Users\\User\\Documents\\NetBeansProjects\\temp.txt");

            if (!inputFile.exists()) {
                mostrarDialogo("El archivo no existe.");
                return;
            }

            RandomAccessFile raf = new RandomAccessFile(inputFile, "rw");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = raf.readLine()) != null) {
                String[] lineSplit = currentLine.split("!");
                String name = lineSplit[0];

                if (!name.equals(nameToDelete)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            raf.close();

            // Eliminar el archivo original y renombrar el temporal
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                mostrarDialogo("Amigo eliminado exitosamente.");
            } else {
                mostrarDialogo("Error al eliminar amigo. No se pudo actualizar el archivo.");
            }
        } catch (IOException ioe) {
            mostrarDialogo("Error al eliminar amigo: " + ioe.getMessage());
        }
    }

    private static void mostrarDialogo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}



/*
+-------------------------------------+
|               Formulario             |
+-------------------------------------+
| -TxtName: JTextField                |
| -TxtNumber: JTextField              |
| -BtmCreate: JButton                 |
| -BtmRead: JButton                   |
| -BtmUpdate: JButton                 |
| -BtmDelete: JButton                 |
| -BtmExit: JButton                   |
| -BtmClear: JButton                  |
+-------------------------------------+
| +BtmCreateActionPerformed()         |
| +BtmReadActionPerformed()           |
| +BtmUpdateActionPerformed()         |
| +BtmDeleteActionPerformed()         |
| +TxtNameActionPerformed()           |
| +TxtNumberActionPerformed()         |
| +BtmClearActionPerformed()          |
| +BtmExitActionPerformed()           |
+-------------------------------------+
               |
               |
               v
+-------------------------------------+
|           AgregarAmigo               |
+-------------------------------------+
| +agregarAmigo(String, long): void   |
+-------------------------------------+
               ^
               |
               |
+-------------------------------------+
|          ConsultarAmigo              |
+-------------------------------------+
| +consultarAmigo(String): void       |
+-------------------------------------+
               ^
               |
               |
+-------------------------------------+
|         ActualizarAmigo              |
+-------------------------------------+
| +actualizarAmigo(String, String, long): void |
+-------------------------------------+
               ^
               |
               |
+-------------------------------------+
|          EliminarAmigo               |
+-------------------------------------+
| +eliminarAmigo(String): void        |
+-------------------------------------+


 */
///
