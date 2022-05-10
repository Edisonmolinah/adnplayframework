package dominio.pago.servicio;


import dominio.pago.modelo.entidad.Pago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import excepciones.YaExisteExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class ServicioCrearPago {

    private static final String EL_PAGO_YA_EXISTE_EN_EL_SISTEMA = "El pago ya existe en el sistema";

    private final RepositorioPago repositorioPago;

    @Inject
    public ServicioCrearPago(RepositorioPago repositorioPago) {
        this.repositorioPago = repositorioPago;
    }

    public CompletionStage <Long> ejecutar(Pago pago) {
        validarExistenciaPrevia(pago);
        return this.repositorioPago.crear(pago);
    }
    @SneakyThrows
    private void validarExistenciaPrevia(Pago pago) {
        Boolean existe = this.repositorioPago.existePorId(pago.getId())
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if(existe) {
            throw new YaExisteExcepcion(EL_PAGO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
