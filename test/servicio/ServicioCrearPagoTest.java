package servicio;

import dominio.pago.modelo.entidad.Pago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import dominio.pago.servicio.ServicioCrearPago;
import excepciones.YaExisteExcepcion;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.Mockito;
import servicio.testdatabuilder.PagoTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ServicioCrearPagoTest {

    @SneakyThrows
    @Test
    public void deberiaCrearElPagoDeManeraCorrecta() {
        Pago pago = new PagoTestDataBuilder().build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existePorId(Mockito.any())).thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(repositorioPago.crear(pago)).thenReturn(CompletableFuture.completedFuture(10L));

        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        Long idPago = servicioCrearPago.ejecutar(pago).toCompletableFuture().get();

        assertEquals("10", idPago.toString());
        Mockito.verify(repositorioPago, Mockito.times(1)).crear(pago);
    }

    @Test
    public void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelJugador() {
        Pago pago = new PagoTestDataBuilder().build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existePorId(Mockito.any())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        assertThrows(YaExisteExcepcion.class, () -> servicioCrearPago.ejecutar(pago));
    }
}
