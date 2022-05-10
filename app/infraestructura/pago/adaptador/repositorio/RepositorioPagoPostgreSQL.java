package infraestructura.pago.adaptador.repositorio;

import dominio.pago.modelo.entidad.Pago;
import dominio.pago.puerto.repositorio.RepositorioPago;
import persistencia.DatabaseExecutionContext;
import play.db.Database;

import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RepositorioPagoPostgreSQL implements RepositorioPago {

    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public RepositorioPagoPostgreSQL(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Long> crear(Pago pago) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "INSERT INTO public.pago(" +
                            "placa, tipo_vehiculo, valor_pago, fecha_pago) " +
                            "VALUES (?, ?, ?, ?)";
                    Long id = 0L;
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, pago.getPlaca());
                        stmt.setInt(2, pago.getTipoVehiculo());
                        stmt.setFloat(3, pago.getValorPago());
                        stmt.setTimestamp(4, convertir(pago.getFechaPago()));

                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0){
                            try (ResultSet rs = stmt.getGeneratedKeys()){
                                if (rs.next()){
                                    id = rs.getLong(1);
                                }
                            } catch (SQLException ex){
                                System.out.println(ex.getMessage());
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return id;
                }, executionContext
        );
    }


    @Override
    public CompletionStage<Void> actualizar(Pago pago) {
        return CompletableFuture.runAsync(
                () -> {
                    String SQL = "UPDATE public.jugador " /*+
                            "SET documento = " + jugador.getDocumento().toString() + ", nombre = '" + jugador.getNombre() +
                            "', apellido = '" + jugador.getApellido() +"', fecha_nacimiento = '" + jugador.getFechaNacimiento().toString() +"'," +
                            " peso = " + jugador.getPeso() + ", altura = " + jugador.getAltura() + "," +
                            " posicion = '" + jugador.getPosicion() + "', pie_habil = '" + jugador.getPieHabil() +
                            "' WHERE id = " + jugador.getId().toString()*/;
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }, executionContext
        );
    }

    @Override
    public CompletionStage<Boolean> existePorId(Long id) {
            return CompletableFuture.supplyAsync(
                    () -> {
                        String SQL = "SELECT COUNT(*) FROM public.pago " +
                                "WHERE id = ?";
                        try (Connection connection = db.getConnection()) {
                            PreparedStatement stmt = connection.prepareStatement(SQL);
                            stmt.setLong(1, id);
                            ResultSet rs = stmt.executeQuery();
                            int cantidad = 0;
                            if (rs.next()) {
                                cantidad = rs.getInt(1);
                            }
                            if (cantidad > 0) {
                                return true;
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return false;
                    }, executionContext
            );
    }


    @Override
    public CompletionStage<Boolean> existePorPlaca(String placa) {
        return CompletableFuture.supplyAsync(
                () -> {
                    String SQL = "SELECT COUNT(*) FROM public.jugador " +
                            "WHERE placa = ?";
                    try (Connection connection = db.getConnection()){
                        PreparedStatement stmt = connection.prepareStatement(SQL);
                        stmt.setString(1, placa);
                        ResultSet rs = stmt.executeQuery();
                        int cantidad = 0;
                        if (rs.next()){
                            cantidad = rs.getInt(1);
                        }
                        if (cantidad > 0){
                            return true;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }, executionContext
        );
    }

    private Timestamp convertir (LocalDate fecha){
        Timestamp timestamp = Timestamp.valueOf(fecha.atStartOfDay());
        return timestamp;
    }
}
