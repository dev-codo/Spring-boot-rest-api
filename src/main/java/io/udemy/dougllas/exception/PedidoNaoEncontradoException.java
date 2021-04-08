package io.udemy.dougllas.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido nao encontrado - no RuntimeException");
    }
}
