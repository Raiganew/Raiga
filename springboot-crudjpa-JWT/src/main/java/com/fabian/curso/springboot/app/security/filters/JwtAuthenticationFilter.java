package com.fabian.curso.springboot.app.security.filters;

import com.fabian.curso.springboot.app.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fabian.curso.springboot.app.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        User user = null;
        String nombre = null;
        String password = null;
        try
        {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            nombre = user.getNombre();
            password = user.getPassword();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(nombre, password);
        //El authenticate lo que hace es por debajo llamar el JpaUserDetailsService para validar
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException
    {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claim = Jwts.claims().add("authorities", new ObjectMapper().writeValueAsString(roles)).build();
        String token = Jwts.builder()
                .subject(user.getUsername())
                .claims(claim)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY).compact();
        //Bearer es un standar en jwt
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        Map<String, String> json = new HashMap<>();
        json.put("token", token);
        json.put("user", user.getUsername());
        json.put("message", String.format("Hola %s has iniciado session correctamente!", user.getUsername()));
        response.getWriter().write(new ObjectMapper().writeValueAsString(json));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException
    {
        Map<String, String> json = new HashMap<>();
        json.put("Message", "Error en la autenticacion username o password incorrectos!");
        json.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(json));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(401);
    }
}
