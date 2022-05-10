package dominio.pago.puerto.dao;


import com.google.inject.ImplementedBy;
import dominio.pago.modelo.dto.DtoPago;
import infraestructura.pago.adaptador.dao.DaoPagoPostgreSQL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ImplementedBy(DaoPagoPostgreSQL.class)

public interface DaoPago {

    CompletionStage<List<DtoPago>> listar();
    CompletionStage<List<DtoPago>> listarPorFecha(LocalDate fecha);
    CompletionStage<Optional<DtoPago>> obtenerPorId(Long id);



    }
