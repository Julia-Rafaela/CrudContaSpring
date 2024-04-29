package br.edu.fateczl.SpringConta.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaBancaria {
 
	int num_conta;
	String nome_cliente;
	float saldo;
	
	@Override
	public String toString() {
		return "ContaBancaria [num_conta=" + num_conta + ", nome_cliente=" + nome_cliente + ", saldo=" + saldo + "]";
	}


	
}
