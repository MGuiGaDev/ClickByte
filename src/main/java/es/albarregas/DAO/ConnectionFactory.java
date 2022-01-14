/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class ConnectionFactory {

    private static Connection conexion = null;
    private static final String DATASOURCE_NAME_MYSQL = "java:comp/env/jdbc/PoolClickbyte";
    

    public static Connection openConnectionMysql() {

        try {
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(DATASOURCE_NAME_MYSQL);
            conexion = (Connection) ds.getConnection();

        } catch (SQLException | NamingException sE) {
            sE.printStackTrace();
        }
        return conexion;
    }

    public static void closeConnection() {
        try {
                conexion.close();
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
