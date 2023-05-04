/*
 * Clase principal (con función main) que se encargará de interactuar con el
 * usuario e interpretar los comandos (qué comando se pide, argumentos, etc.). Utilizará la segunda
 * clase para realizar las operaciones de gestión de archivos. Manejará todas las posibles excepciones.
 */


import CasoPractico_A_MiniTerminalFileManager.MiniFileManager;
import java.io.File;
import java.util.Scanner;

public class MiniTerminal {
    static Scanner teclado=new Scanner(System.in);
    static File carpeta_actual=new File("ejerciciominimini");
    static MiniFileManager ruta_actual=new MiniFileManager(carpeta_actual);
    
    public static boolean Menu(){
        if (!carpeta_actual.exists()) {
            carpeta_actual.mkdir();
        }
        String comando[], opcion;
        System.out.println("--- Mini Terminal LINUX ---\n"+
                           "❀ pwd : Nos muestra cual es la carpeta actual \n" +
                           "● cd  : Cambia la carpeta actual \n" +
                           "● ls : Muestrame la lista de directorios y archivos de la carpeta actual \n"+
                           "● ll : Como ls pero muestra también el tamaño y la fecha de última modificación\n" +
                           "● mkdir :Crea el directorio y carpetas \n" +
                           "● rm  : Borra  carpeta\n" +
                           "● mv : Mueve o renombra ‘FILE1’ a ‘FILE2’.\n"+
                           "● help : Muestra ayuda\n" +
                           "● exit : Termina el programa. felicidades \n");   
        
        opcion=teclado.nextLine();
        // split para separar en los espacios, pero sin remplazar espacios
        comando=opcion.split(" ");
        return realizarComando(comando);    
    }
    
    public static boolean realizarComando(String comando[]){
        switch(comando[0]){
            case "pwd":
                System.out.println("Ruta actual: "+ruta_actual.getPWD());
            break;
            case "cd":
                //ejemplo de funcionamiento del comando cd:
                    //cd ejerciciominimini/prueba2
                if(comando.length==2){
                    
                    ruta_actual.changeDir(new File(comando[1])
                    );
                }else{
                    ruta_actual.changeDir(new File(comando[2]).getParentFile());
                }             
            break;
            case "ls":
                ruta_actual.printList(false);
            break;
            case "ll":
                ruta_actual.printList(true);
            break;
            case "mkdir":
                ruta_actual.makeDir(comando[1]);
            break;
            case "rm":
                File dirBorrar=new File(comando[1]);
                ruta_actual.deleteDir(dirBorrar);
            break;
            case "mv":
                ruta_actual.moveFile(new File(comando[1]), new File(comando[2]));
            break;
            case "help":
                              
            break;
            case "exit":
                return false;
            default:
                System.out.println("Comando introducido incorrecto. Inténtelo de nuevo.");
                Menu();
            break;
        }//FIN Switch;  
        return true;
    }

    
    public static void main(String[] args) {
        try {
            boolean bucle=true;
            while(bucle){
                bucle=Menu();// nos  muestra el menu
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
