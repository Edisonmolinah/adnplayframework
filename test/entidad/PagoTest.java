package entidad;

import dominio.pago.modelo.entidad.Pago;
import org.junit.Test;
import servicio.testdatabuilder.PagoTestDataBuilder;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class PagoTest {

    @Test
    public void deberiaCrearCorrectamenteElPago(){
        LocalDate fechaCreacion = LocalDate.now();

        Pago pago = new PagoTestDataBuilder().conFechaPago(fechaCreacion).conId(1L).build();


        assertEquals("1", pago.getId().toString());
        assertEquals("abc123", pago.getPlaca());
        assertEquals("1", pago.getTipoVehiculo().toString());
        assertEquals("8000.0",pago.getValorPago().toString());
        assertEquals(fechaCreacion, pago.getFechaPago());
    }
}
