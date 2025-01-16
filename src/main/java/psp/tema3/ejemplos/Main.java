package psp.tema3.ejemplos;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente("Stiven", 5));
        clientes.add(new Cliente("Marcos", 8));
        clientes.add(new Cliente("Maria", 6));
        clientes.add(new Cliente("Marcos", 4));

        String nombresClientes = clientes.stream().map(x -> x.getNombre()).collect(Collectors.joining(","));

        System.out.println(nombresClientes);

    }

}
