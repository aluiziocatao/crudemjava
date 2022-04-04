package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {

		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Maria da Dores");
		contato.setIdade(35);
		contato.setDataCadastro(new Date());
		
		//contatoDao.save(contato);
		
		//Atualizar o contato.
		Contato c1 = new Contato();
		c1.setNome("Maria da Dores Orlando");
		c1.setIdade(36);
		c1.setDataCadastro(new Date());
		c1.setId(8); //Esse é o número que está no banco de dados.
		
		//contatoDao.update(c1);
		
		//Deletar o contato pelo seu número de ID
		contatoDao.deleteByID(7);
		
		
		//Visualização dos registros do banco de dados - TODOS
		
		for(Contato c : contatoDao.getContatos()){
			System.out.println("Contato: " + c.getNome());
		}
	}

}
