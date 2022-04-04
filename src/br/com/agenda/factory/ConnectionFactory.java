package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	//Nome do Usu�rio do MySQL
	private static final String USERNAME = "root";
	
	//Senha do Banco de Dados MySQL
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	/*
	 * Conex�o com o banco de dados	 
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		//Faz com que a classe seja carregada pela JVM
		//Class.forName("com.mysql.jdbc.Driver");
		
		//Cria a conex�o com o Banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception{
		//Recuperar uma conex�o com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//Testar se a conex�o � nula
		if(con != null) {
			System.out.println("Conex�o obtida com sucesso!");
			con.close();
		}
	}
}
