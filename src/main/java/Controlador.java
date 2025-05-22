import java.util.List;

public interface Controlador {
    Contacto nuevoContacto(String nombre, String apellido, int telefono, String email);
    Contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email);
    boolean borrarContacto(int id);
    Contacto buscarContactoPorId(int id);
    List<Contacto> buscarContactoPorNombre(String nombre);
    List<Contacto> buscarContactoPorApellido(String apellido);
    List<Contacto> buscarContactoPorTelefono(int telefono);
    List<Contacto> buscarContactoPorEmail(String email);
}
