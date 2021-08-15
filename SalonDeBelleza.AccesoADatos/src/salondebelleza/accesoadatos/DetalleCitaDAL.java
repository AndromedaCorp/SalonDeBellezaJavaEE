
package salondebelleza.accesoadatos;

import java.util.*;
import java.sql.*;
import salondebelleza.entidadesdenegocio.*;

public class DetalleCitaDAL {
    
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Usuario
    static String obtenerCampos() {
        return "d.Id,d.idCita, d.idServicio, d.Precio. d.Duracion,";
    }
    // Metodo para obtener el SELECT a la tabla Rol y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(DetalleCita pDetalleCita) {
        String sql;
        sql = "SELECT ";
        if (pDetalleCita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pDetalleCita.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " DetalleCita d"); // Agregar los campos de la tabla de Rol mas el FROM a la tabla Rol con su alias "r"
        return sql;
    }
    
    // Metodo para obtener Order by a la consulta SELECT de la tabla Rol y ordene los registros de mayor a menor 
    private static String agregarOrderBy(DetalleCita pDetalleCita) {
        String sql = " ORDER BY r.Id DESC";
        if (pDetalleCita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Rol en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pDetalleCita.getTop_aux() + " ";
        }
        return sql;
    }
    
}
