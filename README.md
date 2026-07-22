# Taller11
# Casos de Prueba - Sistema de Reservas de Asientos

| Clase | ID | Método | Datos de entrada | Salida esperada | Propósito |
| :--- | :---: | :--- | :--- | :--- | :--- |
| **Asiento** | **CP-01** | Constructor | `codigo="A1", tipo="VIP"` | Objeto creado, `ocupado=false` | Caso válido |
| **Asiento** | **CP-02** | Constructor | `codigo="  ", tipo="ESTANDAR"` | `IllegalArgumentException("El código...")` | Caso error |
| **Asiento** | **CP-03** | Constructor | `codigo="B2", tipo="PREMIUM"` | `IllegalArgumentException("Tipo de asiento inválido...")` | Caso error |
| **Asiento** | **CP-04** | `calcularPrecioBase()` | Asiento con `tipo="ESTANDAR"` | `5.0` | Caso válido |
| **Asiento** | **CP-05** | `calcularPrecioBase()` | Asiento con `tipo="4D"` | `12.0` | Caso límite |
| **Asiento** | **CP-06** | `ocupar()` | Asiento libre (`ocupado=false`) | Estado cambia a `ocupado=true` | Caso válido |
| **Asiento** | **CP-07** | `ocupar()` | Asiento ya ocupado (`ocupado=true`) | `IllegalStateException("El asiento ... ya está ocupado")` | Caso error |
| **SalaCine** | **CP-08** | `agregarAsiento()` | Sala(cap=50), Asiento(`"A1", "ESTANDAR"`) | Asiento añadido exitosamente a la lista | Caso válido |
| **SalaCine** | **CP-09** | `agregarAsiento()` | Sala llena (`asientos.size() == capacidadMaxima`) | `IllegalStateException("La sala ha alcanzado su capacidad máxima")` | Caso límite |
| **SalaCine** | **CP-10** | `agregarAsiento()` | Asiento con código `"A1"` duplicado | `IllegalArgumentException("Ya existe un asiento con el código A1")` | Caso error |
| **SalaCine** | **CP-11** | `buscarAsiento()` | `codigo="A1"` (existente) | Retorna la instancia del Asiento A1 | Caso válido |
| **SalaCine** | **CP-12** | `buscarAsiento()` | `codigo="Z99"` (no existe) | `NoSuchElementException("Asiento no encontrado: Z99")` | Caso error |
| **ReservaService** | **CP-13** | `reservarAsientos()` | Lista con 3 asientos ("A1", "A2", "A3") estándar ($5.0 c/u) | Subtotal $15.0 - 5% desc = **14.25** | Caso válido |
| **ReservaService** | **CP-14** | `reservarAsientos()` | Lista con 2 asientos | Aplica 5% de descuento (límite inferior descuento) | Caso límite |
| **ReservaService** | **CP-15** | `reservarAsientos()` | Lista con 4 asientos | Aplica 15% de descuento (límite inferior desc. alto) | Caso límite |
| **ReservaService** | **CP-16** | `reservarAsientos()` | Lista con 6 asientos | Reserva exitosa de 6 asientos (límite máximo) | Caso límite |
| **ReservaService** | **CP-17** | `reservarAsientos()` | Lista vacía `[]` o `null` | `IllegalArgumentException("Debe indicar al menos un asiento")` | Caso error |
| **ReservaService** | **CP-18** | `reservarAsientos()` | Lista con 7 asientos | `IllegalArgumentException("No se pueden reservar más de 6...")` | Caso error |