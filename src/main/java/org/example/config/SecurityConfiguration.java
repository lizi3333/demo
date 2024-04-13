package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration   {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository repository(DataSource dataSource){
        JdbcTokenRepositoryImpl repository=new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
    @Bean
    public SecurityFilterChain chain(HttpSecurity http,PersistentTokenRepository repository) throws Exception {
        return http
                .authorizeHttpRequests(auth->{
                        auth.requestMatchers("/static/**").permitAll();
                        auth.anyRequest().authenticated();
                })
                .formLogin(conf->{
                    conf.loginPage("/login");
                    conf.loginProcessingUrl("/doLogin");
                    conf.defaultSuccessUrl("/");
                    conf.permitAll();
                })
                .logout(conf->{
                    conf.logoutUrl("/logout");
                    conf.logoutSuccessUrl("/login");
                    conf.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(conf->{
                    conf.alwaysRemember(false);
                    conf.tokenRepository(repository);
                    conf.tokenValiditySeconds(3600 * 24 *7);
                })
                .build();
    }
}
