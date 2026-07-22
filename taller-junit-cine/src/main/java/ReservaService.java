import java.util.ArrayList;
import java.util.List;
import javax.imageio.IIOException;

public class ReservaService {
    private static final int MAX_ASIENTO_POR_COMPRA= 6;
    private SalaCine sala;
    public ReservaService(SalaCine sala){
        this.sala= sala;
    }

    public double reservaAsientos(List<String> codigosAsientos){
        if (codigosAsientos== null || codigosAsientos.isEmpty()){
            throw new IllegalArgumentException("Debe indicar al menos un asiento");
        }
        if (codigosAsientos.size() > MAX_ASIENTO_POR_COMPRA){   
            throw new IllegalArgumentException("No se puede reservar mas de " + MAX_ASIENTO_POR_COMPRA + " Asientos por comprar");
        }

        double subtotal = 0.0;
        List<Asiento> reservados = new ArrayList<>();
        for(String codigo: codigosAsientos){
            Asiento asiento = sala.buscarAsiento(codigo);
            asiento.ocupar();
            reservados.add(asiento);
            subtotal+=asiento.calcularPrecioBase();
        }
        return aplicarDescuento(subtotal, reservados.size());
    }
    private double aplicarDescuento(double subtotal, int cantidadAsientos){
        double descuento= 0.0;
        if (cantidadAsientos >= 4){
            descuento=0.15;
        } else if (cantidadAsientos>= 2 ){
            descuento=0.05;
        }
        return subtotal -(subtotal * descuento);
    }
}
