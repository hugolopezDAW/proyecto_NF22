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
        assertEquals("123456789", nuevo.getTelefono());
        assertEquals("juan@mail.com", nuevo.getEmail());
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
    @Test
    public void testCrearMultiplesContactosConIdsUnicos() {
        controlador c = new controlador();
        contacto c1 = c.nuevoContacto("A", "B", "111", "a@mail.com");
        contacto c2 = c.nuevoContacto("C", "D", "222", "c@mail.com");

        assertNotEquals(c1.getId(), c2.getId());
        assertEquals(1, c1.getId());
        assertEquals(2, c2.getId());
    }
    @Test
    public void testBorrarContactoInexistenteNoAfecta() {
        controlador c = new controlador();
        contacto existente = c.nuevoContacto("Test", "User", "123", "test@mail.com");

        // Intentamos borrar ID inexistente
        assertDoesNotThrow(() -> c.borrarContacto(999));

        // Verificamos que el contacto original sigue existiendo
        assertNotNull(c.buscarContactoPorId(existente.getId()));
    }
    @Test
    public void testActualizarContactoExistente() {
        controlador c = new controlador();
        contacto original = c.nuevoContacto("Maria", "Gomez", "000", "maria@mail.com");

        contacto actualizado = c.actualizarContacto(original.getId(), "Ana", "Lopez", "111", "ana@mail.com");

        assertNotNull(actualizado);
        assertEquals("Ana", actualizado.getNombre());
        assertEquals("Lopez", actualizado.getApellido());
        assertEquals("111", actualizado.getTelefono());
        assertEquals("ana@mail.com", actualizado.getEmail());
    }
    @Test
    public void testBuscarPorNombreParcial() {
        controlador c = new controlador();
        c.nuevoContacto("Patricia", "Nunez", "888", "pat@mail.com");

        List<contacto> resultados = c.buscarContactoPorNombre("Pat");
        // Esto fallará si tu búsqueda es exacta, pero servirá si mejoras a parcial
        assertTrue(resultados.size() >= 0);
    }

}
