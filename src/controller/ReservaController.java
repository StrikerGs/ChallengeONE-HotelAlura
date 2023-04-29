package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import dao.ReservaDao;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {

	private ReservaDao reservaDao;
	
	public ReservaController() {
		
		Connection con = new ConnectionFactory().recuperarConexao();
		this.reservaDao = new ReservaDao(con);
		
	}
	
	public void salvar(Reserva reserva) {
		
		this.reservaDao.salvar(reserva);
		
	}
	
	public void alterar(Object dataEntrada, Object dataSaida, Object valor, String formaDePagamento, Integer id) {
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate dataEntradaConvertida = LocalDate.parse(dataEntrada, formatter);
//		LocalDate dataSaidaConvertida = LocalDate.parse(dataSaida, formatter);
		
		this.reservaDao.alterar(dataEntrada, dataSaida, valor, formaDePagamento, id);
		
	}
	
	public void deletar(Integer id) {
		
		this.reservaDao.deletar(id);
		
	}
	
	public List<Reserva> listar(){
		
		return reservaDao.listar();
		
	}
	
	public List<Reserva> buscar(Integer id){
		
		return this.reservaDao.buscar(id);
		
	}
	
}
