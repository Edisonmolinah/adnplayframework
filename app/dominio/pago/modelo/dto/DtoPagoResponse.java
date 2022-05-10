package dominio.pago.modelo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DtoPagoResponse {

    private Long id;
    private String placa;
    private Float valorPago;
    private String mensaje;


    public DtoPagoResponse(Long id, String placa, Float valorPago){

        this.id = id;
        this.placa = placa;
        this.valorPago = valorPago;
        this.mensaje = "El pago ha sido exitoso";

    }

}
