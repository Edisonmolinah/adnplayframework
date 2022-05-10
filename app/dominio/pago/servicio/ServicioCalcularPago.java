package dominio.pago.servicio;


import dominio.pago.modelo.dto.DtoCalcularPago;
import dominio.pago.modelo.dto.DtoPago;

import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ServicioCalcularPago {
/*
    private static final String EL_TIPO_DE_VEHICULO_ES_INCORRECTO = "El tipo de vehiculo es incorrecto";

    public DtoCalcularPago ejecutar(Integer tipoVehiculo ) {
        calcularValorPago(tipoVehiculo);
        DtoPago dtoPago = new DtoPago();
        dtoPago.setTipoVehiculo(dtoPago.getTipoVehiculo());
        dtoPago.setFechaPago(dtoPago.getFechaPago());

        return new DtoCalcularPago(tipoVehiculo, calcularValorPago(tipoVehiculo));
    }

    @Inject
    private Float calcularValorPago(Integer tipoVehiculo) {

        float tarifaVehiculo = 0;
        LocalDate fechaCobro = LocalDate.now();
        switch (tipoVehiculo) {
            case 1:
                tarifaVehiculo = 8000;
                break;
            case 2:
                tarifaVehiculo = 12000;
                break;
            case 3:
                tarifaVehiculo = 18000;
                break;

            *//*default:
                throw new ExcepcionValorInvalido(EL_TIPO_DE_VEHICULO_ES_INCORRECTO);*//*
        }

        if (fechaCobro.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue()
                || fechaCobro.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue()) {

            tarifaVehiculo = tarifaVehiculo * 1.15f;
        }
        return tarifaVehiculo;
    }*/
}
