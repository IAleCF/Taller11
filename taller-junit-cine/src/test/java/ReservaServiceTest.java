import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {
    private SalaCine sala;
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        sala = new SalaCine("Sala Principal", 10); 
        reservaService = new ReservaService(sala);
        sala.agregarAsiento(new Asiento("A1", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A2", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A3", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A4", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A5", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A6", "ESTANDAR"));
        sala.agregarAsiento(new Asiento("A7", "ESTANDAR"));
    }

    // ** CASOS VALIDOS **
    // casos de prueba para verificar el comportamiento correcto del metodo reservaAsientos
    @Test
    @DisplayName("Reserva de 2 asientos y se Aplica descuento del 5%")
    void reservaAsientosDosAsientos() {
        List<String> dosAsientos = List.of("A1", "A2");
        double total = reservaService.reservaAsientos(dosAsientos);
        assertEquals(9.5, total);
        assertTrue(total > 0, "El total a pagar debe ser mayor a cero");
    }
    @Test
    @DisplayName("Reserva de 4 asientos: Aplica descuento del 15%")
    void reservaAsientos_CuatroAsientos_QuincePorcientoDescuento() {
        List<String> cuatroAsientos = List.of("A1", "A2", "A3", "A4");
        double total = reservaService.reservaAsientos(cuatroAsientos);
        assertEquals(17.0, total);
        assertFalse(total == 20.0, "El total debe reflejar un descuento aplicado");
    }


    // ** CASOS ERROR **
    // casos de prueba para verificar que se lanzan excepciones en situaciones invalidas
    @Test
    @DisplayName("Debe lanzar excepcion si la lista de asientos es nula")
    void reservaAsientos_ListaNula() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(null);
        });
        assertEquals("Debe indicar al menos un asiento", exception.getMessage());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si la lista de asientos esta vacia")
    void reservaAsientos_ListaVacia() {
        List<String> asientosVacios = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(asientosVacios);
        });
        assertEquals("Debe indicar al menos un asiento", exception.getMessage());
    }

    // Caso de que si es mayor al liminte de asientos por compra, se lanza una excepcion
    @Test
    @DisplayName("Debe lanzar excepcion si se intentan reservar mas de 6 asientos")
    void reservaAsientos_MasDeSeisAsientos() {
        List<String> sieteAsientos = List.of("A1", "A2", "A3", "A4", "A5", "A6", "A7");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(sieteAsientos);
        });
        assertTrue(exception.getMessage().contains("No se puede reservar mas de"));
    }


    // CASOS LIMITES
    // casos de prueba para verificar el comportamiento en los limites del metodo reservaAsientos
    @Test
    @DisplayName("Reserva de 1 asiento: No aplica descuento")
    void reservaAsientos_UnAsiento_SinDescuento() {
        List<String> unAsiento = List.of("A1");
        double total = reservaService.reservaAsientos(unAsiento);
        
        // Asumiendo que el precio base del asiento es 5.0
        assertEquals(5.0, total);
        assertFalse(total < 5.0, "El total no debe tener descuentos aplicados");
    }

    @Test
    @DisplayName("Limite: Reservar 4 asientos (Frontera inferior para 15% descuento)")
    void reservaAsientos_CuartoAsientos() {
        List<String> cuatroAsientos = List.of("A1", "A2", "A3", "A4");
        double total = reservaService.reservaAsientos(cuatroAsientos);
        assertEquals(17.0, total);
    }
 }
