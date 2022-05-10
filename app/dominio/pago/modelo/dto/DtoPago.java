
package dominio.pago.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoPago {
    private Long id;
    private String placa;
    private Integer tipoVehiculo;
    private Float valorPago;
    private LocalDate fechaPago;

}
