import java.io.*;
import java.util.List;

public class fileController implements controlador {
    private final File dataFolder;
    private final File contactFolder;
    private final memoryController memoryController;

    public fileController(String dataPath) {
        this.dataFolder = new File(dataPath);
        this.contactFolder = new File(dataFolder, "contacts");
        this.memoryController = new memoryController();

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
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String nombre = br.readLine();
                    String apellido = br.readLine();
                    int telefono = Integer.parseInt(br.readLine());
                    String email = br.readLine();

                    memoryController.nuevoContacto(nombre, apellido, telefono, email);
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Error cargando contacto: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        contacto c = memoryController.nuevoContacto(nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, c.getId() + ".txt");

        try (FileWriter writer = new FileWriter(contactFile)) {
            writer.write(c.getNombre() + "\n");
            writer.write(c.getApellido() + "\n");
            writer.write(c.getTelefono() + "\n");
            writer.write(c.getEmail() + "\n");
        } catch (IOException e) {
            System.err.println("Error guardando contacto: " + e.getMessage());
        }

        return c;
    }

    @Override
    public contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        contacto c = memoryController.actualizarContacto(id, nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, id + ".txt");

        if (c != null) {
            try (FileWriter writer = new FileWriter(contactFile)) {
                writer.write(c.getNombre() + "\n");
                writer.write(c.getApellido() + "\n");
                writer.write(c.getTelefono() + "\n");
                writer.write(c.getEmail() + "\n");
            } catch (IOException e) {
                System.err.println("Error actualizando contacto: " + e.getMessage());
            }
        }

        return c;
    }

    @Override
    public boolean borrarContacto(int id) {
        boolean deleted = memoryController.borrarContacto(id);
        File contactFile = new File(contactFolder, id + ".txt");

        if (contactFile.exists()) {
            deleted = contactFile.delete();
        }

        return deleted;
    }

    @Override
    public contacto buscarContactoPorId(int id) {
        return memoryController.buscarContactoPorId(id);
    }

    @Override
    public List<contacto> buscarContactoPorNombre(String nombre) {
        return memoryController.buscarContactoPorNombre(nombre);
    }

    @Override
    public List<contacto> buscarContactoPorApellido(String apellido) {
        return memoryController.buscarContactoPorApellido(apellido);
    }

    @Override
    public List<contacto> buscarContactoPorTelefono(int telefono) {
        return memoryController.buscarContactoPorTelefono(telefono);
    }

    @Override
    public List<contacto> buscarContactoPorEmail(String email) {
        return memoryController.buscarContactoPorEmail(email);
    }
}

/*import java.io.*;

public class fileController implements controlador {
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
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String nombre = br.readLine();
                    String apellido = br.readLine();
                    int telefono = Integer.parseInt(br.readLine());
                    String email = br.readLine();

                    super.nuevoContacto(nombre, apellido, telefono, email);
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Error cargando contacto: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public contacto nuevoContacto(String nombre, String apellido, int telefono, String email) {
        contacto c = super.nuevoContacto(nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, c.getId() + ".txt");

        try (FileWriter writer = new FileWriter(contactFile)) {
            writer.write(c.getNombre() + "\n");
            writer.write(c.getApellido() + "\n");
            writer.write(c.getTelefono() + "\n");
            writer.write(c.getEmail() + "\n");
        } catch (IOException e) {
            System.err.println("Error guardando contacto: " + e.getMessage());
        }

        return c;
    }

    @Override
    public contacto actualizarContacto(int id, String nombre, String apellido, int telefono, String email) {
        contacto c = super.actualizarContacto(id, nombre, apellido, telefono, email);
        File contactFile = new File(contactFolder, id + ".txt");

        try (FileWriter writer = new FileWriter(contactFile)) {
            writer.write(c.getNombre() + "\n");
            writer.write(c.getApellido() + "\n");
            writer.write(c.getTelefono() + "\n");
            writer.write(c.getEmail() + "\n");
        } catch (IOException e) {
            System.err.println("Error actualizando contacto: " + e.getMessage());
        }

        return c;
    }

    @Override
    public boolean borrarContacto(int id) {
        boolean deleted = super.borrarContacto(id);
        File contactFile = new File(contactFolder, id + ".txt");
        if (contactFile.exists()) {
            deleted = contactFile.delete();
        }
        return deleted;
    }
}*/


