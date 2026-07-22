import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {
    Asiento asiento;

    @BeforeEach
    void setUp(){
        asiento=new Asiento("A1","VIP");
    }

    @Test
    @DisplayName("Constructor crea asiento válido")
    void constructorValido(){
        assertFalse(asiento.isOcupado());
        assertEquals("A1",asiento.getCodigo());
    }

    @Test
    @DisplayName("Caso límite, cadena de longitud cero")
    void constructorLimite() {
        IllegalArgumentException errorLimite = assertThrows(IllegalArgumentException.class, () -> new Asiento("", ""), "Se esperaba un error al no insertar texto");
        assertEquals("Error", errorLimite.getMessage());
    }

    @Test
    @DisplayName("Caso error, tipo de asiento inválido")
    void constructorError() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Asiento("A2", "EstANd4r"), "Se espera un error por ingresar mal el tipo de asiento");
        assertEquals("Error", error.getMessage());
    }

    



    // TODO: Traducir los demás casos de su tabla a métodos @Test.
}
