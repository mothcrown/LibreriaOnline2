/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriaonline2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mothcrown
 */
public class LibreriaOnline2 {

    static List<Cliente> listaClientes;
    static List<Libro> listaLibros;
    static List<Visita> listaVisitas;
    static Persistence ficheroClientes;
    static Persistence ficheroLibros;
    static Persistence ficheroVisitas;
    static Visita visita;
    static Descuento descuento;
    static Servicio servicios;
    static Persistence persistence;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cargaClientes();
        cargaVisitas();
        cargaLibros();
        descuento = new Descuento();
        servicios = new Servicio();
        String usuario = "invitado";
        visita = null;
        
        do {
            usuario = mostrarMenu(usuario);
        } while (!usuario.equals("SALIR"));
    }

    public static String mostrarMenu(String usuario) {
        int modo = menuTexto(usuario);
        Scanner sc = new Scanner(System.in);
        System.out.printf("Introduzca opción: ");
        int op = validaOpcion(sc.nextLine(), false);
        String user = (op >= 0) ? opcionesMenu(op, modo) : usuario;
        return user;
    }
    
    public static int menuTexto(String usuario) {
        int valor = -1;
        switch(usuario) {
            case "invitado":    menuInvitado();
                                valor = 0;
                                break;
            case "cliente":     menuCliente();
                                valor = 1;
                                break;
            default:            break;
        }
        return valor;
    }
    
    public static void menuInvitado(){
        System.out.print("\n******** LIBRERÍA ONLINE DAWRIO ********\n");
        System.out.print("-- La pasión por leer.\n");
        System.out.print("1.\tCatálogo de libros\n");
        System.out.print("2.\tCatálogo de servicios\n");
        System.out.print("0.\tSalir\n");
        System.out.print("--------------------------------\n");
        System.out.print("9.\tIngresar\n");
    }
    
    public static void menuCliente(){
        System.out.print("\n******** LIBRERÍA ONLINE DAWRIO ********\n");
        System.out.print("-- ¡Bienvenido, " + visita.getCliente().getNombre() + "!\n");
        System.out.print("1.\tComprar libros\n");
        System.out.print("2.\tComprar servicios\n");
        System.out.print("0.\tLog out\n");
        System.out.print("--------------------------------\n");
        System.out.print("9.\tMi perfil\n");
        
    }
    
    public static String opcionesMenu(int op, int modo) {
        String usuario = "invitado";
        switch(modo) {
            case 0:     usuario = opcionesInvitado(op);
                        break;
            case 1:     usuario = opcionesCliente(op);
                        break;
            default:    break;
        }
        return usuario;
    }
    
    public static String opcionesInvitado(int op) {
        String usuario = "invitado";
        switch(op) {
            case 1:     mostrarLibros(usuario);
                        break;
            case 2:     mostrarServicios(usuario);
                        break;
            case 0:     usuario = "SALIR";
                        break;
            case 9:     usuario = ingresarUsuario();
                        break;
            default:    System.out.print("\n¡Elige una opción válida!\n");
                        break;
        }
        return usuario;
    }
    
    public static String opcionesCliente(int op) {
        String usuario = "cliente";
        switch(op) {
            case 1:     comprarLibros(usuario);
                        break;
            case 2:     comprarServicios(usuario);
                        break;
            case 0:     usuario = "invitado";
                        listaVisitas.add(visita);
                        ficheroVisitas.guarda(listaVisitas);
                        ficheroClientes.guarda(listaClientes);
                        break;
            case 9:     verPerfil();
                        break;
            default:    System.out.print("\n¡Elige una opción válida!\n");
                        break;
        }
        return usuario;
    }
    
    public static void verPerfil() {
        for (int i = 0; i < listaVisitas.size(); i++) {
            if (visita.getCliente().getNombre().toUpperCase().equals(listaVisitas.get(i).getCliente().getNombre().toUpperCase())) {
               System.out.print(listaVisitas.get(i).toString() + "\n");
            }
        }
        System.out.print(visita.toString() + "\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInserte cualquier caracter para regresar: ");
        String salir = sc.nextLine();
    }
    
    public static void comprarLibros(String usuario) {
        mostrarLibros(usuario);
        double compra;
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Qué libro desea comprar? (¡Pulse 0 para salir sin comprar!): ");
        int op = validaOpcion(sc.nextLine(), false);
        if (op > 0) {
            compra = listaLibros.get(op - 1).getPrecio() - (listaLibros.get(op - 1).getPrecio() * descuento.getDescuentoProductos(visita.getCliente().getTipoMiembro()));
            visita.setGastoProductos(visita.getGastoProductos() + compra);
            System.out.print("\n¡Gracias por su compra!\n");
        }
    }
    
    public static void comprarServicios(String usuario) {
        mostrarServicios(usuario);
        double compra;
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Qué servicio desea comprar? (¡Pulse 0 para salir sin comprar!)");
        int op = validaOpcion(sc.nextLine(), false);
        if (op > 0) {
            switch(op){
                case 1: compra = servicios.getPreciosServicio("mensual") - (servicios.getPreciosServicio("mensual") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 2: compra = servicios.getPreciosServicio("trimestral") - (servicios.getPreciosServicio("trimestral") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 3: compra = servicios.getPreciosServicio("anual")- (servicios.getPreciosServicio("anual") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 4: compra = servicios.getPreciosMembresia("Plata") - (servicios.getPreciosMembresia("Plata") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Plata");
                        break;
                case 5: compra = servicios.getPreciosMembresia("Oro") - (servicios.getPreciosMembresia("Oro") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Oro");
                        break;
                case 6: compra = servicios.getPreciosMembresia("Premium") - (servicios.getPreciosMembresia("Premium") * descuento.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Premium");
                        break;
            }
            System.out.print("\n¡Gracias por su compra!\n");
        }
    }
    
    public static String ingresarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntroduzca su nombre de usuario (¡O cree uno nuevo si es la primera vez que nos visita!): ");
        String user = sc.nextLine();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (user.toUpperCase().equals(listaClientes.get(i).getNombre().toUpperCase()))
                visita = new Visita(listaClientes.get(i), new Date());
        }
        if (visita == null) {
            Cliente nuevoCliente = new Cliente("user");
            listaClientes.add(nuevoCliente);
            visita = new Visita(nuevoCliente, new Date());
        }
        
        return "cliente";
    }
    
    public static void mostrarLibros(String usuario) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        sb.append("\nCatálogo de Libros");
        sb.append("\n---------------------");
        for (int i = 0; i < listaLibros.size(); i++) {
            sb.append("\n".concat(Integer.toString(i + 1)));
            sb.append(". \tTítulo: ".concat(listaLibros.get(i).getTitulo()));
            sb.append("\n\tAutor: ".concat(listaLibros.get(i).getAutor()));
            sb.append("\n\tPrecio: ".concat(listaLibros.get(i).getPrecio() + " euros\n"));
        }
        sb.append("\n");
        System.out.print(sb.toString());
        if (usuario.equals("invitado")) {
            System.out.print("\nInserte cualquier caracter para regresar: ");
            String salir = sc.nextLine();
        }
    }
    
    public static void mostrarServicios(String usuario) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        sb.append("\nCatálogo de Servicios");
        sb.append("\n---------------------\n");
        sb.append("1.\tSuscripción mensual: ");
        sb.append("\t".concat(servicios.getPreciosServicio("mensual") + " euros\n"));
        sb.append("2.\tSuscripción trimestral: ");
        sb.append("\t".concat(servicios.getPreciosServicio("trimestral") + " euros\n"));
        sb.append("3.\tSuscripción anual: ");
        sb.append("\t".concat(servicios.getPreciosServicio("anual") + " euros\n"));
        sb.append("4.\tMembresía Plata: ");
        sb.append("\t".concat(servicios.getPreciosMembresia("Plata") + " euros\n"));
        sb.append("5.\tMembresía Oro: ");
        sb.append("\t".concat(servicios.getPreciosMembresia("Oro") + " euros\n"));
        sb.append("6.\tMembresía Premium: ");
        sb.append("\t".concat(servicios.getPreciosMembresia("Premium") + " euros\n"));
        System.out.print(sb.toString());
        if (usuario.equals("invitado")) {
            System.out.print("\nInserte cualquier caracter para regresar: ");
            String salir = sc.nextLine();
        }
    }
    
    public static int validaOpcion(String valor, Boolean modo) {
        int num = -1;
        try {
            num = Integer.parseInt(valor);
            if (modo && num < 0)
                System.out.print("\n¡No has introducido un número válido!\n");
        } catch(NumberFormatException e) {
            System.out.print("\n¡No has introducido un número entero!\n");
        }
        return num;
    }
    
    public static void cargaClientes() {
        ficheroClientes = new FilePersistence();
        ficheroClientes.abrir("clientes.dat");
        
        ArrayList<Cliente> clientes1 = new ArrayList<>();
        
        Cliente cliente = new Cliente("Ayoze");
        cliente.setMiembro(true);
        cliente.setTipoMiembro("Oro");
        clientes1.add(cliente);
        
        cliente = new Cliente("Carlos");
        cliente.setMiembro(true);
        cliente.setTipoMiembro("Premium");
        clientes1.add(cliente);
        
        cliente = new Cliente("Alejandro");
        cliente.setMiembro(false);
        clientes1.add(cliente);
        
        ficheroClientes.guarda(clientes1);
        listaClientes = clientes1;
    }
    
    public static void cargaVisitas() {
        ficheroVisitas = new FilePersistence();
        ficheroVisitas.abrir("visitas.dat");
        
        ArrayList<Visita> libroVisitas = new ArrayList<>();
        
        Visita visita = new Visita(listaClientes.get(0), new Date());
        libroVisitas.add(visita);
        
        ficheroVisitas.guarda(libroVisitas);
        listaVisitas = libroVisitas;
    }
    
    public static void cargaLibros() {
        ficheroLibros = new FilePersistence();
        ficheroLibros.abrir("libros.dat");
        
        ArrayList<Libro> libreria = new ArrayList<>();
        
        Libro libro = new Libro("Drácula", "Bram Stoker", 15);
        libreria.add(libro);
        libro = new Libro("El Aleph", "Jorge Luis Borges", 25);
        libreria.add(libro);
        libro = new Libro("La Estación de la Calle Perdido", "China Mieville", 20);
        libreria.add(libro);
        
        ficheroLibros.guarda(libreria);
        listaLibros = libreria;
    }
}
