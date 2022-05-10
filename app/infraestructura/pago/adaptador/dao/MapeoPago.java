package infraestructura.pago.adaptador.dao;

import dominio.pago.modelo.dto.DtoPago;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MapeoPago {
    public DtoPago mapRow(ResultSet rs) throws SQLException {

        DtoPago record = new DtoPago();
        record.setId(rs.getLong("id"));
        record.setPlaca(rs.getString("placa"));
        record.setTipoVehiculo(rs.getInt("tipo_vehiculo"));
        record.setValorPago(rs.getFloat("valor_pago"));
        record.setFechaPago(extraerLocalDate(rs, "fecha_pago"));

        return record;
    }
    private LocalDate extraerLocalDate(ResultSet resultSet, String label) throws SQLException {
        Timestamp fecha = resultSet.getTimestamp(label);
        LocalDate resultado = null;
        if (!resultSet.wasNull()) {
            resultado = fecha.toLocalDateTime().toLocalDate();
        }
        return resultado;
    }
}
