package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import modelo.Reserva;

public class ReservaDao {

	Connection con;
	
	public ReservaDao(Connection con) {
		
		this.con = con;
		
	}
	
	public void salvar(Reserva reserva) {
		
		try {
		
			String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";
			
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
				pstm.setObject(1, reserva.getDataEntrada());
				pstm.setObject(2, reserva.getDataSaida());
				pstm.setBigDecimal(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaDePagamento());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					
					while(rst.next()) {
						
						reserva.setId(rst.getInt(1));
						
					}
					
				}
				
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
	}
	
	public void alterar(Object objetoEntrada, Object objetoSaida, Object objetoValor, String formaDePagamento, Integer id) {
		
		try {
			
			String sql = "UPDATE reservas r SET r.data_entrada = ?, r.data_saida = ?, r.valor = ?, r.forma_pagamento = ? WHERE id = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				String dataEntrada = objetoEntrada.toString();
				String dataSaida = objetoSaida.toString();
				String valorC = objetoValor.toString();
				BigDecimal valor = new BigDecimal(valorC);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dataEntradaC = LocalDate.parse(dataEntrada, formatter);
				LocalDate dataSaidaC = LocalDate.parse(dataSaida, formatter);
				
		
				pstm.setObject(1, dataEntradaC);
				pstm.setObject(2, dataSaidaC);
				pstm.setBigDecimal(3, valor);
				pstm.setString(4, formaDePagamento);
				pstm.setInt(5, id);
				
				pstm.execute();
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
	}
	
	public void deletar(Integer id) {
		
		try {
			
			//String sql = "DELETE reservas, hospedes FROM reservas INNER JOIN hospedes ON hospedes.reserva_id = reservas.id WHERE reservas.id = ?";
			/* A query acima não estava deixando eu excluir o registro pois ele tinha um relacionamento, então 
			 * copiei o comando usado no deletar do HospedeDao para deletar primeiro o registro que possui a foreign key e depois o registro pai */
			String sql = "DELETE hospedes, reservas FROM hospedes INNER JOIN "
					+ "reservas ON reservas.id = hospedes.reserva_id WHERE hospedes.reserva_id = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setInt(1, id);			
				pstm.execute();
				
			}
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public List<Reserva> listar() {
		
		try {
			
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM reservas";
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.execute();
				
				transformandoResultSetEmReserva(reservas, pstm);
				
			}
	
			return reservas;
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
	
	}
	
	public List<Reserva> buscar(Integer id) {
		
		try {
			
			List<Reserva> listaDeReserva = new ArrayList<>();
			String sql = "SELECT * FROM reservas WHERE id = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setInt(1, id);
				pstm.execute();
				
				transformandoResultSetEmReserva(listaDeReserva, pstm);
				
			}
			
			return listaDeReserva;
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public void transformandoResultSetEmReserva(List<Reserva> listaDeReserva, PreparedStatement pstm) {
		
		try {
			
			try(ResultSet rst = pstm.getResultSet()){
				
				while(rst.next()) {
					
					Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2).toLocalDate(), 
							rst.getDate(3).toLocalDate(), rst.getBigDecimal(4), rst.getString(5));
					listaDeReserva.add(reserva);
					
				}
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
