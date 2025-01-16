package psp.tema3.ejemplos;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

public class Ej32_InterfaceAddress {
    public static void main(String[] args) throws SocketException {
        // Modificarlo según tu sistema para que nos muestre la información de la red local de nuestra tarjeta de red
        NetworkInterface nif = NetworkInterface.getByIndex(1);
        List<InterfaceAddress> list = nif.getInterfaceAddresses();
        for (InterfaceAddress iaddr : list) {
            // método getAddress()
            System.out.println("getAddress() : " + iaddr.getAddress());
            // método getBroadcast() 
            System.out.println("getBroadcast() : " + iaddr.getBroadcast());
            // método getNetworkPrefixLength() 
            System.out.println("PrefixLength : " + iaddr.getNetworkPrefixLength());
            // método hashCode() 
            System.out.println("Hashcode : " + iaddr.hashCode());
            // método toString() 
            System.out.println("toString() : " + iaddr.toString());
            System.out.println("---------------------------------------------");
        }

        
    }
}
