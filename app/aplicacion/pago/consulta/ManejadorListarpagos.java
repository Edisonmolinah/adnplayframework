package aplicacion.pago.consulta;


import dominio.pago.modelo.dto.DtoPago;
import dominio.pago.puerto.dao.DaoPago;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletionStage;


public class ManejadorListarpagos {

    private final DaoPago daoPago;
    @Inject
    public ManejadorListarpagos(DaoPago daoPago){
        this.daoPago = daoPago;
    }
    public CompletionStage<List<DtoPago>> ejecutarFecha(LocalDate fecha){
        return this.daoPago.listarPorFecha(fecha);
    }
    public CompletionStage<List<DtoPago>> ejecutar(){ return this.daoPago.listar(); }
}
