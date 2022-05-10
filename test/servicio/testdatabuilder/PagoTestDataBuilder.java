package servicio.testdatabuilder;


import dominio.pago.modelo.dto.DtoPago;
import dominio.pago.modelo.entidad.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagoTestDataBuilder {

    private Long id;
    private String placa;
    private Integer tipoVehiculo;
    private float valorPago;
    private LocalDate fechaPago;

    public PagoTestDataBuilder() {

        id = 1l;
        placa = "abc123";
        tipoVehiculo = 1;
        valorPago = 8000;
        fechaPago = LocalDate.now();
    }

    public PagoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }


    public PagoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public PagoTestDataBuilder conTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public PagoTestDataBuilder conValorPago(float ValorPago) {
        this.valorPago = valorPago;
        return this;
    }

    public PagoTestDataBuilder conFechaPago(LocalDate fechaCreacion) {
        this.fechaPago = fechaCreacion;
        return this;
    }
    /*public Pago build(String placa, Integer tipoVehiculo, float valorPago, LocalDate fechaPago) {
        return new Pago(id,placa, tipoVehiculo, valorPago,fechaPago);*/


    public DtoPago dtoPago(){
        return new DtoPago(id, placa, tipoVehiculo, valorPago, fechaPago);
    }
    public Pago build() {
        return new Pago(id,placa, tipoVehiculo, valorPago,fechaPago);
    }
}
