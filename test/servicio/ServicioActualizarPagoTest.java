package servicio;

import dominio.pago.modelo.entidad.Pago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import dominio.pago.servicio.ServicioActualizarPago;
import excepciones.NoSeEncuentraExcepcion;
import org.junit.Test;
import org.mockito.Mockito;
import servicio.testdatabuilder.PagoTestDataBuilder;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertThrows;

public class ServicioActualizarPagoTest {

    @Test
    public void deberiaValidarLaExistenciaPreviaDelPago(){
        Pago pago = new PagoTestDataBuilder().conId(1L).build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existePorPlaca(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(false));
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        assertThrows(NoSeEncuentraExcepcion.class, () -> servicioActualizarPago.ejecutar(pago));
    }

    @Test
    public void deberiaActualizarCorrectamenteEnElRepositorio() {
        Pago pago = new PagoTestDataBuilder().conId(1L).build();
        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Mockito.when(repositorioPago.existePorPlaca(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(true));
        ServicioActualizarPago servicioActualizarPago = new ServicioActualizarPago(repositorioPago);

        servicioActualizarPago.ejecutar(pago);

        Mockito.verify(repositorioPago, Mockito.times(1)).actualizar(pago);
    }
}
