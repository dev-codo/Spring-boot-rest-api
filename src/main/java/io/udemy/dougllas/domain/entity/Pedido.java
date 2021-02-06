package io.udemy.dougllas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    /* using LOMBOK */
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public LocalDate getDataPedido() {
//        return dataPedido;
//    }
//
//    public void setDataPedido(LocalDate dataPedido) {
//        this.dataPedido = dataPedido;
//    }
//
//    public BigDecimal getTotal() {
//        return total;
//    }
//
//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }
//
//    public List<ItemPedido> getItens() {
//        return itens;
//    }
//
//    public void setItens(List<ItemPedido> itens) {
//        this.itens = itens;
//    }
//
//    @Override
//    public String toString() {
//        return "Pedido{" +
//                "id=" + id +
//                ", dataPedido=" + dataPedido +
//                ", total=" + total +
//                '}';
//    }
}
