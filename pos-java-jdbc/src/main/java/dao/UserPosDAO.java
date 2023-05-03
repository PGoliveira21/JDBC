package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import conexaoJdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {
	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	// metodo de salvar no banco de dados
	public void salvar(Userposjava userposjava) {
		String sql = "insert into userposjava (nome, email) values (?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();// salva no banco;

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			} // reverte a operação se der erro
			e.printStackTrace();
		}

	}

	// metodo de buscar os dados no banco de dados
	public List<Userposjava> listar() throws SQLException {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			Userposjava userposjava = new Userposjava();

			userposjava.setEmail(resultado.getString("email"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setId(resultado.getLong("id"));

			list.add(userposjava);

		}
		return list;
	}
	
	//lista os dados do usuario e o telefone pelo id passado no metodo construtor
	public List<BeanUserFone> listaUserFone (Long idUser){
		
		List<BeanUserFone> beanUserFone = new ArrayList<BeanUserFone>();
		String sql = " Select nome,numero,email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				
				beanUserFone.add(userFone);
				
			}
		} catch (SQLException e) {
			System.out.println("Erro aqui");
			e.printStackTrace();
		}
		
		
		
		return beanUserFone;
		
	}
	

	// Consultar so um objeto
	public Userposjava buscar(Long id) throws SQLException {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			retorno.setEmail(resultado.getString("email"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setId(resultado.getLong("id"));

		}
		return retorno;
	}

	// metodo de atualização recebendo um objeto UserposJava
	public void atualizar(Userposjava userposjava) {
		try {

			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
	  } catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
System.out.println("Ocorreu um rool back");
				e1.printStackTrace();
			}
		}
	}

	public void salvar(Telefone telefone) {
		String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa)VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public void deleteFonesPorUser(Long idUser) {
		try {
			//usuariopessoa e chave estrangeira
			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;
		
			
			PreparedStatement statement2 = connection.prepareStatement(sqlFone);
			statement2.executeUpdate();
			connection.commit();
			PreparedStatement statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// NUNCA SE ESQUECA DE USAR O ROLLBACK PARA NAO FAZER MERDA
			e1.printStackTrace();
		}
		}
	}
	
	
}
