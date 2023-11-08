package it.unical.demacs.FundasticServer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Specifica il percorso a cui applicare le politiche CORS (Cross-Origin Resource Sharing)
                .allowedOrigins("http://localhost:4200") // Specifica l'origine del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specifica i metodi HTTP consentiti
                .allowedHeaders("*"); // Specifica gli header consentiti
    }
}
