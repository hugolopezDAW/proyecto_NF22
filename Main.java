import java.util.Scanner;

public class Main {
    private static controlador controlador = new fileController("/home/daw1/Escriptori/contactos");
    private static TUI tui = new TUI(System.in);
    public static void main(String[] args) {
        while(true) {
            int opcion = tui.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> crearContacto();
                case 2 -> buscarContacto();
                case 3 -> borrarContacto();
                case 4 -> actualizarContacto();
                case 5 -> System.exit(0);
                default -> System.out.println("Opcion no válida. ");
            }
        }
    }
    public static void crearContacto() {
        String[] datos = tui.pedirDatosNuevoContacto();
        contacto c = controlador.nuevoContacto(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
        tui.mostrarLinea(c);
    }
    public static void buscarContacto() {
        int criterio = tui.mostrarMenuBusqueda();
        System.out.println("Valor de búsqueda");
        String valor = tui.leerLinea();
        switch (criterio) {
            case 1 -> tui.mostrarLinea(controlador.buscarContactoPorNombre(valor).toArray());
            case 2 -> tui.mostrarLinea(controlador.buscarContactoPorApellido(valor).toArray());
            case 3 -> tui.mostrarLinea(controlador.buscarContactoPorTelefono(Integer.parseInt(valor)).toArray());
            case 4 -> tui.mostrarLinea(controlador.buscarContactoPorEmail(valor).toArray());
        }
    }
    public static void borrarContacto() {
        int id = tui.pedirIdBorrarContacto();
        controlador.borrarContacto(id);
    }
    public static void actualizarContacto() {
        int id = tui.pedirIdBorrarContacto();
        String [] datos = tui.pedirDatosActualizarContacto();
        contacto c = controlador.actualizarContacto(id, datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
        if (c != null) {
            tui.mostrarLinea(c);
        }else {
            System.out.println("No se ha encontrado ningun contacto con este ID");
        }
    }
}