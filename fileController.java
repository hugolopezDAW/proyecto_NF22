import java.io.*;

public class fileController extends controlador {
    private File dataFolder;
    private File contactFolder;

    public fileController(String dataPath) {
        this.dataFolder = new File(dataPath);
        this.contactFolder = new File(dataFolder, "contacts");

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        if (!contactFolder.exists()) {
            contactFolder.mkdirs();
        }

        cargarContactes();
    }

    private void cargarContactes() {
        File[] files = contactFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    contacto c = (contacto) ois.readObject();
                    super.nuevoContacto(c.getNombre(), c.getApellido(), c.getTelefono(), c.getEmail());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error cargando contacto: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        contacto c = super.nuevoContacto(nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, c.getId() + ".dat");

        return c;
    }


    @Override
    public contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        contacto c = super.actualizarContacto(id, nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, c.getId() + ".dat");
        return c;
    }
    @Override
    public boolean borrarContacto(int id) {
        boolean deleted = super.borrarContacto(id);
        File contactFile = new File(contactFolder, id + ".dat");
        if (contactFile.exists()) {
            deleted = contactFile.delete();
        }
        return deleted;
    }
}


