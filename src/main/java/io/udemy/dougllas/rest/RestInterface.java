package io.udemy.dougllas.rest;

import java.util.List;

/*
* Interface criado a partir da sugestao do prof. | v.44
* */

public interface RestInterface<T> {

    List<T> getAll();

    T getById(Integer id);

    T salvar(T t);

    void deletar(Integer id);

    void updater(Integer id, T t);

    List<T> procurar(T t);

}
