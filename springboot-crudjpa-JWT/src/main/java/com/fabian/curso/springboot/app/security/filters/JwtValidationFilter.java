package com.fabian.curso.springboot.app.security.filters;

import com.fabian.curso.springboot.app.security.SimpleGrantedAuthorityJsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.fabian.curso.springboot.app.security.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JwtValidationFilter extends BasicAuthenticationFilter
{
    public JwtValidationFilter(AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIX_TOKEN))
        {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");
        try
        {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String nombre = claims.getSubject();
            String authoritiesClaims = (String) claims.get("authorities");
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                    //El addMIxIn para sobre escribir el constructor de una clase en este caso por que va a bucar la palabra role, pero en nuestros claims los roles los tenemos como authorities
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.getBytes(), SimpleGrantedAuthority[].class));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(nombre, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e)
        {
            Map<String,String> json = new HashMap<>();
            json.put("error",e.getMessage());
            json.put("message","El token no es valido");

            response.getWriter().write(new ObjectMapper().writeValueAsString(json));
            response.setStatus(401);
            response.setContentType(CONTENT_TYPE);
        }
    }
}
