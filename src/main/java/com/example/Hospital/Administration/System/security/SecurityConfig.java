package com.example.Hospital.Administration.System.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Consider enabling in production
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login/**","/registration","/registration/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/receptionist/**").hasRole("RECEPTIONIST")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/pharmacist/**").hasRole("PHARMACIST")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .successHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.setAttribute("username", authentication.getName());

                            // Redirect based on role
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                            for (GrantedAuthority authority : authorities) {
                                if (authority.getAuthority().equals("ROLE_DOCTOR")) {
                                    response.sendRedirect("/doctor/doctor-home");
                                    return;
                                } else if (authority.getAuthority().equals("ROLE_PHARMACIST")) {
                                    response.sendRedirect("/pharmacist/pharmacist-home");
                                    return;
                                } else if (authority.getAuthority().equals("ROLE_RECEPTIONIST")) {
                                    response.sendRedirect("/reception/reception-home");
                                    return;
                                }
                            }
                            // Default fallback
                            response.sendRedirect("/");
                        })
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
