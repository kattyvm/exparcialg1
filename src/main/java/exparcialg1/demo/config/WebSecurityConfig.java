package exparcialg1.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/redirectByRole", true);

        http.logout().logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.rememberMe()
                .tokenValiditySeconds(172800)
                .rememberMeParameter("rememberme");

        http.authorizeRequests().antMatchers("/admin","/admin/**").hasAuthority("Administrador")
                .antMatchers("/gestor","/gestor/**").hasAnyAuthority("Gestor")
                .antMatchers("/usuario","/usuario/**").hasAnyAuthority("Administrador","Gestor","Registrado")
                .anyRequest().permitAll();

    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT correo, pwd, enabled FROM usuarios WHERE correo = ?")
                .authoritiesByUsernameQuery("SELECT u.correo, r.nombrerol FROM usuarios u INNER JOIN " +
                        "roles r ON (u.idroles = r.idroles) WHERE u.correo = ? and u.enabled = 1");
    }
}
