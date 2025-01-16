package psp.tema3.ejemplos;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class Ej31_NetworkInterface {
    public static void main(String[] args) throws SocketException,UnknownHostException {

        // getNetworkInterfaces() obtenemos la lista de todas las interfaces en el sistema
        ArrayList<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

        System.out.println("Información de las tarjetas de red del sistema...\n");
        for (NetworkInterface iface :  interfaces) {
            // isUp() comprueba si la interfaz está activa y levantada no 
            if (iface.isUp()) {
                // getName() método
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Interface Name: " + iface.getName());

                // getDisplayName() método
                System.out.println("Interface display name: " + iface.getDisplayName());

                // getHardwareAddress() método
                System.out.println("Hardware Address: " +
                        Arrays.toString(iface.getHardwareAddress()));

                // getParent() método
                System.out.println("Parent: " + iface.getParent());

                // getIndex() método
                System.out.println("Index: " + iface.getIndex());

                // Interface addresses of the network interface
                System.out.println("\tInterface addresses: ");

                // getInterfaceAddresses() método
                for (InterfaceAddress addr : iface.getInterfaceAddresses()) {
                    System.out.println("HO");
                    System.out.println("\t\t" + addr.getAddress().toString());
                }
                // Interface addresses of the network interface
                System.out.println("\tInetAddresses associated with this interface: ");

                // getInetAddresses() method returns list of all
                // addresses currently bound to this interface
                Enumeration<InetAddress> en = iface.getInetAddresses();
                while (en.hasMoreElements()) {
                    System.out.println("HA");
                    System.out.println("\t\t" + en.nextElement().toString());
                }

                // getMTU() method
                System.out.println("\tMTU: " + iface.getMTU());

                // getSubInterfaces() method
                System.out.println("\tSubinterfaces: " +
                        Collections.list(iface.getSubInterfaces()));

                // isLoopback() method
                System.out.println("\tis loopback: " + iface.isLoopback());

                // isVirtual() method
                System.out.println("\tis virtual: " + iface.isVirtual());

                // isPointToPoint() method
                System.out.println("\tis point to point: " + iface.isPointToPoint());

                // supportsMulticast() method
                System.out.println("Supports Multicast: " + iface.supportsMulticast());

            }
        }

        // getByIndex() method returns network interface
        // with the specified index
        NetworkInterface nif = NetworkInterface.getByIndex(1);

        // toString() method is used to display textual
        // information about this network interface
        System.out.println("Network interface 1: " + nif.toString());

        // getByName() method returns network interface
        // with the specified name
        NetworkInterface nif2 = NetworkInterface.getByName("eth0");
        System.out.println("Network interface eth0: " + ((nif2!=null)?nif2.toString():"No existe eth0"));

        InetAddress ip = InetAddress.getByName("localhost");
        // getbyInetAddress() method
        NetworkInterface nif3 = NetworkInterface.getByInetAddress(ip);
        System.out.println("\nlocalhost associated with: " + nif3);
    }
}
