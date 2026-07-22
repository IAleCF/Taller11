
import java.util.ArrayList;
import java.util.List;

class SalaCine{
    private String nombre;
    private List<Asiento> asientos;
    private int capacidadMaxima;

    public SalaCine(String nombre, int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.asientos = new ArrayList<>();
    }

    public void agregarAsiento(Asiento asiento) {
        if (asientos.size() >= capacidadMaxima) {
            throw new IllegalStateException("La sala ha alcanzado su capacidad máxima");
        }
        for (Asiento a : asientos) {
            if (a.getCodigo().equals(asiento.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un asiento con el código " + asiento.getCodigo());
            }
        }
        asientos.add(asiento);
    }
}