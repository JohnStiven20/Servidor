package psp.tema3.ejemplos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.stream.Collectors;

public class Worker implements Runnable {

    private final Socket socketCliente;
    private DataInputStream entrada = null;
    private DataOutputStream salida = null;
    private String nombre = "";

    public Worker(Socket socketCliente) {
        this.socketCliente = socketCliente;

    }

    @Override
    public void run() {

        boolean conectado = true;

        try { // Establece canal de entrada

            entrada = new DataInputStream(socketCliente.getInputStream());
            salida = new DataOutputStream(socketCliente.getOutputStream());

            System.out.println("Conexión aceptada: " + socketCliente.getInetAddress());

            agregarCliente();

            while (conectado) {

                String mensajeRecibido = entrada.readUTF();
                String comando = "";
                String enviarMensaje = "";

                if (!mensajeRecibido.isEmpty()) {
                    comando = mensajeRecibido.substring(0, 3);
                }

                if (comando.equals("MSG")) {
                    enviarMensaje = "CHT " + nombre + "," + mensajeRecibido.substring(4, mensajeRecibido.length());
                    mensajeTodos(enviarMensaje);
                } else if (comando.equals("CON")) {

                    String nombreUsuario = mensajeRecibido.substring(mensajeRecibido.indexOf(" ") + 1,mensajeRecibido.length()).trim();
                    System.out.println(nombreUsuario);

                    boolean encontrado = Ej43_ServidorBasico.clientes.stream().anyMatch(cliente -> cliente.nombre.equals(nombreUsuario));

                    if (!encontrado) {
                        this.nombre = nombreUsuario;
                        enviarMensaje = "OK Todo bien";
                        salida.writeUTF(enviarMensaje);
                        salida.flush();

                    } else {
                        System.out.println("Comando de fuera: NOK");
                        enviarMensaje = "NOK Nombre repetido";
                        salida.writeUTF(enviarMensaje);
                        salida.flush();
                    }
                } else if (comando.equals("LUS")) {

                    String clientesNombres = "LST "+ Ej43_ServidorBasico.clientes.stream().map(x -> x.nombre).collect(Collectors.joining(","));
                    mensajeTodos(clientesNombres);

                } else if (comando.equals("PRV")) {

                    System.out.println(mensajeRecibido + " QUE PASA EN PRV");
                    String usuarioDestinatario = mensajeRecibido.substring(mensajeRecibido.indexOf(" ") + 1, mensajeRecibido.lastIndexOf(","));
                    String mensaje = mensajeRecibido.substring(mensajeRecibido.lastIndexOf(",") + 1, mensajeRecibido.length());
                    mensajePrivado(mensaje, usuarioDestinatario, this.nombre);
                
                } else if (comando.equals("EXI")) {
                    String mensaje = "EXI " + this.nombre;
                    mensajeTodosExit(mensaje);;
                    conectado = false;
                    eliminarCliente();
                }

                System.out.println("<-- Servidor: " + mensajeRecibido);
                System.out.println("--> Cliente " + nombre + " : " + enviarMensaje);
                System.out.println( "--------------------------------------------------------------------------------------");

            }
        } catch (IOException e) {
            System.out.println("dwe" + e.getMessage());
        } finally {
            eliminarCliente();
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socketCliente != null) socketCliente.close();
                System.out.println("Conexión cerrada para el cliente: " + nombre);
            } catch (IOException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void mensajeTodos(String mensaje) {
        synchronized (Ej43_ServidorBasico.clientes) {
            for (Worker cliente : Ej43_ServidorBasico.clientes) {
                try {
                    cliente.salida.writeUTF(mensaje);
                    cliente.salida.flush();
                } catch (IOException e) {
                    System.out.println("Error enviando mensaje a " + cliente.nombre + ": " + e.getMessage());
                }
            }
        }
    }
    

    public void mensajeTodosExit(String mensaje) {
        for (Worker cliente : Ej43_ServidorBasico.clientes) {
            try {

                cliente.salida.writeUTF(mensaje);
                cliente.salida.flush();
   
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean mensajePrivado(String mensaje, String usuarioDestinario, String usarioLocal) {
        for (Worker cliente : Ej43_ServidorBasico.clientes) {
            try {

                if (cliente.nombre.equals(usuarioDestinario)) {

                    String mensajeEnviarCliente = "PRV " + usarioLocal + "," + mensaje;
                    cliente.salida.writeUTF(mensajeEnviarCliente);
                    cliente.salida.flush();
                    return true;
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    public void agregarCliente() {
        synchronized (Ej43_ServidorBasico.clientes) {
            Ej43_ServidorBasico.clientes.add(this);
        }
    }

    public void eliminarCliente() {
        synchronized (Ej43_ServidorBasico.clientes) {
            Ej43_ServidorBasico.clientes.remove(this);
        }
    }
    
    
}
