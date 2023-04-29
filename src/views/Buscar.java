package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private HospedeController hospedeController;
	private ReservaController reservaController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		
		this.hospedeController = new HospedeController();
		this.reservaController = new ReservaController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pagamento");
		
		// preenchendo as reservas
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		// fazendo os registro aparecerem apenas se a tabela de reservas for clicada
		scroll_table.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(modelo.getDataVector().isEmpty()) {
					
					preencherTabelaReservas();
					
				} else if(!modelo.getDataVector().isEmpty()) {
					
					limparTabelaReserva();
					
				}
				
			}
			
		});
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		
		// preenchendo os hospedes
		
		
		JScrollPane scroll_tableHospedes = new JScrollPane(tbHospedes);
		panel.addTab("Hospedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHospedes, null);
		scroll_tableHospedes.setVisible(true);
		
		// fazendo a mesma coisa feita na tabela de reservas
//		scroll_tableHospedes.addMouseListener(new MouseAdapter() {
//		
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				if(modeloHospedes.getDataVector().isEmpty()) {
//					
//					preencherTabelaHospede();
//					
//				} else if(!modeloHospedes.getDataVector().isEmpty()) {
//				
//					limparTabelaHospede();
//				
//				}		
//			}
//		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			// try catch para caso ao ocorra um numberException, o buscarPorSobrenome seja executado.	
				try {
					
					buscarPorNumeroDaReserva(txtBuscar.getText());
					
				} catch(NumberFormatException exception) {
					
					buscarPorSobrenome(txtBuscar.getText());
					
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
					if(modelo.getDataVector().isEmpty()) {
						
						alterarHospede();
						limparTabelaHospede();
						preencherTabelaHospede();
						
					} else if(modeloHospedes.getDataVector().isEmpty()) {
						
						alterarReserva();
						limparTabelaReserva();
						preencherTabelaReservas();
						
					}
				
			}
			
		});
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		
		
	
			btnDeletar.addMouseListener(new MouseAdapter() {
				
				/* os ifs foram feitos assim pois estava dando a exception Array Index Out Of Bounds Exception 
				quando tentava fazer a exclusão com ambas as tabelas com registros, ainda não entendi essa exception */
				@Override
				public void mouseClicked(MouseEvent e) {
						
					if(modeloHospedes.getDataVector().isEmpty()) {
						
						deletarReserva();
						
					} else if(modelo.getDataVector().isEmpty()) {
						
						deletarHospede();
						
					}
				}
				
			});
				

	
			
		
		
		setResizable(false);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    
	 
	 private void preencherTabelaReservas() {
		 
		 List<Reserva> listaDeReservas = listarReserva();
		 
		 for(Reserva reserva : listaDeReservas) {
			 
			 modelo.addRow(new Object[] {reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), 
					 reserva.getValor(), reserva.getFormaDePagamento()});
			 
		 }
		 
	 }
	    
	 private List<Reserva> listarReserva(){
		 
		 return this.reservaController.listar();
		 
	 }
	 
	 private void alterarReserva() {
		 
		 Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
		 
		 // foi necessario enviar as datas como Object pois estava dando uma exception de cast com string
		 if(objetoDaLinha instanceof Integer) {
			 
			 Integer id = (Integer) objetoDaLinha;
			 Object dataEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1);
			 Object dataSaida = modelo.getValueAt(tbReservas.getSelectedRow(), 2);
			 Object valor =  modelo.getValueAt(tbReservas.getSelectedRow(), 3);
			 String formaDePagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
			 this.reservaController.alterar(dataEntrada, dataSaida, valor, formaDePagamento, id);
			 JOptionPane.showMessageDialog(this, "Dados da reserva alterados com sucesso!");
			 
		 } else {
			 
			 JOptionPane.showMessageDialog(this, "Por favor, selecione o ID antes de tentar alterar.");
			 
		 }
		 
	 }
	 
	 private void deletarReserva() {
		 
		 
		 
			 Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
			 
			 if(objetoDaLinha instanceof Integer) {
				 
				 Integer id = (Integer) objetoDaLinha;
				 this.reservaController.deletar(id);
				 modelo.removeRow(tbReservas.getSelectedRow());
				 JOptionPane.showMessageDialog(this, "Registro da reserva excluido com sucesso!");
				 
			 } else {
				 
				 JOptionPane.showMessageDialog(this, "Por favor, selecione o ID antes de tentar excluir.");
				 
			 }
			 
		 }
	
	    
	 private void preencherTabelaHospede() {
		 
		 List<Hospede> listaDeHospedes = listarHospede();
		 
		 try {
			 
			 for(Hospede hospede : listaDeHospedes) {
				 
				 modeloHospedes.addRow(new Object[] {hospede.getId(), hospede.getNome(), hospede.getSobrenome(), 
						 hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getReserva_id()});
				 
			 }
			 
		 } catch(Exception e) {
			 
			 throw e;
			 
		 }
		 
	 }
	    
	 private List<Hospede> listarHospede(){
		 
		 return this.hospedeController.listar();
		 
	 }
	 
	 
	 private void alterarHospede() {
		 
		 Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
		 
		 // foi necessario enviar as datas como Object pois estava dando uma exception de cast com string
		 if(objetoDaLinha instanceof Integer) {
			 
			 Integer id = (Integer) objetoDaLinha;
			 String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
			 String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
			 Object dataNascimento = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3);
			 String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
			 String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
			 this.hospedeController.alterar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
			 JOptionPane.showMessageDialog(this, "Dados do hospede alterados com sucesso!");
			 
		 } else {
			 
			 JOptionPane.showMessageDialog(this, "Por favor, selecione o ID antes de tentar alterar." );
			 
		 }
		 
	 }
	 
	 private void deletarHospede() {
		 
		 
			 
			 Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
			 
			 if(objetoDaLinha instanceof Integer) {
				 
				 Integer id = (Integer) objetoDaLinha;
				 this.hospedeController.deletar(id);
				 modeloHospedes.removeRow(tbHospedes.getSelectedRow());
				 JOptionPane.showMessageDialog(this, "Hospede excluido com sucesso!");
				 
			 } else {
				 
				 JOptionPane.showMessageDialog(this, "Por favor, selecione o ID antes de tentar excluir.");
				 
			 }
			 
		 }
	 
	 private void buscarPorNumeroDaReserva(String texto) {
		 
		 if(!texto.equals("") && texto != null) {
			 
			 Integer id = Integer.valueOf(texto);
			 List<Reserva> reserva = this.reservaController.buscar(id);
			 List<Hospede> hospede = this.hospedeController.buscar(id);
			 
			try { 
				
				 for(Reserva result : reserva) {
					 
					 modelo.addRow(new Object[] {result.getId(), result.getDataEntrada(), 
							 result.getDataSaida(), result.getValor(), result.getFormaDePagamento()});
					 
				 }
				 
				 for(Hospede result : hospede) {
					 
					 modeloHospedes.addRow(new Object[] {result.getId(), result.getNome(), result.getSobrenome(), 
							 result.getDataNascimento(), result.getNacionalidade(), result.getTelefone(), result.getReserva_id()});
					 
				 }
				 
			} catch(Exception e) {
				
				throw e;
				
			}
		 }
		 
	 }
	 
	 private void buscarPorSobrenome(String texto) {
		 
		 if(!texto.equals("") && texto != null) {
			 
			 String sobrenome = texto;
			 List<Hospede> hospede = this.hospedeController.buscar(sobrenome);
			 
			 try {
				 
				 for(Hospede result : hospede) {
					 
					 modeloHospedes.addRow(new Object[] {result.getId(), result.getNome(), result.getSobrenome(), 
							 result.getDataNascimento(), result.getNacionalidade(), result.getTelefone(), result.getReserva_id()});
					 
				 }
				 
			 } catch(Exception e) {
				 
				 throw e;
				 
			 }
		 }
		 
	 }
	 
	 private void limparTabelaReserva() {
		 
		 modelo.getDataVector().clear();
		 
	 }
	 
	 private void limparTabelaHospede() {
		 
		 modeloHospedes.getDataVector().clear();
		 
	 }

	
}
