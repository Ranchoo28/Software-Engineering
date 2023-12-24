package it.unical.demacs.FundasticServer.Config;

import it.unical.demacs.FundasticServer.Users.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/**").permitAll();
                    //auth.requestMatchers("/user").hasRole(String.valueOf(Role.Utente));
                    //auth.requestMatchers("/moderator").hasRole(String.valueOf(Role.Moderatore));
                    //auth.requestMatchers("/publisher").hasRole(String.valueOf(Role.Publisher));
                    //auth.requestMatchers("/finanziatore").hasRole(String.valueOf(Role.Finanziatore));
                })
                .httpBasic(withDefaults())
                .build();
    }
}


