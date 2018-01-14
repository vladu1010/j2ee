package net.homecredit.comp.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author vladut.stoica
 */
@Configuration
@Profile("!local")
public class ManagedContext {

    @Value("${managedDs.jndiName}")
    String dataSourceJndiName;

    @Bean(name = "rawDataSource", destroyMethod = "")
    public DataSource getDataSource() throws SQLException {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(dataSourceJndiName);
    }
}
