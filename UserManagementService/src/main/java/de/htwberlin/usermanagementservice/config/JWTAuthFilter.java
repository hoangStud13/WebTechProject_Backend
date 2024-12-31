package de.htwberlin.usermanagementservice.config;


import de.htwberlin.usermanagementservice.service.JWTUtils;
import de.htwberlin.usermanagementservice.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Authentication Filter for validating JWT tokens on incoming requests.
 * This filter checks if the request contains a valid JWT token, extracts the user details,
 * and sets the authentication in the security context if the token is valid.
 */
@Component
public class JWTAuthFilter extends OncePerRequestFilter {


    private final JWTUtils jwtUtils;

    private final UserService userDetailsService;

    @Autowired
    public JWTAuthFilter(JWTUtils jwtUtils, UserService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * The main filter method that is invoked for each HTTP request.
     * It checks if the request contains a valid JWT token, and if so, sets the authentication
     * in the Spring Security context.
     *
     * @param request The HTTP request to be processed.
     * @param response The HTTP response to be returned.
     * @param filterChain The filter chain to pass the request through if no token is found or if it's invalid.
     * @throws ServletException If an error occurs during the processing of the request.
     * @throws IOException If an error occurs during input or output operations.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if(authHeader==null || authHeader.isBlank()){
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }

        }
        filterChain.doFilter(request, response);
    }
}
