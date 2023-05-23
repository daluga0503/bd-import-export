package jugadores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import crud.CRUD;

public class JugadorService implements CRUD<Jugador>{
    Connection conn;
    public JugadorService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Jugador> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Jugador> result = new ArrayList<Jugador>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, apellidos, posicion, dorsal FROM Jugador";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            String posicion = querySet.getString("posicion");
            int dorsal = querySet.getInt("dorsal");
            result.add(new Jugador(id, nombre, apellidos, posicion, dorsal));
        } 
        statement.close();    
        return result;
    }

    public Jugador requestById(long id) throws SQLException{
        Statement statement = null;
        Jugador result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT id, nombre, apellidos FROM Jugador WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            String posicion = querySet.getString("posicion");
            int dorsal = querySet.getInt("dorsal");
            result = new Jugador(id, nombre, apellidos, posicion, dorsal);
        }
        statement.close();    
        return result;
    }

    public long createOrUpdate(Jugador object) throws SQLException{
        long id = object.getId();
        String nombre = object.getNombre();
        String apellidos = object.getApellidos();
        String posicion = object.getPosicion();
        long dorsal = object.getDorsal();
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO Jugador (id, nombre, apellidos, posicion, dorsal) VALUES ('%d', '%s', '%s', '%s', '%d')",id, nombre, apellidos, posicion, dorsal);
        // Ejecución de la consulta
        try {
            int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            if(e.getErrorCode()==1062){
                this.update(object);
                return id;
            }
            throw e;
        }
        
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long _id = generatedKeys.getLong(1);
                statement.close();
                return _id;
            }
            else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public long create(Jugador object) throws SQLException{
        String nombre = object.getNombre();
        String apellidos = object.getApellidos();
        String posicion = object.getPosicion();
        long dorsal = object.getDorsal();
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO Jugador (nombre, apellidos, posicion, dorsal) VALUES ('%s', '%s', '%s', '%d')", nombre, apellidos, posicion, dorsal);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                statement.close();
                return id;
            }
            else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public int update(Jugador object) throws SQLException{
        long id = object.getId();
        String nombre = object.getNombre();
        String apellidos = object.getApellidos();
        String posicion = object.getPosicion();
        long dorsal = object.getDorsal();
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE Jugador SET nombre = '%s', apellidos = '%s', posicion = '%s', dorsal = '%d'  WHERE id=%d", nombre, apellidos, posicion, dorsal, id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean delete(long id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM Jugador WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }

}
