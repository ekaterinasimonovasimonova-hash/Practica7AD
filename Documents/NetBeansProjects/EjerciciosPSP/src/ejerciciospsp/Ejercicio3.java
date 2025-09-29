
package ejerciciospsp;

import java.io.IOException;


public class Ejercicio3 {
    public static void main(String[] args) throws IOException, InterruptedException{
        
        int retorno; // 1,0,-1
        long pid; 
        
        ProcessBuilder pb = new ProcessBuilder("ping", "www.google.com");
        
        Process p = pb.start();
        
        pid = p.pid();
        
        retorno = p.waitFor();
            
        System.out.println("El pid:  "+pid);
        System.out.println("El retorno es: "+retorno);
        System.out.println("Salida estandar");
        System.err.println("Salida error");
    }
}
