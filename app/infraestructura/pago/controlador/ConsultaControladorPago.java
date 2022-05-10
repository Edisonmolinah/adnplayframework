package infraestructura.pago.controlador;


import aplicacion.pago.consulta.ManejadorListarpagos;
import dominio.pago.modelo.dto.DtoPago;
import org.springframework.format.annotation.DateTimeFormat;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletionStage;


public class ConsultaControladorPago extends Controller {

    private final ManejadorListarpagos manejadorListarpagos;
    private final HttpExecutionContext ec;

    @Inject
    public ConsultaControladorPago(ManejadorListarpagos manejadorListarpagos, HttpExecutionContext ec) {
        this.manejadorListarpagos = manejadorListarpagos;
        this.ec = ec;
    }

    public CompletionStage<Result> listar(){
        return this.manejadorListarpagos.ejecutar().thenApplyAsync(resultado -> {
            final List<DtoPago> listaPagos = resultado;
            return ok(Json.toJson(listaPagos));
        }, ec.current());
    }

    public CompletionStage<Result> listarPorFecha(LocalDate fecha){
        return this.manejadorListarpagos.ejecutarFecha(fecha).thenApplyAsync(resultado -> {
            return ok(Json.toJson(resultado));
        }, ec.current());
    }



}
