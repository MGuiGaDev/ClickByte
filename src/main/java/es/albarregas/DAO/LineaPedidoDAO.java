/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class LineaPedidoDAO implements ILineaPedidoDAO {

    private static Connection conexion = null;

    @Override
    public void insertarLineaPedido(LineaPedido lp) {
        Statement crearLp = null;
        String sql = "INSERT INTO lineasPedidos (IdProducto, IdPedido, Cantidad, Orden) VALUES('" + lp.getIdProducto() + "','" + lp.getIdPedido() + "', '" + lp.getCantidad() + "', '" + lp.getOrden() + "')";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            crearLp = conexion.createStatement();
            crearLp.executeUpdate(sql);
            conexion.commit();
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            closeConnection();
        }
    }

    @Override
    public ArrayList<LineaCesta> obtenerTodasLasLineas(Pedido pedido) {
        ArrayList<LineaCesta> lineasCesta = new ArrayList();
        LineaCesta lineaCesta = null;
        PreparedStatement lineasPedidoPS = null;
        ResultSet lineaPedidoRS = null;
        String consulta = "select p.idProducto, p.nombre, p.marca, p.imagen, p.precio, lp.cantidad, lp.idlinea, pe.idPedido from productos p inner join lineaspedidos lp using(idproducto) inner join pedidos pe on lp.idPedido = pe.idPedido and pe.estado='c' and pe.idPedido = ?;";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            lineasPedidoPS = conexion.prepareStatement(consulta);
            lineasPedidoPS.setShort(1, pedido.getIdPedido());
            lineaPedidoRS = lineasPedidoPS.executeQuery();
            while (lineaPedidoRS.next()) {
                lineaCesta = new LineaCesta();
                lineaCesta.setIdProducto(lineaPedidoRS.getShort(1));
                lineaCesta.setNombre(lineaPedidoRS.getString(2));
                lineaCesta.setMarca(lineaPedidoRS.getString(3));
                lineaCesta.setNombreImagen(lineaPedidoRS.getString(4));
                lineaCesta.setPrecioUnitario(lineaPedidoRS.getDouble(5));
                lineaCesta.setCantidad(lineaPedidoRS.getShort(6));
                lineaCesta.setIdLinea(lineaPedidoRS.getShort(7));
                lineaCesta.setIdPedido(lineaPedidoRS.getShort(8));
                lineasCesta.add(lineaCesta);
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection();
        }
        return lineasCesta;
    }

    @Override
    public void actualizarCantidadLineaPedido(LineaPedido lp) {
        PreparedStatement lineaP = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            lineaP = conexion.prepareStatement("UPDATE lineasPedidos SET cantidad=? WHERE IdLinea=?;");
            lineaP.setShort(1, lp.getCantidad());
            lineaP.setShort(2, lp.getIdLinea());
            lineaP.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            closeConnection();
        }
    }

    @Override
    public LineaPedido getIdLineaPedido(LineaCesta lc) {
        LineaPedido lp = new LineaPedido();
        PreparedStatement lineaPedidoPS = null;
        ResultSet lineaPedidoRS = null;
        String consulta = "SELECT l.IdLinea FROM  lineasPedidos l inner join pedidos p using (IdPedido) WHERE p.Estado = 'c' AND l.IdPedido = ? AND l.IdProducto = ?;";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            lineaPedidoPS = conexion.prepareStatement(consulta);
            lineaPedidoPS.setShort(1, lc.getIdPedido());
            lineaPedidoPS.setShort(2, lc.getIdProducto());
            lineaPedidoRS = lineaPedidoPS.executeQuery();
            while (lineaPedidoRS.next()) {
                lp.setIdLinea(lineaPedidoRS.getShort(1));
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection();
        }
        return lp;
    }

    @Override
    public void eliminarUnaLineaPedido(LineaPedido lp) {
        PreparedStatement lineaP = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            lineaP = conexion.prepareStatement("DELETE FROM lineasPedidos WHERE IdLinea=?;");
            lineaP.setShort(1, lp.getIdLinea());
            lineaP.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            closeConnection();
        }
    }

    @Override
    public void eliminarTodasLasLineas(Pedido p) {
        PreparedStatement pPS = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            pPS = conexion.prepareStatement("DELETE FROM lineasPedidos WHERE IdPedido=?;");
            pPS.setShort(1, p.getIdPedido());
            pPS.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            closeConnection();
        }
    }

    @Override
    public void insertarVariasLineasPedido(String consulta) {
        Statement crearLp = null;
        String sql = consulta;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            crearLp = conexion.createStatement();
            crearLp.executeUpdate(sql);
            conexion.commit();
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            closeConnection();
        }
    }


    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
