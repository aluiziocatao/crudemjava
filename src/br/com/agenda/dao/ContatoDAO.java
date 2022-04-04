package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	/*
	 * CRUD 
	 * c: Create - OK - INSERT, r: Read	- SELECT, u: Update - UPDATE, d: Delete - DELETE
	 */
	
	public void save(Contato contato) {
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			//Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement, para executar um query
			pstm = conn.prepareStatement(sql);
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new java.sql.Date(contato.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();	
			System.out.println("Contato Salvo com Sucesso!!");
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Contato contato) {
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar classe para executar a query
			pstm = conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new java.sql.Date(contato.getDataCadastro().getTime()));
			
			//Qual o ID do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());
			
			//Executar a query
			pstm.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}				
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			//System.out.println("Removido com Sucesso!!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT**** 
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//Recuperar o id 
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));
				
				contatos.add(contato);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
}
