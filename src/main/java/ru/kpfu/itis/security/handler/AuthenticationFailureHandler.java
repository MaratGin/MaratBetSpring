package ru.kpfu.itis.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import ru.kpfu.itis.controllers.ChatController;
import ru.kpfu.itis.security.listener.AuthenticationFailureListener;
import ru.kpfu.itis.services.LoginAttemptService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private MessageSource messages;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private AuthenticationFailureListener authentificationFailureListener;
    private final Logger log = LoggerFactory.getLogger(AuthenticationFailureHandler.class);


    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
       log.info("Authentification failed!");
        setDefaultFailureUrl("/signIn?error");
        super.onAuthenticationFailure(request, response, exception);
        System.out.println("Failure Handler");
        final Locale locale = Locale.ENGLISH;
//        final Locale locale = localeResolver.resolveLocale(request);
        System.out.println(locale.getLanguage());
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptService.loginFailed(request.getRemoteAddr());
        } else {
            loginAttemptService.loginFailed(xfHeader.split(",")[0]);
        }
//        String errorMessage = messages.getMessage("message.badCredentials", null, locale);


//        final Locale locale = localeResolver.resolveLocale(request);
//
//
//        String errorMessage = messages.getMessage("message.badCredentials", null, locale);
//
//        if (exception.getMessage()
//                .equalsIgnoreCase("User is disabled")) {
//            errorMessage = messages.getMessage("auth.message.disabled", null, locale);
//        } else if (exception.getMessage()
//                .equalsIgnoreCase("User account has expired")) {
//            errorMessage = messages.getMessage("auth.message.expired", null, locale);
//        } else if (exception.getMessage()
//                .equalsIgnoreCase("blocked")) {
//            errorMessage = messages.getMessage("auth.message.blocked", null, locale);
//        } else if (exception.getMessage()
//                .equalsIgnoreCase("unusual location")) {
//            errorMessage = messages.getMessage("auth.message.unusual.location", null, locale);
//        }
        request.getSession()
                .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "badCredentials");
//        response.sendRedirect("/signIn?error");
//        request.getSession()
//                .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "badCredentials");
    }
}
