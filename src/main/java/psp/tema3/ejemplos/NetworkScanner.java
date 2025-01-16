package psp.tema3.ejemplos;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkScanner {

    public static void main(String[] args) {
       


        try {
            // Obtener la dirección IP de la interfaz
            InetAddress ipInterfaz = obtenerDireccionIPInterfaz("eth17");
            if (ipInterfaz == null) {
                System.out.println("No se encontró una dirección IP válida para la interfaz " + "interfaz");
                return;
            }

            String direccion = ipInterfaz.getHostAddress();
            System.out.println("Escaneando la red para la interfaz " + "interfaz" + " con dirección IP: " + direccion);

            // Determinar el rango de direcciones basado en un prefijo /24
            String[] partes = direccion.split("\\.");
            String prefijo = partes[0] + "." + partes[1] + "." + partes[2] + ".";

            for (int i = 1; i < 255; i++) { // Evita 0 y 255
                String ip = prefijo + i;
                if (esHostAlcanzable(ip)) {
                    System.out.println("Host activo: " + ip);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static InetAddress obtenerDireccionIPInterfaz(String nombreInterfaz) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            if (ni.getName().equalsIgnoreCase(nombreInterfaz)) {
                Enumeration<InetAddress> direcciones = ni.getInetAddresses();
                while (direcciones.hasMoreElements()) {
                    InetAddress ip = direcciones.nextElement();
                    if (ip.getHostAddress().contains(".")) { // Solo direcciones IPv4
                        return ip;
                    }
                }
            }
        }
        return null;
    }

    private static boolean esHostAlcanzable(String ip) {
        try {
            InetAddress host = InetAddress.getByName(ip);
            return host.isReachable(100); // Tiempo de espera: 100ms
        } catch (IOException e) {
            return false;
        }
    }
}
