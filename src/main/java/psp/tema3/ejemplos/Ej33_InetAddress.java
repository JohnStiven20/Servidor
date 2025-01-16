package psp.tema3.ejemplos;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Este ejemplo muestra el uso de la clase InetAddress
 */
public class Ej33_InetAddress {
    public static void main(String[] args) throws IOException {

        InetAddress address1 = InetAddress.getLocalHost();
        System.out.println("InetAddress of Local Host : " + address1 + " Accesible? "+address1.isReachable(100));

        // To get and print InetAddress of Named Host
        InetAddress address2 = InetAddress.getByName("cliente.psp");
        System.out.println("InetAddress of Named Host : " + address2 );

        // To get and print ALL InetAddresses of Named Host
        InetAddress address3[] = InetAddress.getAllByName("www.google.com");
        for (int i = 0; i < address3.length; i++) {
            System.out.println("ALL InetAddresses of Named Host : " + address3[i]);
        }

        // To get and print InetAddresses of Host with specified IP Address
        byte IPAddress[] = { 125, 0, 0, 1 };
        InetAddress address4 = InetAddress.getByAddress(IPAddress);
        System.out.println("InetAddresses of Host with specified IP Address : " + address4);

        // To get and print InetAddresses of Host with specified IP Address and hostname
        byte[] IPAddress2 = { 105, 22, (byte) 223, (byte) 186 };
        InetAddress address5 = InetAddress.getByAddress("gfg.com", IPAddress2);
        System.out.println("InetAddresses of Host with specified IP Address and hostname : " + address5 + " Accesible? "+address5.isReachable(100));
    }
}
