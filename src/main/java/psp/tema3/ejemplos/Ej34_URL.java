package psp.tema3.ejemplos;

import java.net.MalformedURLException;
import java.net.URL;

public class Ej34_URL {

    
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("https://es.wikipedia.org/wiki/API_Java");
        URL ut = new URL("http", "localhost", 8080, "/api/resource");



        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Host Name: " + url.getHost());
        System.out.println("Port Number: " + url.getPort());
        System.out.println("File Name: " + url.getFile());
        System.out.println("-------------------------------");
        ejemploCompleto();

        // Llama al método para mostrar más información

        // Modifica el método para probar más métodos de URL
    }

    /**
     * Método con un ejemplo más completo
     */
    public static void ejemploCompleto() {
        URL url;
        try {
            url = new URL("https://www.google.com/search?q=programacion++java+network&client=firefox&sourceid=chrome&ie=UTF-8");

            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host Name: " + url.getHost());
            System.out.println("Port Number: " + url.getPort());
            System.out.println("Default Port Number: " + url.getDefaultPort());
            System.out.println("Query String: " + url.getQuery());
            System.out.println("Path: " + url.getPath());
            System.out.println("File: " + url.getFile());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
