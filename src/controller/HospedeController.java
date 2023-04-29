package controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.HospedeDao;
import factory.ConnectionFactory;
import modelo.Hospede;

public class HospedeController {

	HospedeDao hospedeDao;
	
	public HospedeController() {
		
		Connection con = new ConnectionFactory().recuperarConexao();
		this.hospedeDao = new HospedeDao(con);
	}
	
	public void salvar(Hospede hospede) {
		
		this.hospedeDao.salvarComReserva(hospede);
		
	}
	
	public void alterar(String nome, String sobrenome, Object dataNascimento, String nacionalidade, String telefone, Integer id) {
		
		this.hospedeDao.alterar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
		
	}
	
	public void deletar(Integer id) {
		
		this.hospedeDao.deletar(id);
		
	}
	
	public List<Hospede> listar(){
		
		return hospedeDao.listar();
		
	}
	
	public List<Hospede> buscar(Integer id){
		
		return hospedeDao.buscar(id);
		
	}
	
	public List<Hospede> buscar(String sobrenome){
		
		return hospedeDao.buscar(sobrenome);
		
	}
	
}
