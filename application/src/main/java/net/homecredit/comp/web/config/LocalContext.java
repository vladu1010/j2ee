package net.homecredit.comp.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author vladut.stoica
 */
@Configuration
@Profile("local")
@PropertySource("classpath:configuration.properties")
public class LocalContext {

    @Value("${localDs.username}")
    String userName;

    @Value("${localDs.password}")
    String password;

    @Value("${localDs.url}")
    String url;

    @Value("${localDs.driverClassName}")
    String driverClassName;

    @Bean(name = "rawDataSource")
    public DataSource getDataSource() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
