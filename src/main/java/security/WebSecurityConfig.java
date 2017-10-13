package security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
//        http.authorizeRequests().anyRequest().permitAll()
//                .and()
//                .csrf().disable();
//        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/h2/**").permitAll()
//                .antMatchers("/secured").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/h2/**")
                .and()
                .headers().frameOptions().disable();
//                .and()
//            .httpBasic();
    }

    // 2
    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth
//            .jdbcAuthentication()
//                .dataSource(dataSource);
//		.usersByUsernameQuery(
//			"select username,password, enabled from user where username=?")
//		.authoritiesByUsernameQuery(
//			"select username, role from user where username=?");
//        auth
//            .inMemoryAuthentication()
//            .withUser("user").password("password").roles("USER");
    }
//    // 3
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("user").password("password").roles("USER");
//    }
// // 1
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//        return manager;
//    }
}
