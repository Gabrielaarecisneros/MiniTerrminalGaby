/*
* Clase MiniFileManager : Tendrá los atributos y métodos que necesites para realizar las distintas
* operaciones relacionadas con la gestión de archivos. Necesitarás al menos un método por cada
* operación. Se anzará una excepción si se produce un error o la operación solicitada no es posible.
* Algunos ejemplos que podrías implementar:
*   ● String getPWD() : Devuelve una cadena de texto con la carpeta actual.
*   ● boolean changeDir(String dir) : Cambia la carpeta actual a dir . Devuelve ‘true’ si fué posible.
*   ● void printList(boolean info) : Muestra una lista con los directorios y archivos de la carpeta
*     actual. Si info es ‘true’ mostrará también su tamaño y fecha de modificación.
*   ● etc.
 */
package CasoPractico_A_MiniTerminalFileManager;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class MiniFileManager {

    File ruta;

    public MiniFileManager(File ruta) {
        this.ruta = ruta;
    }

    public String getPWD() {
        return ruta.getAbsolutePath();
    }

    public File getCurrentPath() {
        return ruta;
    }

    public boolean changeDir(File dir) {
        ruta = dir;
        return ruta.exists();
    }
    /*
    if (dir.exists()) {
            ruta = new File(ruta.getPath() + dir.getName());
        } else {
            System.out.println("No existe la ruta indicada");
        }
    */

    // Muestra una lista con los directorios y archivos de la carpeta actual.
    // Si info=true muestra también su tamaño y fecha de modificación
    public void printList(boolean info) {
        // Obtenemos lista de archivos de la carpeta actual
        File[] lista = getCurrentPath().listFiles();

        // Ordena la lista alfabéticamente
        Arrays.sort(lista);

        // Recorre la lista y muestra las carpetas ordenadas
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isDirectory()) {
                if (info) {
                    System.out.println("[*] " + lista[i].getName() + "\t" + lista[i].length() + " bytes\t" + new Date(lista[i].lastModified()));
                } else {
                    System.out.println("[*] " + lista[i].getName());
                }
            }
        }

        // Recorre la lista y muestra los archivos ordenados
        // mira el  tamaño del  archivo 
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isFile()) {
                if (info) {
                    System.out.println("[A] " + lista[i].getName() + "\t" + lista[i].length() + " bytes\t" + new Date(lista[i].lastModified()));
                } else {
                    System.out.println("[A] " + lista[i].getName());
                }
            }
        }
    }

    public boolean makeDir(String dir) {
        return (new File(dir)).mkdir();
    }

    public boolean deleteDir(File dir) {
        File contenido[] = dir.listFiles();
        if (dir.listFiles().length != 0) {
            for (File f : contenido) {
                if (f.isDirectory()) {
                    System.out.println("No es posible borrar el directorio seleccionado ya que contiene otros directorios o subcarpetas en su interior.");
                    return false;
                }
            }
            for (File f : contenido) {
                f.delete();
            }
        }
        return dir.delete();
    }

    public boolean moveFile(File origen, File destino) {
        return origen.renameTo(destino);
    }

}//FIN Class
