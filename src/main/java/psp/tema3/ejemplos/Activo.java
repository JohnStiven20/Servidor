package psp.tema3.ejemplos;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class Activo implements Runnable {

    private final String dirreccion;
    private final int numero;
    private final AtomicInteger contador;
    private final int ultimo;


    public Activo(String dirreccion, int numero, AtomicInteger contador, int ultimo) {
        this.dirreccion = dirreccion;
        this.numero = numero;
        this.contador = contador;
        this.ultimo = ultimo;
    }

    @Override
    public void run() {

        try {
            todasLasPosibilidades(dirreccion);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        if (numero >= 254) {
            System.out.println(contador);
        }

    }

    synchronized void todasLasPosibilidades(String dirreccion) throws Throwable {

        String nueva = dirreccion.substring(0, 9);
        recorrer(nueva + "." + numero);

    }

    public void recorrer(String dirreccion) throws Throwable {
        InetAddress inetAddress = InetAddress.getByName(dirreccion);

        if (inetAddress.isReachable(100)) {
            System.out.println("Es alcanzable " + inetAddress);
            contador.set(contador.get() + 1);
         }
    }

    public int getContador() {
        return contador.get();
    }

}
