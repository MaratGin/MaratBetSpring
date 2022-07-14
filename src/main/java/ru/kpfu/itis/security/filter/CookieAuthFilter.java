package ru.kpfu.itis.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.kpfu.itis.controllers.ChatController;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.security.authentification.CookieAuthentication;
import ru.kpfu.itis.security.details.UserDetailsImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CookieAuthFilter extends GenericFilterBean {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    @Qualifier("customUserDetailsImpl")
    private UserDetailsService userDetailsService;
    private final Logger log = LoggerFactory.getLogger(CookieAuthFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
       log.info("filter!");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth")) {
                    Optional<Auth> authOptional = authRepository.findByCookieValue(cookie.getValue());
                    if (authOptional.isPresent()) {
                        String email = authOptional.get().getUser().getEmail();
                        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                        CookieAuthentication cookieAuthentication = new CookieAuthentication(cookie.getValue());
                        cookieAuthentication.setUserDetails((UserDetailsImpl) userDetails);
                        cookieAuthentication.setAuthenticated(true);

                        SecurityContextHolder.getContext().setAuthentication(cookieAuthentication);
                    }
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
