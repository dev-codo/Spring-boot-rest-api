package io.udemy.dougllas.domain.repository;

import io.udemy.dougllas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

//    List<Cliente> findByNomeLike(String nome); OR, giving other name than Query methods...
    @Query("select c from Cliente c where c.nome like :neimi") // JPQL
    List<Cliente> encontrarPorNome(@Param("neimi") String nome);
//    @Query("select * from Cliente c where c.nome like '%:neimi%'", nativeQuery=true) // SQL

    List<Cliente> findByNomeOrId(String nome, Integer id);

    boolean existsByNome(String mingau);

    @Modifying
    void deleteByNome(String nome);

    /* get Cliente with Pedido | @OneToMany no Cliente */
    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

} // OR, without JpaRepository - manually created methods...

/* aula 18 */
//@Repository
//public class Clientes {

//    private static final String INSERT = "insert into cliente (nome) values (?)";
//    private static final String SELECT_ALL = "SELECT * FROM CLIENTE";
//    private static final String SELECT_POR_NOME = "select * from cliente where nome like ?"; // alternative
//    private static final String UPDATE = "update cliente set nome = ? where id = ?";
//    private static final String DELETE = "delete from cliente where id = ?";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public Cliente salvar(Cliente cliente) {
////        jdbcTemplate.update(INSERT, cliente.getNome()); // com JdbcTemplate
//        entityManager.persist(cliente); // com Jpa
//        return cliente;
//    }
//
//    @Transactional
//    public Cliente atualizar(Cliente cliente) {
////        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId()); // com JdbcTemplate
//        entityManager.merge(cliente); // com Jpa
//        return cliente;
//    }
//
//    @Transactional
//    public void deletar(Cliente cliente) {
////        deletar(cliente.getId()); // com JdbcTemplate
//        if (!entityManager.contains(cliente)) {
//            cliente = entityManager.merge(cliente);
//        }
//        entityManager.remove(cliente); // com Jpa
//    }
//
//    @Transactional
//    public void deletar(Integer id) {
////        jdbcTemplate.update(DELETE, id); // com JdbcTemplate
//        Cliente cliente = entityManager.find(Cliente.class, id);
//        deletar(cliente);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> buscarPorNome(String neimi) {
////        return jdbcTemplate.query(        // com JdbcTemplate
////                SELECT_ALL.concat(" where nome like ?"),
////                obterClienteRowMapper(),
////                "%" + nome + "%");
//
//        String jpql = "select c from Cliente c where c.nome like :neimi";
//        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//        query.setParameter("neimi", "%" + neimi + "%");
//        return query.getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> obterTodos() {
////        return jdbcTemplate.query(SELECT_ALL, obterClienteRowMapper());
//        return entityManager
//                .createQuery("from Cliente", Cliente.class)
//                .getResultList(); // com Jpa
//    }
//
////    com JdbcTemplate
////    private RowMapper<Cliente> obterClienteRowMapper() {
////        return new RowMapper<Cliente>() {
////            @Override
////            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//////                Integer id = rs.getInt("id");
//////                String nome = rs.getString("nome");
//////                return new Cliente(id, nome); // OU...
////
////                Cliente cliente = new Cliente();
////                cliente.setId(rs.getInt("id"));
////                cliente.setNome(rs.getString("nome"));
////                return cliente;
////            }
////        };
////    }
//}
