package aplicacion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import aplicacion.servicio.imp.UsuarioServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	UsuarioServiceImp userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	           
	            http.authorizeRequests()
	                    .antMatchers("/","/alumnos").permitAll()
	                    .antMatchers("/usuarios").hasRole("ADMIN") 
	                    .antMatchers("/categorias").authenticated()
	                    //.regexMatchers("/usuarios/.*").hasRole("ADMIN")
	                    .and()
	                    .formLogin()
	                        .loginPage("/login")
	                        .permitAll()
	                        .defaultSuccessUrl("/")
	                        .failureUrl("/login?error=true")
	                        .usernameParameter("username")
	                        .passwordParameter("password")
	                        .and()
	                    .logout()
	                        .permitAll()
	                        .logoutUrl("/logout")
	                        .logoutSuccessUrl("/login/logout")
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                        .and()
	                   
	                    .csrf().disable();
	           
	    }
	   
	   
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
	    }
}
