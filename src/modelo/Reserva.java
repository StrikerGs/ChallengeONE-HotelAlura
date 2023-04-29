package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Reserva {

	private Integer id;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private BigDecimal valor;
	private String formaDePagamento;
	private Long valorDiaria = 100l;

	
	public Reserva() {
	
		//Long diferençaDeDias = this.dataEntrada.until(this.dataSaida, ChronoUnit.DAYS);
		//this.valor = new BigDecimal(diferençaDeDias * 100);
		
	}
	
	// construtor para o ReservaDao
	public Reserva(Integer id, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor, String formaDePagamento) {
		
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
		
	}
	
	public Integer getId() {
		
		return this.id;
		
	}
	
	public void setId(Integer id) {
		
		this.id = id;
		
	}
	
	public LocalDate getDataEntrada() {
		
		return this.dataEntrada;
		
	}
	
	public void setDataEntrada(LocalDate dataEntrada) {
		
		this.dataEntrada = dataEntrada;
		
	}
	
	public LocalDate getDataSaida() {
		
		return this.dataSaida;
		
	}
	
	public void setDataSaida(LocalDate dataSaida) {
		
		this.dataSaida = dataSaida;
		
	}
	
	public BigDecimal getValor() {
		
		return this.valor;
		
		
	}
	
	public void setValor(Long diferencaDias) {
		
		if(diferencaDias > 0) {
	
			this.valor = new BigDecimal(diferencaDias * this.valorDiaria);
		
		} else {
			
			this.valor = new BigDecimal(this.valorDiaria);
			
		}
	}
	
	public String getFormaDePagamento() {
		
		return this.formaDePagamento;
		
	}
	
	public void setFormaDePagamento(String formaDePagamento) {
		
		this.formaDePagamento = formaDePagamento;
		
	}
	
	public Long getDiaria() {
		
		return this.valorDiaria;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Data de Entrada: " + getDataEntrada() + "| Data de Saida: " + getDataSaida() + "| Valor: " + getValor() + "| Forma de Pagamento: " + getFormaDePagamento() + "]";
	}
	
}
