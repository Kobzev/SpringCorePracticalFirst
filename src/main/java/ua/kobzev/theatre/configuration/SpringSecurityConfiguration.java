package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import ua.kobzev.theatre.enums.UserRoles;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kostiantyn_Kobziev on 3/23/2016.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String USERS_BY_USERNAME_QUERY = "SELECT email,password,1 from users where email = ?";
    private static String ROLE_PREFIX = "ROLE_";
    private static String AUTHORITIES_BY_USERNAME_QUERY = "SELECT username, role from roles where username =?  ";

    @Autowired
    private DriverManagerDataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        System.out.println("user: " + passwordEncoder.encode("user"));
        System.out.println("admin: " + passwordEncoder.encode("admin"));
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .rolePrefix(ROLE_PREFIX)
                .usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers("/users/**","/upload", "/uploadfile", "/reports/**").hasRole(UserRoles.BOOKING_MANAGER.name())
                .antMatchers("/events/**", "/assignedevents/**", "/auditoriums/**").hasRole(UserRoles.RESGISTERED_USER.name())
                .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .and().formLogin();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    private static class CsrfHeaderFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                    .getName());
            if (csrf != null) {
                Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                String token = csrf.getToken();
                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                    cookie = new Cookie("XSRF-TOKEN", token);
                    cookie.setPath(request.getServletPath());
                    response.addCookie(cookie);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}
