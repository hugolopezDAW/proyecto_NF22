import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Controlador controlador = new DataBaseController();
    private static final TUI tui = new TUI(System.in);

    public static void main(String[] args) {
        while (true) {
            int opcion = tui.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> crearContacto();
                case 2 -> buscarContacto();
                case 3 -> borrarContacto();
                case 4 -> actualizarContacto();
                case 5 -> {
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void crearContacto() {
        String[] datos = tui.pedirDatosNuevoContacto();
        Contacto c = controlador.nuevoContacto(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
        tui.mostrarLinea(c);
    }

    public static void buscarContacto() {
        int criterio = tui.mostrarMenuBusqueda();
        System.out.print("Valor de búsqueda: ");
        String valor = scanner.nextLine();

        switch (criterio) {
            case 1 -> tui.mostrarLinea(controlador.buscarContactoPorNombre(valor).toArray());
            case 2 -> tui.mostrarLinea(controlador.buscarContactoPorApellido(valor).toArray());
            case 3 -> tui.mostrarLinea(controlador.buscarContactoPorTelefono(Integer.parseInt(valor)).toArray());
            case 4 -> tui.mostrarLinea(controlador.buscarContactoPorEmail(valor).toArray());
            default -> System.out.println("Criterio de búsqueda no válido.");
        }
    }

    public static void borrarContacto() {
        int id = tui.pedirIdBorrarContacto();
        boolean exito = controlador.borrarContacto(id);
        if (exito) {
            System.out.println("Contacto borrado correctamente.");
        } else {
            System.out.println("No se encontró un contacto con ese ID.");
        }
    }

    public static void actualizarContacto() {
        int id = tui.pedirIdBorrarContacto();
        Contacto existente = controlador.buscarContactoPorId(id);

        if (existente == null) {
            System.out.println("No se ha encontrado ningún contacto con ese ID.");
            return;
        }

        String[] datos = tui.pedirDatosActualizarContacto();
        Contacto actualizado = controlador.actualizarContacto(id, datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);

        if (actualizado != null) {
            tui.mostrarLinea(actualizado);
        } else {
            System.out.println("Ha ocurrido un error al actualizar el contacto.");
        }
    }
}
