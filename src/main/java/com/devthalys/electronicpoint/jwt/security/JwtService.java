package com.devthalys.electronicpoint.jwt.security;

import com.devthalys.electronicpoint.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expirationToken;

    @Value("${security.jwt.signature-key}")
    private String assignatureKey;

    public String generateToken(UserModel user){
        long expiration = Long.valueOf(expirationToken);
        LocalDateTime now = LocalDateTime.now().plusMinutes(expiration);
        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, assignatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(assignatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenIsValid(String token){
        try {
            Claims claims = getClaims(token);
            Date date = claims.getExpiration();
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e){
            return false;
        }
    }

    public String getUsername(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }
}
