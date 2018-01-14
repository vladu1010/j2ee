package net.homecredit.comp.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.homecredit.comp.aop.AuditInterceptor;
import net.homecredit.comp.aop.LoggingInterceptor;
import net.homecredit.comp.config.PersistenceConfig;
import net.homecredit.comp.monitoring.MonitoringConfig;
import net.homecredit.comp.security.config.RestSsoSecurityConfig;
import net.homecredit.comp.security.config.SecurityConfig;
import net.homecredit.comp.security.config.WebSsoSecurityConfig;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({
        LocalContext.class,
        ManagedContext.class,
        PersistenceConfig.class,
        MonitoringConfig.class,
        SecurityConfig.class,
        RestSsoSecurityConfig.class,
        WebSsoSecurityConfig.class,
})
@ComponentScan({"net.homecredit.**.dao", "net.homecredit.**.web", "net.homecredit.**.service", "net.homecredit.**.rest", "net.homecredit.**.aop"})
public class RootConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    public RegexpMethodPointcutAdvisor exceptionLoggingInterceptorAdvisor(LoggingInterceptor loggingInterceptor) {
        RegexpMethodPointcutAdvisor toReturn = new RegexpMethodPointcutAdvisor();
        toReturn.setAdvice(loggingInterceptor);
        String[] logExcPatterns = new String[]{"net.homecredit.comp.rest.*","net.homecredit.comp.web.*","net.homecredit.comp.service.*", "net.homecredit.comp.dao.*"};
        toReturn.setPatterns(logExcPatterns);
        return toReturn;
    }

    @Bean
    public AuditInterceptor auditInterceptor() {
        return new AuditInterceptor();
    }

    @Bean
    public RegexpMethodPointcutAdvisor auditInterceptorAdvisor(AuditInterceptor auditInterceptor) {
        RegexpMethodPointcutAdvisor toReturn = new RegexpMethodPointcutAdvisor();
        toReturn.setAdvice(auditInterceptor);
        String[] auditPatterns = new String[]{"net.homecredit.comp.rest.*"};
        toReturn.setPatterns(auditPatterns);
        toReturn.setOrder(1);
        return toReturn;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
