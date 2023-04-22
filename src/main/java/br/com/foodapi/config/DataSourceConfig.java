package br.com.foodapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	
	/** DataSource é uma interface com uma padronização de conexão do banco de dados mais segura (permite pool de conexões) .
	 * 	Evita a criação e destruição excessiva de conexões com o banco de dados.
	 * 
	 */
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(user);
		config.setPassword(password);
		config.setIdleTimeout(300000); //5min
		config.setMaxLifetime(600000); //10min
		config.setConnectionTestQuery("SELECT 1");
		return new HikariDataSource(config);
	}
}
