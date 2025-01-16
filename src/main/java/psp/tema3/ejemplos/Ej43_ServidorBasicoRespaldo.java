package psp.tema3.ejemplos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ej43_ServidorBasicoRespaldo {
    public static final int PUERTO = 4444;

    public static void main(String[] args) throws IOException {

        ServerSocket socketServidor = null;

        
        try {

            socketServidor = new ServerSocket(PUERTO);

        } catch (IOException e) {

            System.out.println("No puede escuchar en el puerto: " + PUERTO);
            System.exit(-1);
        }

        Socket socketCliente = null;

        System.out.println("Escuchando: " + socketServidor);
       
        try {

            while (true) {

                socketCliente = socketServidor.accept();
                Worker worker = new Worker(socketCliente,null);

                new Thread(worker).start();
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }
}
