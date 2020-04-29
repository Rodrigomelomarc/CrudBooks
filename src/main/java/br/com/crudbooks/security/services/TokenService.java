package br.com.crudbooks.security.services;

import br.com.crudbooks.user.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Configuration
public class TokenService {

    @Value("${books.jwt.expiration}")
    private String EXPIRATION_TIME;
    @Value("${books.jwt.secret}")
    private String SECRET;

    public String generateToken(Authentication authentication) {
        var actualUser = (User) authentication.getPrincipal();
        var today = new Date();
        var expirationDate = new Date(today.getTime() + Long.parseLong(EXPIRATION_TIME));
        return Jwts.builder()
                .setIssuer("Api de livros")
                .setSubject(actualUser.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Long getUserToken(String token) {
        var claims = Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
