import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class ReservaServiceTest {
    private SalaCine sala;
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        // simulamos la sala de cine 
        sala = new SalaCine(); 
        reservaService = new ReservaService(sala);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la lista de asientos es nula")
    void reservaAsientos_ListaNula_LanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(null);
        });
        assertEquals("Debe indicar al menos un asiento", exception.getMessage());
    }

    @Test
    @DisplayName("Debe lanzar excepción si la lista de asientos está vacía")
    void reservaAsientos_ListaVacia_LanzaExcepcion() {
        List<String> asientosVacios = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(asientosVacios);
        });
        assertEquals("Debe indicar al menos un asiento", exception.getMessage());
    }

    @Test
    @DisplayName("Debe lanzar excepción si se intentan reservar más de 6 asientos")
    void reservaAsientos_MasDeSeisAsientos_LanzaExcepcion() {
        List<String> sieteAsientos = List.of("A1", "A2", "A3", "A4", "A5", "A6", "A7");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservaAsientos(sieteAsientos);
        });
        assertTrue(exception.getMessage().contains("No se puede reservar mas de"));
    }

    @Test
    @DisplayName("Reserva de 1 asiento: No aplica descuento")
    void reservaAsientos_UnAsiento_SinDescuento() {
        List<String> unAsiento = List.of("A1");
        double total = reservaService.reservaAsientos(unAsiento);
        
        // Asumiendo que el precio base del asiento es 10.0
        assertEquals(10.0, total);
        assertFalse(total < 10.0, "El total no debe tener descuentos aplicados");
    }

    @Test
    @DisplayName("Reserva de 2 asientos: Aplica descuento del 5%")
    void reservaAsientos_DosAsientos_CincoPorcientoDescuento() {
        List<String> dosAsientos = List.of("A1", "A2");
        double total = reservaService.reservaAsientos(dosAsientos);
        
        // Subtotal = 20.0 -> Descuento 5% (1.0) -> Total = 19.0
        assertEquals(19.0, total);
        assertTrue(total > 0, "El total a pagar debe ser mayor a cero");
    }

    @Test
    @DisplayName("Reserva de 4 asientos: Aplica descuento del 15%")
    void reservaAsientos_CuatroAsientos_QuincePorcientoDescuento() {
        List<String> cuatroAsientos = List.of("A1", "A2", "A3", "A4");
        double total = reservaService.reservaAsientos(cuatroAsientos);
        
        // Subtotal = 40.0 -> Descuento 15% (6.0) -> Total = 34.0
        assertEquals(34.0, total);
        assertFalse(total == 40.0, "El total debe reflejar un descuento aplicado");
    }
 }
