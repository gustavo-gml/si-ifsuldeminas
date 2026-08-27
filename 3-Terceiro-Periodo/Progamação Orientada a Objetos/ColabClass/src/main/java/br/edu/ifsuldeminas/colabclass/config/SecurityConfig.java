package br.edu.ifsuldeminas.colabclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifsuldeminas.colabclass.service.UsuarioDetailsService;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(
        UsuarioDetailsService usuarioDetailsService,
        LoginSuccessHandler loginSuccessHandler) {

    this.usuarioDetailsService = usuarioDetailsService;
    this.loginSuccessHandler = loginSuccessHandler;
}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // 🔓 PÚBLICO (sem login)
                .requestMatchers(
                        "/",
                        "/login",
                        "/usuario/novo",
                        "/usuario/salvar",
                        "/css/**")
                .permitAll()

                // 🔐 ÁREAS LOGADAS (qualquer usuário autenticado)
                .requestMatchers(
                        "/dashboard",
                        "/home",
                        "/usuario/perfil",
                        "/turma/**")
                .authenticated()

                // 🔐 ADMIN ONLY
                .requestMatchers(
                        "/admin/**",
                        "/usuario/listar",
                        "/usuario/admin/**",
                        "/usuario/representante/**",
                        "/usuario/aluno/**")
                .hasRole("ADMIN")

                // 🔐 PROFESSOR / REPRESENTANTE / ADMIN
                .requestMatchers(
                        "/representante/**",
                        "/aviso/**",
                        "/disciplina/**",
                        "/link/**")
                .hasAnyRole("REPRESENTANTE", "PROFESSOR", "ADMIN")

                // 🔥 EVENTOS (CRUD só gestores)
                .requestMatchers("/evento/novo")
                .hasAnyRole("PROFESSOR", "ADMIN", "REPRESENTANTE")

                .requestMatchers("/evento/editar/**")
                .hasAnyRole("PROFESSOR", "ADMIN", "REPRESENTANTE")

                .requestMatchers("/evento/excluir/**")
                .hasAnyRole("PROFESSOR", "ADMIN", "REPRESENTANTE")

                // ✅ ENTREGA (ALUNO PRECISA ESTAR LOGADO)
                .requestMatchers("/evento/entregar/**")
                .authenticated()

                // resto
                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .permitAll()
            )

            .exceptionHandling(exception ->
                exception.accessDeniedPage("/403")
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    AuthenticationProvider authenticationProvider() {

        
    DaoAuthenticationProvider provider =
            new DaoAuthenticationProvider();

    provider.setUserDetailsService(
            usuarioDetailsService);

    provider.setPasswordEncoder(
            passwordEncoder());

    return provider;
}

}