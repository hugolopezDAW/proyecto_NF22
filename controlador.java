import java.util.*;
public class controlador {
    protected int lastContactId = 0;
    private List<contacto> contacts = new ArrayList<>();

    public contacto nuevoContacto(String nombre, String apellido, String telefono, String email) {
        contacto c = new contacto(++lastContactId, nombre, apellido, telefono, email);
        contacts.add(c);
        return c;
    }
    public contacto actualizarContacto(int id, String nombre, String apellido, String telefono, String email){
    contacto c = buscarContactoPorId(id);
        if (c != null) {
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);
            c.setEmail(email);
        }
        return c;
    }
    public void borrarContacto(int id) {
        contacts.removeIf(c->c.getId()==id);
    }
    public contacto buscarContactoPorId(int id){
        for (contacto c : contacts) {
            if (c.getId() == id) return c;

        }
        return null;
    }
    public List<contacto> buscarContactoPorNombre(String nombre) {
        return contacts.stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre)).toList();
    }
    public List<contacto> buscarContactoPorApellido(String apellido) {
        return contacts.stream().filter(c -> c.getApellido().equalsIgnoreCase(apellido)).toList();
    }
    public List<contacto> buscarContactoPorTelefono(String telefono) {
        return contacts.stream().filter(c -> c.getTelefono().equalsIgnoreCase(telefono)).toList();
    }
    public List<contacto> buscarContactoPorEmail(String email) {
        return contacts.stream().filter(c -> c.getEmail().equalsIgnoreCase(email)).toList();
    }
}
