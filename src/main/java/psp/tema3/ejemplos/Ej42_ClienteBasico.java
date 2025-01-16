package psp.tema3.ejemplos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ej42_ClienteBasico {

    public static void main(String[] args) throws IOException {

        Socket socketCliente = null;
        DataInputStream entrada = null;
        DataOutputStream salida = null;
        String valorEntrada = " ";

        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
            socketCliente = new Socket("localhost", 4444);
            // Obtenemos el canal de entrada
            // Obtenemos el canal de entrada
            entrada = new DataInputStream(socketCliente.getInputStream()); // Obtenemos el canal de salida
            salida = new DataOutputStream(socketCliente.getOutputStream());  // 'true' para auto-flush

        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
            System.exit(-1);
        }
        Scanner teclado = new Scanner(System.in);

        String linea;

        // El programa cliente no analiza los mensajes enviados por el
        // usuario, simplemente los reenvía al servidor hasta que este
        // se despide con "Adios"
        try {
            while (true) {
                // Leo la entrada del usuario
                linea = teclado.nextLine();
                // La envia al servidor por el OutputStream
   
                salida.writeUTF(linea);
                
                String mensajeRecibido = entrada.readUTF();
                // Recibe la respuesta del servidor por el InputStream
                // Envía a la salida estándar la respuesta del servidor
                System.out.println("Respuesta servidor: " + mensajeRecibido);
                // Si es "Adios" es que finaliza la comunicación
                if (linea.equalsIgnoreCase("Adios")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // Libera recursos
        salida.close();
        entrada.close();
        teclado.close();
        socketCliente.close();

    }

}
