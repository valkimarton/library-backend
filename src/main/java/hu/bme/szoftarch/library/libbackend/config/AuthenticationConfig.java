package hu.bme.szoftarch.library.libbackend.config;

import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;
import hu.bme.szoftarch.library.libbackend.security.JwtAuthenticationFilter;
import hu.bme.szoftarch.library.libbackend.security.JwtAuthorizationFilter;
import hu.bme.szoftarch.library.libbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(        // TODO: Is this needed?
        prePostEnabled = true
)
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username,password,enabled from lib_user where username=?")    // setting the users table: 'customer'
                .authoritiesByUsernameQuery("SELECT lib_user.username as username, role.name as role FROM lib_user INNER JOIN user_roles ON lib_user.id=user_roles.user_id INNER JOIN role ON user_roles.role_id=role.id WHERE lib_user.username=?");            // setting the authorities table: 'user_roles'
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                // EVERYONE
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/writing/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/writing/sort/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/writing/recommend/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/image/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/api/writing").permitAll()      // TODO: move to ADMIN
                .antMatchers(HttpMethod.POST, "/api/writing/**").permitAll()
                // USER
                .antMatchers(HttpMethod.GET).hasAuthority(RoleType.USER.name())    // TODO: this helps now, but should be reviewed later
                // ADMIN
                //.antMatchers(HttpMethod.POST, "/api/writing").hasAuthority(RoleType.ADMIN.name())
                // DENY ALL OTHER
                .antMatchers("**").denyAll()                            // Denies every other request
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
    }


}
