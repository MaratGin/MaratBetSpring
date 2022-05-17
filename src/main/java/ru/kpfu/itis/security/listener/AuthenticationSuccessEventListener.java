package ru.kpfu.itis.security.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.controllers.ChatController;
import ru.kpfu.itis.services.LoginAttemptService;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private HttpServletRequest request;
    private final Logger log = LoggerFactory.getLogger(AuthenticationSuccessEventListener.class);


    @Autowired
    private LoginAttemptService loginAttemptService;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        log.info("Successful login");
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptService.loginSucceeded(request.getRemoteAddr());
        } else {
            loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }
}
