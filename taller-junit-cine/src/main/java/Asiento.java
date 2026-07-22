public class Asiento {
    private String codigo;
    private String tipo;
    private boolean ocupado;

    public Asiento(String codigo, String tipo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del asiento no puede estar vacío");
        }
        if (!tipo.equals("ESTANDAR") && !tipo.equals("VIP") && !tipo.equals("4D")) {
            
            throw new IllegalArgumentException("Tipo de asiento inválido: " + tipo);
        }
        this.codigo = codigo;
        this.tipo = tipo;
        this.ocupado = false;
    }

    public double calcularPrecioBase() {
        switch (tipo) {
            case "ESTANDAR":
                return 5.0;
            case "VIP":
                return 8.5;
            case "4D":
                return 12.0;
            default:
                return 0.0;
        }
    }

    public void ocupar() {
        if (ocupado) {
            throw new IllegalStateException("El asiento " + codigo + " ya está ocupado");
        }
        ocupado = true;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    //METODOS ADICIONALES PARA TESTEAR
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
