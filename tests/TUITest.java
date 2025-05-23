import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

public class TUITest {

    @Test
    public void testMostrarMenuPrincipalSeleccion() {
        String input = "2\n";
        TUI tui = new TUI(new ByteArrayInputStream(input.getBytes()));

        int opcion = tui.mostrarMenuPrincipal();
        assertEquals(2, opcion);
    }

    @Test
    public void testMostrarMenuBusquedaSeleccion() {
        String input = "3\n";
        TUI tui = new TUI(new ByteArrayInputStream(input.getBytes()));

        int opcion = tui.mostrarMenuBusqueda();
        assertEquals(3, opcion);
    }

    @Test
    public void testPedirDatosNuevoContacto() {
        String input = "\nJuan\nPerez\n123456789\njuan@mail.com\n";
        TUI tui = new TUI(new ByteArrayInputStream(input.getBytes()));

        String[] datos = tui.pedirDatosNuevoContacto();
        assertArrayEquals(new String[] {"Juan", "Perez", "123456789", "juan@mail.com"}, datos);
    }

    @Test
    public void testPedirDatosActualizarContacto() {
        String input = "NuevoNombre\nNuevoApellido\n999888777\nnuevo@email.com\n";
        TUI tui = new TUI(new ByteArrayInputStream(input.getBytes()));

        String[] datos = tui.pedirDatosActualizarContacto();
        assertEquals("NuevoApellido", datos[1]);
        assertEquals("999888777", datos[2]);
    }

    @Test
    public void testPedirIdBorrarContacto() {
        String input = "7\n";
        TUI tui = new TUI(new ByteArrayInputStream(input.getBytes()));

        int id = tui.pedirIdBorrarContacto();
        assertEquals(7, id);
    }
}
