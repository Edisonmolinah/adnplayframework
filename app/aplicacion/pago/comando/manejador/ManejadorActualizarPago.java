
package aplicacion.pago.comando.manejador;


import aplicacion.pago.comando.ComandoPago;
import aplicacion.pago.comando.fabrica.FabricaPago;
import dominio.pago.modelo.entidad.Pago;
import dominio.pago.servicio.ServicioActualizarPago;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class ManejadorActualizarPago {

        private final FabricaPago fabricaPago;
        private final ServicioActualizarPago servicioActualizarPago;

   @Inject
    public ManejadorActualizarPago(FabricaPago fabricaPago, ServicioActualizarPago servicioActualizarPago) {
        this.fabricaPago = fabricaPago;
        this.servicioActualizarPago = servicioActualizarPago;
    }

    public CompletionStage<Void> ejecutar(ComandoPago comandoPago, String id){
       Pago pago = this.fabricaPago.crear(comandoPago);
       pago.setId(Long.parseLong(id));
       return this.servicioActualizarPago.ejecutar(pago);


    }

}

