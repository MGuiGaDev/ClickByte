/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
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
public class PedidoDAO implements IPedidoDAO {

    private static Connection conexion = null;

    @Override
    public boolean crearPedido(Pedido pedido) {
        boolean creado = false;
        Statement crearPedido = null;
        String sql = "INSERT INTO pedidos (Fecha, Estado, IdUsuario, Importe, Iva) VALUES(now(), 'c', " + pedido.getIdUsuario() + "," + pedido.getImporte() + ", " + pedido.getIva() + ");";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            crearPedido = conexion.createStatement();
            crearPedido.executeUpdate(sql);
            conexion.commit();
            creado = true;
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
        return creado;
    }

    @Override
    public Pedido obtenerPedidoNoFinalizado(Pedido pedido) {
        Pedido p = null;
        PreparedStatement pedidoPS = null;
        ResultSet pedidoRS = null;
        String consulta = "SELECT IdPedido, Fecha, Estado, IdUsuario, Importe, Iva FROM pedidos WHERE Estado = 'c'   AND IdUsuario = ?;";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            pedidoPS = conexion.prepareStatement(consulta);
            pedidoPS.setShort(1, pedido.getIdUsuario());
            pedidoRS = pedidoPS.executeQuery();
            while (pedidoRS.next()) {
                p = new Pedido();
                p.setIdPedido(pedidoRS.getShort(1));
                p.setFecha(pedidoRS.getDate(2));
                p.setEstado(pedidoRS.getString(3));
                p.setIdUsuario(pedidoRS.getShort(4));
                p.setImporte(pedidoRS.getDouble(5));
                p.setIva(pedidoRS.getDouble(6));
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection();
        }
        return p;
    }

    @Override
    public void eliminarPedido(Pedido pedido) {
        PreparedStatement pPS = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            pPS = conexion.prepareStatement("DELETE FROM lineasPedidos WHERE idPedido=?;");
            pPS.setShort(1, pedido.getIdPedido());
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
    public void actualizarPedido(Pedido pedido) {
        PreparedStatement pPS = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            pPS = conexion.prepareStatement("UPDATE pedidos SET Importe=?, Iva=? WHERE IdPedido=?;");
            pPS.setDouble(1, pedido.getImporte());
            pPS.setDouble(2, pedido.getIva());
            pPS.setShort(3, pedido.getIdPedido());
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
    public void cambiarEstadoPedido(Pedido pedido) {
        PreparedStatement pPS = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            pPS = conexion.prepareStatement("UPDATE pedidos SET Estado='f' WHERE IdPedido=?;");
            pPS.setShort(1, pedido.getIdPedido());
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
    public ArrayList<Pedido> obtenerTodosLosPedidos(Usuario usuario) {
        Pedido p = null;
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        PreparedStatement pPS = null;
        ResultSet pedidoRS = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            pPS = conexion.prepareStatement("SELECT * FROM pedidos WHERE IdUsuario=?;");
            pPS.setShort(1, usuario.getIdUsuario());
            pedidoRS = pPS.executeQuery();
            while (pedidoRS.next()) {
                p = new Pedido();
                p.setIdPedido(pedidoRS.getShort(1));
                p.setFecha(pedidoRS.getDate(2));
                p.setEstado(pedidoRS.getString(3));
                p.setIdUsuario(pedidoRS.getShort(4));
                p.setImporte(pedidoRS.getDouble(5));
                p.setIva(pedidoRS.getDouble(6));
                listaPedidos.add(p);
            }
        } catch (SQLException ex1) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);

        } finally {
            closeConnection();
        }
        return listaPedidos;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
