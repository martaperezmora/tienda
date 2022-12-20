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
import com.mpm.springprojects.tienda.model.Producto;

public class PedidosDAOImpl extends JdbcDaoSupport implements PedidosDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Page<Pedido> findAll(Pageable page) {
        String queryCount = "select count(1) from Pedidos";
        Integer total = getJdbcTemplate().queryForObject(queryCount, Integer.class);

        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT * FROM Pedidos ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Pedido> pedidos = getJdbcTemplate().query(query, new RowMapper<Pedido>() {

            @Override
            @Nullable
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getInt("codigo"));
                pedido.setNombre(rs.getString("nombre"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setPrecio(rs.getFloat("precio"));

                return pedido;
            }

        });

        return new PageImpl<Pedido>(pedidos, page, total);
    }

    @Override
    public Pedido findById(int codigo) {
        String query = "select * from Pedidos where codigo = ?";
        Object params[] = { codigo };
        int types[] = { Types.INTEGER };
        Pedido pedido = (Pedido) getJdbcTemplate().queryForObject(query, params, types,
                new BeanPropertyRowMapper(Pedido.class));
        return pedido;
    }

    @Override
    public Page<Pedido> findByCliente(int codigo, Pageable page) {
        String queryCount = "select count(1) from Pedidos";
        Integer total = getJdbcTemplate().queryForObject(queryCount, Integer.class);

        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT * FROM Pedidos WHERE cliente_id = " + codigo + "ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Pedido> pedidos = getJdbcTemplate().query(query, new RowMapper<Pedido>() {

            @Override
            @Nullable
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getInt("codigo"));
                pedido.setFecha(rs.getDate("fecha"));

                // ???

                return pedido;
            }

        });

        return new PageImpl<Pedido>(pedidos, page, total);
    }

    @Override
    public void insert(Pedido pedido) {
        String query = "insert into Pedidos (nombre," +
                " descripcion," +
                " precio," +
                " imagen)" +
                " values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                Array arrayCodProductos;
                int i = 0;
                for(Producto producto : pedido.getProductos()){
                    arrayCodProductos[i] = producto.getCodigo();
                    i++;
                }

                ps.setInt(1, pedido.getCliente().getCodigo());
                ps.setDate(2, pedido.getFecha());
                ps.setArray(3, arrayCodProductos);
                ps.setDouble(4, pedido.getImporteTotal());

                return ps;
            }
        }, keyHolder);

        pedido.setCodigo(keyHolder.getKey().intValue());

    }

    @Override
    public void update(Pedido pedido) {
        String query = "update Productos set cliente_id = ?, productos = ?, fecha = ?, importe_total = ? where codigo = ?";

        Object[] params = {
                pedido.getCliente().getCodigo(),
                pedido.getProductos(),
                

        };

        int[] types = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.FLOAT,
                Types.INTEGER
        };

        int update = getJdbcTemplate().update(query, params, types);

    }

    @Override
    public void delete(int codigo) {
        String query = "delete from Pedidos where codigo = ?";

        Object params[] = { codigo };
        int types[] = { Types.INTEGER };

        int update = getJdbcTemplate().update(query, params, types);
    }

}
