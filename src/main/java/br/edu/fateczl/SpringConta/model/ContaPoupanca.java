package br.edu.fateczl.SpringConta.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaPoupanca extends ContaBancaria {
 
	ContaBancaria contaBancaria;
	int dia_rendimento;
	float  taxa_rendimento;
 
	@Override
	public String toString() {
		return "ContaPoupanca [contaBancaria=" + contaBancaria + ", dia_rendimento=" + dia_rendimento
				+ ", taxa_rendimento=" + taxa_rendimento + "]";
	}
}
