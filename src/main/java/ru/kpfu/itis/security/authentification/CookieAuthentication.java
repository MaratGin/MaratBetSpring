package ru.kpfu.itis.security.authentification;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.kpfu.itis.security.details.UserDetailsImpl;

import java.util.Collection;

public class CookieAuthentication implements Authentication {

    private boolean isAuthenticated;
    private UserDetailsImpl userDetails;
    private String cookieValue;

    public CookieAuthentication(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUser();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return cookieValue;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }
}
