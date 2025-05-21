import java.util.*;
import java.util.stream.Collectors;

public class controlador {
    protected int lastContactId = 0;
    private Map<Integer, contacto> contacts = new HashMap<>();

    public contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        contacto c = new contacto(++lastContactId, nombre, apellido, telefono, email);
        contacts.put(c.getId(), c);
        return c;
    }

    public contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        contacto c = contacts.get(id);
        if (c != null) {
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);
            c.setEmail(email);
        }
        return c;
    }

    public boolean borrarContacto(int id) {
        return contacts.remove(id) != null;
    }

    public contacto buscarContactoPorId(int id) {
        return contacts.get(id);
    }

    public List<contacto> buscarContactoPorNombre(String nombre) {
        return contacts.values().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    public List<contacto> buscarContactoPorApellido(String apellido) {
        return contacts.values().stream()
                .filter(c -> c.getApellido().equalsIgnoreCase(apellido))
                .collect(Collectors.toList());
    }

    public List<contacto> buscarContactoPorTelefono(int telefono) {
        return contacts.values().stream()
                .filter(c -> c.getTelefono() == telefono)
                .collect(Collectors.toList());
    }

    public List<contacto> buscarContactoPorEmail(String email) {
        return contacts.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }
}

