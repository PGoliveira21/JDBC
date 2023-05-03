package pos_java_jdbc.pos_java_jdbc;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import conexaoJdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	
	
	@Test
	public void initBanco() {
	UserPosDAO userPosDAO = new UserPosDAO();
	Userposjava userposjava = new Userposjava();
	
	
	userposjava.setNome("Dinamico");
    userposjava.setEmail("testandonovament.com");
    
    userPosDAO.salvar(userposjava);
	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		UserPosDAO userposdao = new UserPosDAO();
		try {
			Userposjava userposjava = userposdao.buscar(5L);
			System.out.println(userposjava);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			Userposjava objetoBanco = dao.buscar(3L);
			objetoBanco.setNome("Nome mudado com metodo atualizar");
			dao.atualizar(objetoBanco);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void initInsertCel() {
		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();
		telefone.setNumero("(31) 7516415333");
		telefone.setTipo("Celular");
		telefone.setUsuario(3L);
		dao.salvar(telefone);
		
	}
	
	@Test
	public void initDeletar () {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(7L);
			initListar();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void pesquisaNumeroUsuario() {
		try {
			UserPosDAO dao = new UserPosDAO();
			List<BeanUserFone> beanUserFone = dao.listaUserFone(3L);
			
			for (BeanUserFone beanUserFone2 : beanUserFone) {
				System.out.println(beanUserFone2);
			}
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void deletaEmCascata() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteFonesPorUser(3L);
		
		
	}
	
}
