package ru.kpfu.itis.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/main").authenticated()
                .and()
                .formLogin()
                .loginPage("/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
//                .successHandler(successfulAuthenticationHandler)
                .failureUrl("/signIn?error")
                .permitAll()
                //.defaultSuccessUrl("/profile")
                .and()
                .logout().logoutSuccessUrl("/signIn").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                /*.and()
                .antMatchers("/signUp", "/signIn", "/main").permitAll()
                .antMatchers("/profile", "/order").hasAnyRole(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name())
                .antMatchers("/admin").hasRole(Role.ROLE_ADMIN.name());*/
//        http.addFilterAfter(cookieAuthFilter, UsernamePasswordAuthenticationFilter.class);


}
}
