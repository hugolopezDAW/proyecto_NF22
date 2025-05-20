import java.io.InputStream;
import java.util.*;
public class TUI {
    private Scanner inputStream;

    public TUI(InputStream in) {
        this.inputStream = new Scanner(in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("1. Crear contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Borrar contacto");
        System.out.println("4. Actualizar contacto");
        System.out.println("5. Exit");
        return inputStream.nextInt();
    }

    public int mostrarMenuBusqueda() {
        System.out.println("1. Por nombre");
        System.out.println("2. Por apellido");
        System.out.println("3. Por telefono");
        System.out.println("4. Por email");
        return inputStream.nextInt();
    }
    public String[] pedirDatosNuevoContacto() {
        inputStream.nextLine(); // consume newline
        System.out.print("Nombre: ");
        String nombre = inputStream.nextLine();
        System.out.print("Apellidos: ");
        String apellido = inputStream.nextLine();
        System.out.print("Teléfono: ");
        String telefono = inputStream.nextLine();
        System.out.print("Email: ");
        String email = inputStream.nextLine();
        return new String[] { nombre, apellido, telefono, email };
    }
    public String[] pedirDatosActualizarContacto() {
        String[] datos = new String[4];
        System.out.print("Nuevo nombre: ");
        datos[0] = inputStream.nextLine();

        System.out.print("Nuevo apellido: ");
        datos[1] = inputStream.nextLine();

        System.out.print("Nuevo teléfono: ");
        datos[2] = inputStream.nextLine();

        System.out.print("Nuevo email: ");
        datos[3] = inputStream.nextLine();


        return datos;
    }
    public int pedirIdBorrarContacto() {
        System.out.println("ID del contacto a borrar: ");
        return inputStream.nextInt();
    }
    public void mostrarLinea (Object datos) {
        System.out.println(datos.toString());
    }
    public void mostrarLinea(Object[] datos) {
        for (Object d : datos) {
            System.out.println(d.toString());
        }
    }
}
