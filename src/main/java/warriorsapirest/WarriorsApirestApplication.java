package warriorsapirest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WarriorsApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsApirestApplication.class, args);
    }

    @Bean
    public ObjectMapper jacksonBuilder() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.registerModule(new VavrModule());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:63342");
            }
        };
    }


    @Configuration
    //@EnableWebMvc
    @ComponentScan()
    public class RestConfigTest  {
    }

}