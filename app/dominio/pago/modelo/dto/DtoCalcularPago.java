package dominio.pago.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoCalcularPago {

    private Integer tipoVehiculo;
    private Float valorAPagar;



}
