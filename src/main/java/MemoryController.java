import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryController implements Controlador {
    protected int lastContactId = 0;
    private Map<Integer, Contacto> contacts = new HashMap<>();

    @Override
    public Contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        Contacto c = new Contacto(nombre, apellido, telefono, email);
        try {
            Field idField = Contacto.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.setInt(c, ++lastContactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contacts.put(lastContactId, c);
        return c;
    }

    @Override
    public Contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        Contacto c = contacts.get(id);
        if (c != null) {
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);
            c.setEmail(email);
        }
        return c;
    }

    @Override
    public boolean borrarContacto(int id) {
        return contacts.remove(id) != null;
    }

    @Override
    public Contacto buscarContactoPorId(int id) {
        return contacts.get(id);
    }

    @Override
    public List<Contacto> buscarContactoPorNombre(String nombre) {
        return contacts.values().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contacto> buscarContactoPorApellido(String apellido) {
        return contacts.values().stream()
                .filter(c -> c.getApellido().equalsIgnoreCase(apellido))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contacto> buscarContactoPorTelefono(int telefono) {
        return contacts.values().stream()
                .filter(c -> c.getTelefono() == telefono)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contacto> buscarContactoPorEmail(String email) {
        return contacts.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }
}
