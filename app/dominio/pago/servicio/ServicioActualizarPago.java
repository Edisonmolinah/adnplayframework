package dominio.pago.servicio;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import dominio.pago.modelo.entidad.Pago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import java.util.concurrent.CompletionStage;

public class ServicioActualizarPago {

    private static final String EL_PAGO_NO_EXISTE_EN_EL_SISTEMA = "El no pago existe en el sistema";

    private final RepositorioPago repositorioPago;

    @Inject
    public ServicioActualizarPago(RepositorioPago repositorioPago) {
        this.repositorioPago = repositorioPago;
    }


    public CompletionStage<Void> ejecutar(Pago pago) {
        validarExistenciaPrevia(pago);
        return this.repositorioPago.actualizar(pago);
    }

   @SneakyThrows
    private void validarExistenciaPrevia(Pago pago) {
        Boolean existe = this.repositorioPago.existePorPlaca(pago.getPlaca())
        .thenApplyAsync(r -> {
            return r;
        }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_PAGO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}





