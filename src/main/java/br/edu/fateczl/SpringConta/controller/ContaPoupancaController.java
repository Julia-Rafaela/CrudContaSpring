package br.edu.fateczl.SpringConta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.SpringConta.model.ContaPoupanca;
import br.edu.fateczl.SpringConta.persistencia.ContaPoupancaDao;
import br.edu.fateczl.SpringConta.persistencia.GenericDao;

@Controller
public class ContaPoupancaController  {
	@Autowired
	GenericDao gdao;

	@Autowired
	ContaPoupancaDao pdao;

	@RequestMapping(name = "contapoupanca", value = "/contapoupanca", method = RequestMethod.GET)
	public ModelAndView indexGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("cmd");
		String num_conta = allRequestParam.get("num_conta");
		ContaPoupanca p = new ContaPoupanca();

		String saida = "";
		String erro = "";
		List<ContaPoupanca> poupancas = new ArrayList<>();
		
	
		try {
			if (cmd != null) {
				if (cmd.contains("alterar")) {
					if (num_conta != null) {
						p.setNum_conta(Integer.parseInt(num_conta));
						p = buscarPoupanca(p);
	                } else {
	                    erro = "Número da conta não fornecido.";
	                }
	            } else if (cmd.contains("excluir")) {
	                if (num_conta != null) {
	                	p.setNum_conta(Integer.parseInt(num_conta));
	                	excluirPoupanca(p);
	                    saida = "Conta excluída com sucesso!";
	                    p = null;
	                } else {
	                    erro = "Número da conta não fornecido.";
	                }
	            }
			}
			poupancas = listarPoupanca();

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("contapoupanca", p);
			model.addAttribute("poupancas", poupancas);
		}
		return new ModelAndView("contapoupanca");

	}

	@RequestMapping(name = "contapoupanca", value = "/contapoupanca", method = RequestMethod.POST)
	public ModelAndView indexPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {

		// passar todos os parametros
		String cmd = allRequestParam.get("botao");
		String contaBancaria = allRequestParam.get("contaBancaria");
		String dia_rendimento = allRequestParam.get("dia_rendimento");
		String taxa_rendimento = allRequestParam.get("taxa_rendimento");
	
		// Saida
		String saida = "";
		String erro = "";
		ContaPoupanca p = new ContaPoupanca();
		List<ContaPoupanca> poupancas = new ArrayList<>();

		if (!cmd.contains("Listar")) {
			p.setNum_conta(Integer.parseInt(contaBancaria));
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			// todos os parametros menos o codigo
			p.setTaxa_rendimento(Float.parseFloat(taxa_rendimento));
			p.setDia_rendimento(Integer.parseInt(dia_rendimento));
		}

		try {
			if (cmd.contains("Cadastrar")) {
				saida = cadastrarPoupanca(p);
				p = null;
			}
			if (cmd.contains("Alterar")) {
				saida = alterarPoupanca(p);
				p = null;
			}
			if (cmd.contains("Excluir")) {
				saida = excluirPoupanca(p);
				p = null;
			}
			if (cmd.contains("Buscar")) {
				p = buscarPoupanca(p);
			}
			if (cmd.contains("Listar")) {
				poupancas = listarPoupanca();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("contapoupanca", p);
			model.addAttribute("poupancas", poupancas);
		}

		return new ModelAndView("contapoupanca");
	}

	private String cadastrarPoupanca(ContaPoupanca p) throws SQLException, ClassNotFoundException{
		String saida = pdao.iudPoupanca("I", p);
		return saida;
	}

	private String alterarPoupanca(ContaPoupanca p)throws SQLException, ClassNotFoundException {
		String saida = pdao.iudPoupanca("U", p);
		return saida;
	}

	private String excluirPoupanca(ContaPoupanca p) throws SQLException, ClassNotFoundException {
		String saida = pdao.iudPoupanca("D", p);
		return saida;

	}

	private List<ContaPoupanca> listarPoupanca() throws SQLException, ClassNotFoundException {
		List<ContaPoupanca> contasPoupanca = pdao.listarPoupanca();
		return contasPoupanca;
	}

	private ContaPoupanca buscarPoupanca(ContaPoupanca p) throws SQLException, ClassNotFoundException {
		p = pdao.consultar(p);
		return p;
	}

}
