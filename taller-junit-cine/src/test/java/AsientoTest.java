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
        assertEquals("El código del asiento no puede estar vacío", errorLimite.getMessage());
    }

    @Test
    @DisplayName("Caso error, tipo de asiento inválido")
    void constructorError() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Asiento("A2", "EstANd4r"), "Se espera un error por ingresar mal el tipo de asiento");
        assertEquals("Tipo de asiento inválido: [tipo]", error.getMessage());
    }

    @Test
    @DisplayName("Caso válido")
    void calcularValido() {
        asiento=new Asiento("A3","4D");
        assertEquals(12.0, asiento.calcularPrecioBase(), "Se obtiene el precio del 4D");
    }

    @Test
    @DisplayName("Caso limite/error")
    void calcularError() {
        asiento.setTipo("5D");
        assertEquals(0.0, asiento.calcularPrecioBase(), "En el caso que no existe el tipo se eligirá el caso DEFAULT");
    }

    @Test
    @DisplayName("Caso válido")
    void ocuparValido() {
        asiento = new Asiento("A6", "ESTANDAR");
        assertEquals("", asiento.ocupar(), "se ocupa este asiento");
    }





    // TODO: Traducir los demás casos de su tabla a métodos @Test.
}
