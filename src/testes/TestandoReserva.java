package testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

public class TestandoReserva {

	
	public static void main(String[] args) {
		
	Reserva reserva = new Reserva();
	
	//Long diferencaDeDias = reserva.getDataEntrada().until(reserva.getDataSaida(), ChronoUnit.DAYS);
	
	//System.out.println(diferencaDeDias);
	
	//System.out.println(reserva.getValor());
	
	ReservaController reservaController = new ReservaController();
	//reservaController.deletar(8);
		
	HospedeController hospedecon = new HospedeController();
	hospedecon.deletar(7);
	
	//hospedecon.alterar("Gustavo", "Suisso", LocalDate.of(2000, 03,13), "brasileiro", "1234567", 6);
//	
//	List<Hospede> listaHospedes = hospedecon.listar();
//	
//	for(Hospede hospede : listaHospedes) {
//		
//		System.out.println(hospede.getReserva_id());
//		
//	}
//	
//	
//	
//	}
	
	
	
		
		
	
	}
}
