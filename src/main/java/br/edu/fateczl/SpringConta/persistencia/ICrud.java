package br.edu.fateczl.SpringConta.persistencia;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T>{
	public T consultar(T t) throws SQLException, ClassNotFoundException;
	public List<T> listarPoupanca() throws SQLException, ClassNotFoundException;
}
