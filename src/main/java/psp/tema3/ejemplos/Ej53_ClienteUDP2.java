package psp.tema3.ejemplos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ej53_ClienteUDP2 {

    public static void main(String args[]) throws Exception {

        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();
        byte[] enviados = new byte[1024];
        byte[] recibidos = new byte[1024];

        // DATOS DEL SERVIDOR al que enviar mensaje
        InetAddress IPServidor = (args.length >= 2) ? InetAddress.getByName(args[0]) : InetAddress.getLocalHost();
        int puerto = (args.length >= 2) ? Integer.parseInt(args[1]) : 9876;

        System.out.println(puerto);
        System.out.println(IPServidor.getHostAddress());

        // INTRODUCIR DATOS POR TECLADO
        System.out.print("Introduce mensaje: ");
        String cadena = in.readLine();
        enviados = cadena.getBytes();

        // ENVIANDO DATAGRAMA AL SERVIDOR
        System.out.println("Enviando " + enviados.length + " bytes al servidor.");
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
        clientSocket.send(envio);

        // RECIBIENDO DATAGRAMA DEL SERVIDOR
        DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        System.out.println("Esperando datagrama....");
        clientSocket.receive(recibo);
        String mayuscula = new String(recibo.getData());

        // OBTENIENDO INFORMACIÓN DEL DATAGRAMA
        InetAddress IPOrigen = recibo.getAddress();
        int puertoOrigen = recibo.getPort();
        System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
        System.out.println("\tDatos: " + mayuscula.trim());

        //cerrar socket
        clientSocket.close();
    }
}
