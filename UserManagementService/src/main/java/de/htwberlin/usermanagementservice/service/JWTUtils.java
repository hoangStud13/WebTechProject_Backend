package de.htwberlin.usermanagementservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Utility class for generating, validating, and extracting information from JSON Web Tokens (JWTs).
 * This class provides methods to generate both access and refresh tokens,
 * extract claims from a token, and validate whether the token is expired or valid.
 */
@Component
public class JWTUtils {

    private SecretKey secretKey;
    private static final long EXPIRATION_TIME = 86400000; // 24hours Reset


    /**
     * Constructs a new {@link JWTUtils} instance and initializes the secret key used for signing JWTs.
     * The secret key is derived from a base64-encoded string.
     */
    public JWTUtils() {
        String secreteString = "1902480173518075017835871083578019843027582038571807081720847108274102831982319240712084";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    /**
     * Generates a JWT for the provided user details.
     *
     * @param userDetails The details of the user for whom the token is generated.
     * @return A JWT as a string.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Generates a refresh token with additional claims for the provided user details.
     *
     * @param claims The claims to be included in the refresh token.
     * @param userDetails The details of the user for whom the refresh token is generated.
     * @return A refresh token as a string.
     */
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extracts the username (subject) from a given JWT.
     *
     * @param token The JWT from which the username will be extracted.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Extracts specific claims from a given JWT using the provided function.
     *
     * @param token The JWT from which claims will be extracted.
     * @param claimsTFunction A function that specifies the claims to extract.
     * @param <T> The type of the claims to extract.
     * @return The extracted claims.
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
    }

    /**
     * Validates the given JWT by checking if it is expired and if the username matches.
     *
     * @param token The JWT to validate.
     * @param userDetails The user details used to match the token's username.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    /**
     * Checks if the given JWT has expired.
     *
     * @param token The JWT to check for expiration.
     * @return {@code true} if the token is expired, {@code false} otherwise.
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }


}
