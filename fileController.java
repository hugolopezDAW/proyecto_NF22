import java.io.File;

public class fileController extends controlador {
    private File dataFolder;
    private File contactFolder;

    @Override
    public contacto nuevoContacto(String nombre, String apellido, String telefono, String email){
        contacto c = super.nuevoContacto(nombre, apellido, telefono, email);

        return c;
    }


}
