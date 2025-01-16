package psp.tema3.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URI;

/**
 * Ejemplo de uso de URLConnection
 */
public class Ej35_UrlConnection {

    public static void main(String[] args) throws MalformedURLException, IOException {

        // Creamos un objeto de la clase URL

        // Desde la versión 20 de Java usamos un objeto URI
        URL u = URI.create("https://www.google.com").toURL();
 
        // Creamos un objeto URLConnection para comunicarnos con la URL
        URLConnection urlconnect = u.openConnection();

        // Para leer el stream
        InputStream stream = urlconnect.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        // Leemos el buffer de entrada
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
