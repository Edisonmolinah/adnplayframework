package dominio.pago.puerto.repositorio;

import com.google.inject.ImplementedBy;
import dominio.pago.modelo.entidad.Pago;
import infraestructura.pago.adaptador.repositorio.RepositorioPagoPostgreSQL;

import java.util.concurrent.CompletionStage;

@ImplementedBy(RepositorioPagoPostgreSQL.class)
public interface RepositorioPago {

    /**
     * Permite crear un usuario
     * @param pago
     * @return el id generado
     */
    CompletionStage<Long> crear(Pago pago);

    /**
     * Permite actualizar un usuario
     * @param pago
     */
    CompletionStage<Void> actualizar(Pago pago);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @return si existe o no
     */
    CompletionStage<Boolean> existePorId(Long id);

    /**
     * Permite validar si existe un pago ingresando una placa
     * @param placa
     * @return si existe o no
     */
    CompletionStage<Boolean> existePorPlaca(String placa);


}
