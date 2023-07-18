package org.jio.fyoga.config;/*  Welcome to Jio word
    @author: Jio
    Date: 7/10/2023
    Time: 11:09 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Bật tính năng web security lên
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { // Dùng để mã hóa mật khẩu với thuật toán Bcrypt
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() { //Giả lập tạo 2 account: 1 USER và 1 ADMIN
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {// Cấu hình security ở đây
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/FYoGa/Login/LoginGoogle").authenticated()
                        .anyRequest().permitAll()


                ) .oauth2Login((oauth2) -> oauth2 // Cấu hình xác thực OAuth2
                        //.loginPage("/login")
                        .permitAll()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());


        return httpSecurity.build();
    }
}
