package modelo;

import java.time.LocalDate;

public class Hospede {

	private Integer id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String telefone;
	private Integer reserva_id;
	
	
	public Hospede() {
		
		//this.nome = nome;
		//this.sobrenome = sobrenome;
		//this.dataNascimento = dataNascimento;
		//this.nacionalidade = nacionalidade;
		//this.telefone = telefone;
		
	}
	
	public Hospede(Integer id, String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone, Integer reserva_id) {
		
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reserva_id = reserva_id;
		
	}
	
	public Integer getId() {
		
		return this.id;
		
	}
	
	public void setId(Integer id) {
		
		this.id = id;
		
	}
	
	public String getNome() {
		
		return this.nome;
		
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
		
	}
	
	public String getSobrenome() {
		
		return this.sobrenome;
		
	}
	
	public void setSobrenome(String sobrenome) {
		
		this.sobrenome = sobrenome;
		
	}
	
	public LocalDate getDataNascimento() {
		
		return this.dataNascimento;
		
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		
		this.dataNascimento = dataNascimento;
		
	}
	
	public String getNacionalidade() {
		
		return this.nacionalidade;
		
	}
	
	public void setNacionalidade(String nacionalidade) {
		
		this.nacionalidade = nacionalidade;
		
	}
	
	public String getTelefone() {
		
		return this.telefone;
		
	}
	
	public void setTelefone(String telefone) {
		
		this.telefone = telefone;
		
	}
	
	
	public Integer getReserva_id() {
		
		return this.reserva_id;
		
	}
	
	public void setReserva_id(Integer reserva_id) {
		
		this.reserva_id = reserva_id;
		
	}
	
	@Override
	public String toString() {
		
	return "[Nome do hospede: " + getNome() + "| Sobrenome: " + getSobrenome() + 
			"| Data_nasc: " + getDataNascimento() + "| Nacionalidade: " + getNacionalidade() + 
			"Numero de Telefone: " + getTelefone() + "Numero da reserva: " + getReserva_id() + "]";

		
	}
	
}
