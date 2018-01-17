package ro.stoicaVlad.Dreamcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import ro.stoicaVlad.Dreamcar.domain.User;
import ro.stoicaVlad.Dreamcar.service.IUserService;

import java.util.List;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configure access
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * Configure in-memory users
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        List<User> users = iUserService.listUsers();
//
//        for (User user : users) {
//            auth.inMemoryAuthentication()
//                    .withUser(user.getName()).password(user.getPassword()).roles("USER");
//        }
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
        auth.userDetailsService(userDetailsService);
    }

}