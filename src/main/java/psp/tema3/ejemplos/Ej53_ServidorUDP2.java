package psp.tema3.ejemplos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ej53_ServidorUDP2 {
    public static void main(String args[]) throws Exception {

        //Puerto por el que escucha el servidor: 9876
        int puerto = 9876;

        byte[] recibidos = new byte[1024];
        byte[] enviados = new byte[1024];
        String cadena;

        if (args.length > 0) {
            try {
                puerto = Integer.parseInt(args[0]);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        DatagramSocket serverSocket = new DatagramSocket(puerto);
         
        while (true) {
            System.out.println("Esperando datagrama.....");

            //RECIBO DATAGRAMA
            recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(paqRecibido);
            System.out.println("Recibiendo...");
            cadena = new String(paqRecibido.getData());

            //DIRECCION ORIGEN
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puertoOrigen = paqRecibido.getPort();
            System.out.println("\tOrigen: " + IPOrigen + ":" + puertoOrigen);
            System.out.println("\tMensaje recibido: " + cadena.trim());

            //CONVERTIR CADENA A MAYÚSCULA
            String mayuscula = cadena.trim().toUpperCase();
            enviados = mayuscula.getBytes();

            //ENVIO DATAGRAMA AL CLIENTE
            DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puertoOrigen);
            serverSocket.send(paqEnviado);

            // Condición de finalización
            if (cadena.trim().equals("*")) {
                break;
            }

        

        }//Fin de while

        serverSocket.close();
        System.out.println("Socket cerrado...");
    }
}
