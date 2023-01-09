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

    @Override
    public Page<Pedido> findAll(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido findById(int codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Pedido Pedido) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Pedido Pedido) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int codigo) {
        String query = "delete from pedidos where codigo = ?";

        Object params[] = { codigo };
        int types[] = { Types.INTEGER };

        int update = getJdbcTemplate().update(query, params, types);
        
    }
    
}