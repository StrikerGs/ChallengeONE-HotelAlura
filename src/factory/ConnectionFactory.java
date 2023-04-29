package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;
	
	public ConnectionFactory() {
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		// tive um problema com as datas mostradas do banco de dados e o problema era o Timezone=UTC, mudei para GMT-3 e resolveu
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=GMT-3");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("");
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
		
	}
	
	public Connection recuperarConexao() {
		
		try {
			
			return this.dataSource.getConnection();
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
