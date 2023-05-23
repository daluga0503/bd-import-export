package crud;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD<T> {
    /** requestAll
     * Obtiene todos los registros de un modelo de la 
     * base de datos
     * @return
     * @throws SQLException
     */
    public ArrayList<T> requestAll() throws SQLException;

    /** requestById
     * Obtiene un registro del modelo dado su clave primaria
     * @param id
     * @return
     * @throws SQLException
     */
    public T requestById(long id) throws SQLException;


    /** Create
     * Crear o actualiza un registro en la base de datos para el modelo en cuestión
     * @param model
     * @return
     * @throws SQLException
     */
    public long createOrUpdate(T model) throws SQLException;

    /** Create
     * Crear un registro en la base de datos para el modelo en cuestión
     * @param model
     * @return
     * @throws SQLException
     */
    public long create(T model) throws SQLException;

    /** update
     * Actualiza la información de un registro de la base de datos
     * para el modelo en cuestión
     * @param object
     * @return
     * @throws SQLException
     */
    public int update(T object) throws SQLException;

    /** delete
     * Elimina un registro del modelo dada su clave primaria
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean delete(long id) throws SQLException;
}
