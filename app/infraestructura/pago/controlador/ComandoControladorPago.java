package infraestructura.pago.controlador;


import aplicacion.pago.comando.ComandoPago;
import aplicacion.pago.comando.manejador.ManejadorActualizarPago;
import aplicacion.pago.comando.manejador.ManejadorCalcularPago;
import aplicacion.pago.comando.manejador.ManejadorCrearPago;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class ComandoControladorPago extends Controller {

    private final ManejadorCrearPago manejadorCrearPago;
    private final ManejadorActualizarPago manejadorActualizarPago;
    private final ManejadorCalcularPago manejadorCalcularPago;
    private final HttpExecutionContext ec;

    @Inject
    public ComandoControladorPago(ManejadorCrearPago manejadorCrearPago,
                                  ManejadorActualizarPago manejadorActualizarPago, ManejadorCalcularPago manejadorCalcularPago, HttpExecutionContext ec) {
        this.manejadorCrearPago = manejadorCrearPago;
        this.manejadorActualizarPago = manejadorActualizarPago;
        this.manejadorCalcularPago = manejadorCalcularPago;
        this.ec = ec;
    }
    public CompletionStage<Result> insertar(Http.Request request){
        JsonNode json = request.body().asJson();
        final ComandoPago pago = Json.fromJson(json, ComandoPago.class);
        return this.manejadorCrearPago.ejecutar(pago).thenApplyAsync(datos -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("id", datos);
            return created(Json.toJson(respuesta));
        }, ec.current());
    }

    public CompletionStage<Result> actualizar(Http.Request request, String id){
        JsonNode json = request.body().asJson();
        final ComandoPago pago = Json.fromJson(json, ComandoPago.class);
        return this.manejadorActualizarPago.ejecutar(pago, id).thenApplyAsync(res -> {
            return ok();
        }, ec.current());
    }

    /*@PostMapping
    @ApiOperation("Crear Pago")
    public DtoPagoResponse crear(@RequestBody ComandoPago comandoPago) {
         return manejadorCrearPago.ejecutar(comandoPago);
    }

    @GetMapping("/calcular_pago/{tipoVehiculo}")
    @ApiOperation("Calcular Pago")
    public DtoCalcularPago calcularPago(@PathVariable Integer tipoVehiculo) {
        return manejadorCalcularPago.ejecutar(tipoVehiculo);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar Pago")
    public DtoPagoResponse actualizar(@RequestBody ComandoPago comandoPago,@PathVariable Long id) {
        comandoPago.setId(id);
       return manejadorActualizarPago.ejecutar(comandoPago);
    }*/
}
