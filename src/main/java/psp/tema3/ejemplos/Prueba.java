package psp.tema3.ejemplos;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Prueba {

    public static void main(String[] args) throws UnknownHostException, SocketException, InterruptedException {

        InetAddress inetAddress = Inet4Address.getLocalHost();
        String ip = "172.18.185.12";
        String dirreccion = inetAddress.getHostAddress();
        System.out.println(dirreccion);
        ArrayList<Thread> lista = new ArrayList<>();
        AtomicInteger contador = new AtomicInteger(0);

        int numeroHostsDisponibles = 255;

        for (int i = 1; i <= numeroHostsDisponibles; i++) {
            Activo activo = new Activo(dirreccion, i -1, contador, numeroHostsDisponibles); 
            lista.add(new Thread(activo));
        }

        for (Thread thread : lista) {
            thread.start();
        }

        System.out.println("-----------------------------------");
    }
}
