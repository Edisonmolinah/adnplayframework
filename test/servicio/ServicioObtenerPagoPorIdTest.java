package servicio;


import dominio.pago.modelo.dto.DtoPago;
import dominio.pago.puerto.dao.DaoPago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import dominio.pago.servicio.ServicioObtenerPorId;
import excepciones.NoSeEncuentraExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import servicio.testdatabuilder.PagoTestDataBuilder;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ServicioObtenerPagoPorIdTest {

    @Test
    public void deberiaLanzarUnaExepcionCuandoNoExistaElPago() {
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        DaoPago daoPago= Mockito.mock(DaoPago.class);
        Mockito.when(repositorioPago.existePorId(Mockito.any())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioObtenerPorId servicioObtenerPorId = new ServicioObtenerPorId(daoPago, repositorioPago);

        assertThrows(NoSeEncuentraExcepcion.class, () -> servicioObtenerPorId.ejecutar(1L));
    }

    @SneakyThrows
    @Test
    public void deberiaObtenerUnPagoPorId(){
        Long id = 1L;
        DtoPago dtoPago = new PagoTestDataBuilder().dtoPago();
        Optional<DtoPago> optional = Optional.of(dtoPago);

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        DaoPago daoPago = Mockito.mock(DaoPago.class);

        Mockito.when(repositorioPago.existePorId(id)).thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(daoPago.obtenerPorId(id)).thenReturn(CompletableFuture.completedFuture(optional));

        ServicioObtenerPorId servicioObtenerPorId = new ServicioObtenerPorId(daoPago, repositorioPago);
        Optional<DtoPago> retornoPagoDto = servicioObtenerPorId.ejecutar(id)
                .toCompletableFuture().get();

        assertEquals(retornoPagoDto, optional);
    }
}
