import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ControladorTest {

    @Test
    public void testCrearContactoCorrectamente() {
        controlador c = new controlador();
        contacto nuevo = c.nuevoContacto("Juan", "Perez", "123456789", "juan@mail.com");

        assertNotNull(nuevo);
        assertEquals(1, nuevo.getId());
        assertEquals("Juan", nuevo.getNombre());
        assertEquals("Perez", nuevo.getApellido());
    }

    @Test
    public void testBuscarContactoPorNombreInexistente() {
        controlador c = new controlador();
        c.nuevoContacto("Ana", "Lopez", "111222333", "ana@mail.com");

        List<contacto> resultados = c.buscarContactoPorNombre("Pedro");
        assertTrue(resultados.isEmpty());
    }

    @Test
    public void testActualizarContactoInexistente() {
        controlador c = new controlador();
        contacto actualizado = c.actualizarContacto(999, "X", "Y", "000", "none");

        assertNull(actualizado);
    }

    @Test
    public void testBorrarContactoYVerificarEliminacion() {
        controlador c = new controlador();
        contacto nuevo = c.nuevoContacto("Carlos", "Diaz", "444555666", "carlos@mail.com");

        c.borrarContacto(nuevo.getId());
        assertNull(c.buscarContactoPorId(nuevo.getId()));
    }

    @org.junit.Test
    @Test
    public void testBuscarPorDistintosCampos() {
        controlador c = new controlador();
        contacto nuevo = c.nuevoContacto("Laura", "Martinez", "987654321", "laura@mail.com");

        assertEquals(nuevo, c.buscarContactoPorId(nuevo.getId()));
        assertEquals(1, c.buscarContactoPorNombre("Laura").size());
        assertEquals(1, c.buscarContactoPorApellido("Martinez").size());
        assertEquals(1, c.buscarContactoPorTelefono("987654321").size());
        assertEquals(1, c.buscarContactoPorEmail("laura@mail.com").size());
    }

    @Test
    public void testCaseInsensitiveBusqueda() {
        controlador c = new controlador();
        c.nuevoContacto("José", "Rodriguez", "321", "jose@mail.com");

        assertEquals(1, c.buscarContactoPorNombre("josé").size());
        assertEquals(1, c.buscarContactoPorApellido("roDRIGUEZ").size());
    }
}
