package aplicacion.pago.comando;


import java.time.LocalDateTime;


public class ComandoPago {

        private Long id;
        private String placa;
        private Integer tipoVehiculo;
        private Float valorPago;
        private LocalDateTime fecha;

        public ComandoPago(Long id, String placa, Integer tipoVehiculo, Float valorPago, LocalDateTime fecha) {
                this.id = id;
                this.placa = placa;
                this.tipoVehiculo = tipoVehiculo;
                this.valorPago = valorPago;
                this.fecha = fecha;
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

        public LocalDateTime getFecha() {
                return fecha;
        }

        public void setFecha(LocalDateTime fecha) {
                this.fecha = fecha;
        }
}
