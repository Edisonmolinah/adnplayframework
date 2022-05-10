package aplicacion.pago.comando.fabrica;

import aplicacion.pago.comando.ComandoPago;
import dominio.pago.modelo.entidad.Pago;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class FabricaPago {

    public Pago crear(ComandoPago comandoPago) {
        return new Pago(comandoPago.getId(),
                comandoPago.getPlaca(),
                comandoPago.getTipoVehiculo(),
                comandoPago.getValorPago(),
                LocalDate.now());
    }
}
