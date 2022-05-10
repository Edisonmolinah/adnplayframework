package dominio.pago.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;*/


public class Pago {

    private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa de 6 caracteres";
    private static final String LA_PLACA_DEBE_TENER_UNA_LONGITUD_IGUAL_A = "La placa debe tener una longitud igual a %s";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_VEHICULO = "El tipo de vehiculo debe ser entre 1 - 3";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_PAGO = "Se debe ingresar la fecha de Pago";

    private static final int LONGITUD_PLACA = 6;

    private Long id;
    private String placa;
    private Integer tipoVehiculo;
    private Float valorPago;
    private LocalDate fechaPago;

    public Pago(Long id,String placa, Integer tipoVehiculo, Float valorPago, LocalDate fechaPago) {
        /*validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA);
        validarLongitud(placa, LONGITUD_PLACA, String.format(LA_PLACA_DEBE_TENER_UNA_LONGITUD_IGUAL_A,LONGITUD_PLACA));
        validarObligatorio(tipoVehiculo, SE_DEBE_INGRESAR_EL_TIPO_VEHICULO);
        validarObligatorio(fechaPago, SE_DEBE_INGRESAR_LA_FECHA_PAGO);*/

        this.id = id;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.valorPago = valorPago;
        this.fechaPago = fechaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
}
