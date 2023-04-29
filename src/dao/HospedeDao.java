package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospede;
import modelo.Reserva;

public class HospedeDao {

	private Connection con;
	
	public HospedeDao(Connection con) {
		
		this.con = con;
		
	}
	
	public void salvarComReserva(Hospede hospede) {
		
		try {
			
			String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, reserva_id) VALUES (?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setObject(3, hospede.getDataNascimento());
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getReserva_id());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					
					while(rst.next()) {
						
						hospede.setId(rst.getInt(1));
						
					}
					
				}
				
			}
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public List<Hospede> listar(){
		
		try {
		
			List<Hospede> hospedes = new ArrayList<>();
			String sql = "SELECT * FROM hospedes";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.execute();
				
				transformarResultSetEmHospede(hospedes, pstm);
				
			}
			
			return hospedes;
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public List<Hospede> buscar(Integer id){
		
		try {
			
			List<Hospede> listaHospedes = new ArrayList<>();
			String sql = "SELECT ID, NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, RESERVA_ID FROM hospedes WHERE RESERVA_ID = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setInt(1, id);
				pstm.execute();
				
				transformarResultSetEmHospede(listaHospedes, pstm);
				
			}
			
			return listaHospedes;
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	
	public List<Hospede> buscar(String sobrenome){
		
		try {
			
			List<Hospede> listaHospedes = new ArrayList<>();
			String sql = "SELECT ID, NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, RESERVA_ID FROM hospedes WHERE sobrenome = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setString(1, sobrenome);
				pstm.execute();
				
				transformarResultSetEmHospede(listaHospedes, pstm);
				
			}
			
			return listaHospedes;
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public void alterar(String nome, String sobrenome, Object objetoNascimento, String nacionalidade, String telefone, Integer id) {
		
		try {
			
			String sql = "UPDATE hospedes h SET h.nome = ?, h.sobrenome = ?, h.data_nascimento = ?, h.nacionalidade = ?, h.telefone = ? WHERE id = ?";

			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				String dataNascimento = objetoNascimento.toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dataNascimentoC = LocalDate.parse(dataNascimento, formatter);
				
				pstm.setString(1, nome);
				pstm.setString(2, sobrenome);
				pstm.setObject(3, dataNascimentoC);
				pstm.setString(4, nacionalidade);
				pstm.setString(5, telefone);
				pstm.setInt(6, id);
				
				pstm.execute();
				
			}
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	public void deletar(Integer id) {
		
		try {
			
			String sql = "DELETE hospedes, reservas FROM hospedes INNER JOIN "
					+ "reservas ON reservas.id = hospedes.reserva_id WHERE hospedes.id = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setInt(1, id);
				pstm.execute();
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException();
			
		}
		
	}
	
	
	
	// abstraindo result set para um método
	private void transformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
		
		try {
		
			try(ResultSet rst = pstm.getResultSet()){
				
				while(rst.next()) {
				
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), 
						rst.getDate(4).toLocalDate(), rst.getString(5), rst.getString(6), rst.getInt(7));
				hospedes.add(hospede);
				
				}
			}
			
		}catch(SQLException e) {
			
			
			throw new RuntimeException(e);
			
		}
	}

	
	
	
}
