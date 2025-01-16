package psp.tema3.ejemplos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ej52_ClienteUDPBasico {
    public static void main(String[] argv) throws Exception {

        // IP y puerto al que se envía el Datagrama
        InetAddress destino = InetAddress.getLocalHost();
        int port = 12345;

        // Buffer para recibir el datagrama
        byte[] buffer = new byte[1024];

        // El mensaje a enviar en el Datagrama se convierte a bytes
        String mensajeEnviado = "Enviando Saludos !!";
        buffer = mensajeEnviado.getBytes(); // codifico String a bytes

        // Se preparara el DatagramPacket que se va a enviar
        DatagramPacket datagramaEnviado = new DatagramPacket(buffer, buffer.length, destino, port);
        // En este caso, especificamos un puerto, aunque podríamos dejarlo para
        // que el SO asigne uno libre
        DatagramSocket socket = new DatagramSocket(34567);

        System.out.println("Host destino : " + destino.getHostName());
        System.out.println("IP Destino : " + destino.getHostAddress());
        System.out.println("Puerto local del socket: " + socket.getLocalPort());
        System.out.println("Puerto al que envio: " + datagramaEnviado.getPort());

        // Envío del Datagrama
        socket.send(datagramaEnviado);

        System.out.println("------------------------------------------------------------------");

        DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);

        socket.receive(datagramaRecibido);

        String valorNuevo = new String(datagramaEnviado.getData(),0 ,datagramaRecibido.getLength());

        System.out.println(" 1 " + valorNuevo);

        // Cierre y liberación de recursos
        socket.close();
    }
}
