package pool;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MyDataSource {
	private static HikariConfig config= new HikariConfig();
	private static HikariDataSource datasource;
	
	static {
		config.setJdbcUrl("jdbc:mysql://localhost/modeloalumnos?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false");
		config.setUsername("mar");
		config.setPassword("alumno");
		config.addDataSourceProperty("maximumPoolSize", 1);//aqui estoy configurandon el nº máximo de conexiones
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		datasource= new HikariDataSource(config);
	}
	private MyDataSource() { }
		public static Connection getConnection() throws Exception{
			return datasource.getConnection();
		
	}
}
