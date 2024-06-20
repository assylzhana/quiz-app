package assylzhan.project.quizapp.config;


import assylzhan.project.quizapp.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService())
                .passwordEncoder(passwordEncoder());

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(flc -> flc.loginProcessingUrl("/auth")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/sign-in")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/sign-in?error"));

        httpSecurity.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/sign-in"));


        return httpSecurity.build();
    }
}