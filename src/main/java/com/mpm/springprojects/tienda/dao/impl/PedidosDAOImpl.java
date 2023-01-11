package com.mpm.springprojects.tienda.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.mpm.springprojects.tienda.dao.PedidosDAO;
import com.mpm.springprojects.tienda.model.Pedido;

public class PedidosDAOImpl extends JdbcDaoSupport implements PedidosDAO {

    @Override
    public Page<Pedido> findAll(Pageable page) {
        String queryCount = "select count(1) from pedidos";
        Integer total = getJdbcTemplate().queryForObject(queryCount, Integer.class);

        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT * FROM pedidos ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Pedido> pedidos = getJdbcTemplate().query(query, new RowMapper<Pedido>() {

            @Override
            @Nullable
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getInt("codigo"));
        
                pedido.setTotal(rs.getDouble("total"));
                pedido.setFecha(rs.getDate("fecha"));

                return pedido;
            }

        });

        return new PageImpl<Pedido>(pedidos, page, total);
    }

    @Override
    public Pedido findById(int codigo) {
        String query = "select * from pedidos where codigo = ?";
        Object params[] = { codigo };
        int types[] = { Types.INTEGER };
        Pedido pedido = (Pedido) getJdbcTemplate().queryForObject(query, params, types,
                new BeanPropertyRowMapper(Pedido.class));
        return pedido;
    }

    @Override
    public void insert(Pedido pedido) {
        String query = "insert into pedidos (codigo_cliente,total,fecha) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, pedido.getCodigoCliente());
                ps.setDouble(2, pedido.getTotal());
                ps.setDate(3, pedido.getFecha());

                return ps;
            }
        }, keyHolder);

        pedido.setCodigo(keyHolder.getKey().intValue());

    }

    @Override
    public void update(Pedido pedido) {
        String query = "update pedidos set codigo_cliente = ?, total = ?, fecha = ? where codigo = ?";

        Object[] params = {
                pedido.getCodigo(),
                pedido.getCodigoCliente(),
                pedido.getTotal(),
                pedido.getFecha(),
        };

        int[] types = {
                Types.INTEGER,
                Types.INTEGER,
                Types.DOUBLE,
                Types.DATE
        };

        int update = getJdbcTemplate().update(query, params, types);

    }

    @Override
    public void delete(int codigo) {
        String query = "delete from pedidos where codigo = ?";

        Object params[] = { codigo };
        int types[] = { Types.INTEGER };

        int update = getJdbcTemplate().update(query, params, types);

    }

}