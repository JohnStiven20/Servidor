package psp.tema3.ejemplos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Ej53_ServidorUDPBasico {
    public static void main(String[] argv) throws Exception {

        byte[] bufer = new byte[1024];
        DatagramSocket socket = new DatagramSocket(12345);


        System.out.println("Esperando Datagrama ................");
        DatagramPacket datagramaRecibido = new DatagramPacket(bufer, bufer.length);
        socket.receive(datagramaRecibido);

        String mensajeRecibido = new String(datagramaRecibido.getData());

        String valor = "VIVA ESPANA";
        bufer = valor.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(bufer, bufer.length , datagramaRecibido.getAddress() , datagramaRecibido.getPort());
        socket.send(datagramPacket);

        //Información recibida
        System.out.println("Número de Bytes recibidos: " + datagramaRecibido.getLength());
        System.out.println("Contenido del Paquete    : " + mensajeRecibido.trim());

        System.out.println("Puerto origen del mensaje: " + datagramaRecibido.getPort());

        System.out.println("IP de origen             : " + datagramaRecibido.getAddress().getHostAddress());
        System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());

        // Liberamos los recursos
        socket.close(); 
    }
}
