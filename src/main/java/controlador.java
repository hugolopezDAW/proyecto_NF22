import java.util.*;
import java.util.stream.Collectors;

public interface controlador {
    contacto nuevoContacto(String nombre, String apellido, int telefono, String email);

    contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email);

    boolean borrarContacto(int id);

    contacto buscarContactoPorId(int id);

    List<contacto> buscarContactoPorNombre(String nombre);

    List<contacto> buscarContactoPorApellido(String apellido);

    List<contacto> buscarContactoPorTelefono(int telefono);

    List<contacto> buscarContactoPorEmail(String email);
}
