package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaoJdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {
	
	
	@Test
	public void initBanco() {
	UserPosDAO userPosDAO = new UserPosDAO();
	Userposjava userposjava = new Userposjava();
	
	userposjava.setId(5L);
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
	
}
