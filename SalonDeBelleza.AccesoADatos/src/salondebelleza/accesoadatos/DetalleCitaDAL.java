
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
        String sql = " ORDER BY d.Id DESC";
        if (pDetalleCita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Rol en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pDetalleCita.getTop_aux() + " ";
        }
        return sql;
    }
    
    
    // Metodo para poder insertar un nuevo registro en la tabla de Rol
    public static int crear(DetalleCita pDetalleCita) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Cita(IdCita,IdServicio,Precio,Duracion) VALUES(?,?,?,?)"; // Definir la consulta INSERT a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pDetalleCita.getIdCita()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pDetalleCita.getIdServicio()); // Agregar el parametro a la consulta donde estan el simbolo ? #1
                ps.setFloat(3, pDetalleCita.getPrecio()); 
                 ps.setDouble(4, pDetalleCita.getDuracion()); 
                  
               // Agregar el parametro a la consulta donde estan el simbolo ? #1
                result = ps.executeUpdate(); // Ejecutar la consulta INSERT en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }
    
    
    // Metodo para poder actualizar un registro en la tabla de Rol
    public static int modificar(DetalleCita pDetalleCita) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE DetalleCita SET IdCita=? IdServicio=? Precio=? Duracion=? WHERE Id=?"; // Definir la consulta UPDATE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pDetalleCita.getIdCita()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pDetalleCita.getIdServicio()); // Agregar el parametro a la consulta donde estan el simbolo ? #1
                ps.setFloat(3, pDetalleCita.getPrecio()); 
                 ps.setDouble(4, pDetalleCita.getDuracion()); 
                result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }
    
    // Metodo para poder eliminar un registro en la tabla de Rol
    public static int eliminar(DetalleCita pDetalleCita) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM DetalleCita WHERE Id=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pDetalleCita.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate();  // Ejecutar la consulta DELETE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    } 
    
    // Metodo para llenar las propiedades de la entidad de Rol con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(DetalleCita pDetalleCita, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
        pIndex++;
        pDetalleCita.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDetalleCita.setIdCita(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pDetalleCita.setIdServicio(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pDetalleCita.setPrecio(pResultSet.getFloat(pIndex)); // index 2
        pIndex++;
        pDetalleCita.setDuracion(pResultSet.getDouble(pIndex)); // index 2
        return pIndex;
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<DetalleCita> pDetalleCita) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                DetalleCita detallecita = new DetalleCita();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(detallecita, resultSet, 0);
                pDetalleCita.add(detallecita); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de DetalleCita y JOIN a la tabla de Cita
    private static void obtenerDatosIncluirCita(PreparedStatement pPS, ArrayList<DetalleCita> pDetalleCita) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Cita> citaMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                DetalleCita detallecita = new DetalleCita();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(detallecita, resultSet, 0);
                if (citaMap.containsKey(detallecita.getIdCita()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Cita cita = new Cita();
                    // en el caso que el Rol no este en el HashMap se asignara
                    CitaDAL.asignarDatosResultSet(cita, resultSet, index);
                    citaMap.put(cita.getId(), cita); // agregar el Rol al  HashMap
                    detallecita.setCita(cita); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    detallecita.setCita(citaMap.get(detallecita.getIdCita())); 
                }
                pDetalleCita.add(detallecita); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de DetalleCita y JOIN a la tabla de Cita
    private static void obtenerDatosIncluirServicio(PreparedStatement pPS, ArrayList<DetalleCita> pDetalleCita) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Servicio> servicioMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                DetalleCita detallecita = new DetalleCita();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(detallecita, resultSet, 0);
                if (servicioMap.containsKey(detallecita.getIdServicio()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Servicio servicio = new Servicio();
                    // en el caso que el Rol no este en el HashMap se asignara
                    ServicioDAL.asignarDatosResultSet(servicio, resultSet, index);
                    servicioMap.put(servicio.getId(), servicio); // agregar el Rol al  HashMap
                    detallecita.setServicio(servicio); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    detallecita.setServicio(servicioMap.get(detallecita.getIdServicio())); 
                }
                pDetalleCita.add(detallecita); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    
    // Metodo para obtener por Id un registro de la tabla de Usuario 
    public static DetalleCita obtenerPorId(DetalleCita pDetalleCita) throws Exception {
        DetalleCita detallecita = new DetalleCita();
        ArrayList<DetalleCita> detallecitas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pDetalleCita); // obtener la consulta SELECT de la tabla Usuario
             // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE d.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pDetalleCita.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, detallecitas); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (detallecitas.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            detallecita = detallecitas.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return detallecita; // devolver el Usuario encontrado por Id 
    }
    
    
    public static ArrayList<DetalleCita> obtenerTodos() throws Exception {
        ArrayList<DetalleCita> detallecitas;
        detallecitas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new DetalleCita()); // obtener la consulta SELECT de la tabla Usuario
            sql += agregarOrderBy(new DetalleCita()); // concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, detallecitas); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return detallecitas; // devolver el ArrayList de Usuario
    }
    
    
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Usuario de forma dinamica
    static void querySelect(DetalleCita pDetalleCita, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pDetalleCita.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" d.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pDetalleCita.getId());
            }
        }
//         verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pDetalleCita.getIdCita()> 0) {
            pUtilQuery.AgregarWhereAnd(" d.IdCita=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pDetalleCita.getIdCita());
            }
        }

        
             // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
      if (pDetalleCita.getIdServicio()> 0) {
            pUtilQuery.AgregarWhereAnd(" d.IdServicio=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pDetalleCita.getIdServicio());
            }
        }
        
              // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
      if (pDetalleCita.getPrecio()> 0) {
            pUtilQuery.AgregarWhereAnd(" d.Precio=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setFloat(pUtilQuery.getNumWhere(), pDetalleCita.getPrecio());
            }
        }
        
        
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
      if (pDetalleCita.getDuracion()> 0) {
            pUtilQuery.AgregarWhereAnd(" d.Duracion=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setDouble(pUtilQuery.getNumWhere(), pDetalleCita.getDuracion());
            }
        }
        }
    
    
    // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Usuario 
    public static ArrayList<DetalleCita> buscar(DetalleCita pDetalleCita) throws Exception {
        ArrayList<DetalleCita> detallecitas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pDetalleCita); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pDetalleCita, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pDetalleCita); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pDetalleCita, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, detallecitas); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return detallecitas; // Devolver el ArrayList de Usuario
    }
    
    
}
