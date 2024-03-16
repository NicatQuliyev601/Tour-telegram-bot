package az.nicat.tourbotapi.config;


import az.nicat.tourbotapi.security.AuthFilterConfigurerAdapter;
import az.nicat.tourbotapi.security.JwtAuthenticationEntryPoint;
import az.nicat.tourbotapi.security.TokenAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final TokenAuthService tokenAuthService;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/requests/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/agent-requests/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/archives/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/offers/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/**").authenticated());

        http.apply(new AuthFilterConfigurerAdapter(tokenAuthService));
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
