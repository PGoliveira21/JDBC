package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {
	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	
	//metodo de salvar no banco de dados
	public void salvar(Userposjava userposjava) {
		String sql = "insert into userposjava (id, nome, email) values (?,?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
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

	
	//metodo de buscar os dados no banco de dados
	public List<Userposjava> listar () throws SQLException{
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
	//Consultar so um objeto
	public Userposjava buscar (Long id) throws SQLException{
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
	
	
}
