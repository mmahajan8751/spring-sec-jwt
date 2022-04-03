package com.devmnack.springsecjwt.service;

import com.devmnack.springsecjwt.model.AuthenticationRequest;
import com.devmnack.springsecjwt.model.AuthenticationResponse;
import com.devmnack.springsecjwt.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    public AuthenticationResponse performAuthentication(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            logger.info("Authentication in progress...");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserName(),
                    authenticationRequest.getPassword()));
            logger.info("Authentication completed");

        } catch (BadCredentialsException e) {
            logger.error("Authentication error");
            throw new Exception("Invalid username/password" + e);
        }

        // At this point, authentication is completed successfully - Based on userdetails generate token
        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken);
    }
}
