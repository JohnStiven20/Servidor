package psp.tema3.ejemplos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MainImagenes {

    public static void main(String[] args) throws IOException {

        File file = new File("./tema3/imagenes");
        file.mkdir();
        String ruta = "https://cliply.co/wp-content/uploads/2019/04/371903520_SOCIAL_ICONS_1x1_400px.gif";
        String ruta2 = "https://www.google.com/imgres?q=imagenes&imgurl=https%3A%2F%2Fmedia.istockphoto.com%2Fid%2F636379014%2Fes%2Ffoto%2Fmanos-la-formaci%25C3%25B3n-de-una-forma-de-coraz%25C3%25B3n-con-silueta-al-atardecer.jpg%3Fs%3D612x612%26w%3D0%26k%3D20%26c%3DR2BE-RgICBnTUjmxB8K9U0wTkNoCKZRi-Jjge8o_OgE%3D&imgrefurl=https%3A%2F%2Fwww.istockphoto.com%2Fes%2Ffotos%2Fimagenes-de-amor&docid=1ULSeBSgR9NVSM&tbnid=XWhYXXUXVaEkgM&vet=12ahUKEwjDlZDz3_-JAxWKT6QEHfMXGYsQM3oECBoQAA..i&w=612&h=408&hcb=2&ved=2ahUKEwjDlZDz3_-JAxWKT6QEHfMXGYsQM3oECBoQAA.png";
        File file2 = new File(file, "imageno.png");

        String f = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3lXZicmXe4z2OqM657rabyW032NuX0ZGCtw&s";

        descargarImagenes(f, file2);

    }

    public static void descargarImagenes(String imagenUrl, File file) throws IOException {

        URL url = new URL(imagenUrl);
        InputStream inputStream = url.openStream();
        int valor;

        try (OutputStream outputStream = new FileOutputStream(file)) {

            while ((valor = inputStream.read()) != -1) {
                outputStream.write(valor);
                System.out.println(valor);
            }

            outputStream.close();
        }

    }
}
