package net.homecredit.comp.web.config;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;
import net.homecredit.comp.security.http.AddTraceUserFilter;
import net.homecredit.comp.http.AddTraceHeaderFilter;
import net.homecredit.comp.log.LogbackConfigurationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author vladut.stoica
 */
@PropertySource("classpath:configuration.properties")
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        container.addListener(new ContextLoaderListener(rootContext));
        container.addListener(new SessionListener());
        container.addListener(new LogbackConfigurationListener());

        String melodyName = "javamelody";
        String hciName = "hcitrace";
        String utName = "usertrace";

        FilterRegistration javamelodyFilter = container.getFilterRegistration(melodyName);
        if(javamelodyFilter == null) javamelodyFilter = container.addFilter(melodyName, MonitoringFilter.class.getName());
        javamelodyFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ASYNC, DispatcherType.REQUEST), false, "/*");
        javamelodyFilter.setInitParameter("url-exclude-pattern", "(/js/.*|/css/.*|/icons/.*|/img/.*|/fonts/.*)");
        javamelodyFilter.setInitParameter("quartz-default-listener-disabled", "true");
        javamelodyFilter.setInitParameter("displayed-counters", "http,sql,spring,services,error,log");

        FilterRegistration hciTraceFilter = container.getFilterRegistration(hciName);
        if(hciTraceFilter == null) hciTraceFilter = container.addFilter(hciName, AddTraceHeaderFilter.class.getName());
        hciTraceFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration springSecurity = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class.getName());
        springSecurity.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ERROR, DispatcherType.REQUEST), false, "/*");

        FilterRegistration userTraceFilter = container.getFilterRegistration(utName);
        if(userTraceFilter == null) userTraceFilter = container.addFilter(utName, AddTraceUserFilter.class.getName());
        userTraceFilter.addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic webServlet = container.addServlet("complaints-web", new DispatcherServlet(rootContext));
        webServlet.setLoadOnStartup(1);
        webServlet.addMapping("/*");
    }
}
