package br.edu.fateczl.SpringConta.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringConta.model.ContaPoupanca;
@Repository
public class ContaPoupancaDao implements ICrud<ContaPoupanca>{
	private GenericDao gDao;

	    public ContaPoupancaDao( GenericDao gDao) {
	    	this.gDao = gDao;
	    }

	    public String iudPoupanca(String op, ContaPoupanca p) throws SQLException, ClassNotFoundException {
	    	Connection c = gDao.getConnection();
			String sql = "CALL GerenciarGrade(?,?,?,?,?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1, op);
			cs.setInt(2, p.getNum_conta());
			cs.setInt(3, p.getDia_rendimento());
			cs.setFloat(4, p.getTaxa_rendimento());
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			String saida = cs.getString(5);
			cs.close();
			c.close();
			return saida;
	    }
	    public ContaPoupanca consultar(ContaPoupanca contaPoupanca) throws SQLException, ClassNotFoundException {
	        Connection c = gDao.getConnection();
	        String sql = "SELECT dia_rendimento, taxa_rendimento FROM ContaPoupanca WHERE num_conta = ?";
	        PreparedStatement ps = c.prepareStatement(sql);
	        ps.setInt(1, contaPoupanca.getNum_conta());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            contaPoupanca.setDia_rendimento(rs.getInt("dia_rendimento"));
	            contaPoupanca.setTaxa_rendimento(rs.getFloat("taxa_rendimento"));
	        } else {
	            contaPoupanca = null;
	        }
	        rs.close();
	        ps.close();
	        c.close();
	        return contaPoupanca;
	    }
	    public List<ContaPoupanca> listarPoupanca() throws SQLException, ClassNotFoundException {
	        List<ContaPoupanca> contasPoupanca = new ArrayList<>();
	        Connection c = gDao.getConnection();
	        String sql = "SELECT num_conta, dia_rendimento, taxa_rendimento FROM ContaPoupanca";
		    PreparedStatement ps = c.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                	ContaPoupanca p = new ContaPoupanca();
	                    p.setNum_conta(rs.getInt("num_conta"));
	                    p.setDia_rendimento(rs.getInt("dia_rendimento"));
	                    p.setTaxa_rendimento(rs.getFloat("taxa_rendimento"));
	                    
	                    
	                    contasPoupanca.add(p);
	                }
	    		    rs.close();
	    		    ps.close();
	    		    c.close();
	    		    return contasPoupanca;
	    }
}
