package infraestructura.pago.adaptador.dao;

import com.typesafe.config.Config;
import dominio.pago.modelo.dto.DtoPago;
import dominio.pago.puerto.dao.DaoPago;

import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class DaoPagoPostgreSQL implements DaoPago {

    private Database db;
    private Config config;
    private DatabaseExecutionContext executionContext;

    @Inject
    public DaoPagoPostgreSQL(Database db, Config config, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.config = config;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<DtoPago>> listar() {
        List<DtoPago> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.pago";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            //System.out.println("Mira: " + rs.getString("nombre"));
                            list.add(new MapeoPago().mapRow(rs));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return list;
                }, executionContext
        );
    }

    @Override
    public CompletionStage<List<DtoPago>> listarPorFecha(LocalDate fecha) {
        return null;
    }



    public CompletionStage<Optional<DtoPago>> obtenerPorId(Long id) {
        List<DtoPago> list = new ArrayList<>();
        return CompletableFuture.supplyAsync(
                () -> {
                    DtoPago dtoPago = new DtoPago();
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.pago " +
                                "WHERE id = " + id;
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            //System.out.println("Mira: " + rs.getString("nombre"));
                            dtoPago = new MapeoPago().mapRow(rs);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Optional<DtoPago> optional = Optional.of(dtoPago);
                    return optional;
                }, executionContext
        );
    }

    /*@Override
    public CompletionStage<Optional<DtoJugador>> obtenerPorDocumento(Long documento) {
        return CompletableFuture.supplyAsync(
                () -> {
                    DtoJugador dtoJugador = new DtoJugador();
                    try (Connection connection = db.getConnection()){
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM public.jugador " +
                                "WHERE documento = " + documento.toString();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            System.out.println("Mira: " + rs.getString("nombre"));
                            dtoJugador = new MapeoJugador().mapRow(rs);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Optional<DtoJugador> optional = Optional.of(dtoJugador);
                    return optional;
                }, executionContext
        );
    }*/
}
