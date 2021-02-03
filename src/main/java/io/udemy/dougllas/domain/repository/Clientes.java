package io.udemy.dougllas.domain.repository;

import io.udemy.dougllas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static final String INSERT = "insert into cliente (nome) values (?)";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static final String SELECT_POR_NOME = "select * from cliente where nome like ?"; // alternative
    private static final String UPDATE = "update cliente set nome = ? where id = ?";
    private static final String DELETE = "delete from cliente where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE,
                cliente.getNome(),
                cliente.getId()
        );
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where nome like ?"),
//                new Object[]{"%" + nome + "%"}, // deprecated
                obterClienteRowMapper(),
                "%" + nome + "%");
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterClienteRowMapper());
    }

    private RowMapper<Cliente> obterClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Integer id = rs.getInt("id");
//                String nome = rs.getString("nome");
//                return new Cliente(id, nome); // OU...

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                return cliente;
            }
        };
    }
}
