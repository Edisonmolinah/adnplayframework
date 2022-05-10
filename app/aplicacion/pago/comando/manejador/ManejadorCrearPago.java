package aplicacion.pago.comando.manejador;



import aplicacion.pago.comando.ComandoPago;
import aplicacion.pago.comando.fabrica.FabricaPago;
import dominio.pago.modelo.dto.DtoPagoResponse;
import dominio.pago.modelo.entidad.Pago;
import dominio.pago.servicio.ServicioCrearPago;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class ManejadorCrearPago  {

    private final FabricaPago fabricaPago;
    private final ServicioCrearPago servicioCrearPago;
    @Inject
    public ManejadorCrearPago(FabricaPago fabricaPago, ServicioCrearPago servicioCrearPago) {
        this.fabricaPago = fabricaPago;
        this.servicioCrearPago = servicioCrearPago;
    }

    public CompletionStage<Long>ejecutar(ComandoPago comandoPago){
        Pago pago = this.fabricaPago.crear(comandoPago);
        return this.servicioCrearPago.ejecutar(pago);
    }

    /*public DtoPagoResponse ejecutar(ComandoPago comandoPago) {
        Pago pago = this.fabricaPago.crear(comandoPago);
        Long id = this.servicioCrearPago.ejecutar(pago);
        return new DtoPagoResponse(id, pago.getPlaca(), pago.getValorPago());
    }*/
}
