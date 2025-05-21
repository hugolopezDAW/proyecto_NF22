import java.io.InputStream;
import java.util.Scanner;

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
        return this.leerEntero();
    }

    public int mostrarMenuBusqueda() {
        System.out.println("1. Por nombre");
        System.out.println("2. Por apellido");
        System.out.println("3. Por telefono");
        System.out.println("4. Por email");
        return this.leerEntero();
    }

    public String[] pedirDatosNuevoContacto() {
        this.inputStream.nextLine();
        System.out.print("Nombre: ");
        String nombre = this.inputStream.nextLine();
        System.out.print("Apellidos: ");
        String apellido = this.inputStream.nextLine();
        System.out.print("Teléfono: ");
        String telefono = this.inputStream.nextLine();
        System.out.print("Email: ");
        String email = this.inputStream.nextLine();
        return new String[]{nombre, apellido, telefono, email};
    }

    public String[] pedirDatosActualizarContacto() {
        String[] datos = new String[4];
        System.out.print("Nuevo nombre: ");
        datos[0] = this.inputStream.nextLine();
        System.out.print("Nuevo apellido: ");
        datos[1] = this.inputStream.nextLine();
        System.out.print("Nuevo teléfono: ");
        datos[2] = this.inputStream.nextLine();
        System.out.print("Nuevo email: ");
        datos[3] = this.inputStream.nextLine();
        return datos;
    }

    public int pedirIdBorrarContacto() {
        System.out.println("ID del contacto a borrar: ");
        return this.leerEntero();
    }

    public void mostrarLinea(Object datos) {
        System.out.println(datos.toString());
    }

    public void mostrarLinea(Object[] datos) {
        for(Object d : datos) {
            System.out.println(d.toString());
        }

    }

    public String leerLinea() {
        this.inputStream.nextLine();
        return this.inputStream.nextLine();
    }

    public int leerEntero() {
        while(!this.inputStream.hasNextInt()) {
            System.out.print("Por favor, introduce un número válido: ");
            this.inputStream.next();
        }

        int valor = this.inputStream.nextInt();
        this.inputStream.nextLine();
        return valor;
    }
}
