package br.edu.fateczl.SpringConta.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaEspecial extends ContaBancaria {

	ContaBancaria contaBancaria;
	float limite;
	
	@Override
	public String toString() {
		return "ContaEspecial [contaBancaria=" + contaBancaria + ", limite=" + limite + "]";
	}
	
	
}
