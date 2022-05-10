package dominio.pago.servicio;

import dominio.pago.modelo.dto.DtoPago;
import dominio.pago.puerto.dao.DaoPago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class ServicioObtenerPorId {

    private static final String EL_PAGO_NO_EXISTE = "El pago no existe en el sistema";

    private final DaoPago daoPago;
    private final RepositorioPago repositorioPago;

    @Inject
    public ServicioObtenerPorId(DaoPago daoPago, RepositorioPago repositorioPago) {
        this.daoPago = daoPago;
        this.repositorioPago = repositorioPago;
    }


    public CompletionStage<Optional<DtoPago>> ejecutar(Long id){
        validarExistenciaPrevia(id);
        return this.daoPago.obtenerPorId(id);
    }

    @SneakyThrows
    private void validarExistenciaPrevia(Long id) {
        Boolean existe = this.repositorioPago.existePorId(id)
                .thenApplyAsync(r -> {
                    return r;
                }).toCompletableFuture().get();
        if (!existe){
            throw new NoSeEncuentraExcepcion(EL_PAGO_NO_EXISTE);
        }
    }

}
