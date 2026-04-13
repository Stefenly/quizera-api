package co.istad.quizera.project.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    public static final String SECRET = "mysecretkeymysecretkeymysecretkey";
    public static final long EXPIRATION = 86400000; // 1 day
}