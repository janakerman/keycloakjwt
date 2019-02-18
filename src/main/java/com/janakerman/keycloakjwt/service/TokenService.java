package com.janakerman.keycloakjwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**

 CREATE CA AND TRUST CHAIN

 2004  openssl genrsa -des3 -out rootCA.key 4096
 2005  openssl req -x509 -new -nodes -key rootCA.key -sha256 -days 1024 -out rootCA.crt


 GENERATE CERT TO UPLOAD TO KEYCLOAK

 2022  openssl req -new -key keycloak.key -out keycloak.csr
 2023  openssl x509 -req -in keycloak.csr -CA rootCA.crt -CAkey rootCA.key -CAcreateserial -out keycloak.crt -days 500 -sha256

 GET PUBLIC KEY

 2035  cat keycloak.crt | openssl x509 -pubkey -noout

 */
@Service
public class TokenService {

    private final KeycloakConfig keycloakConfig;

    public TokenService(@Autowired KeycloakConfig config) {
        this.keycloakConfig = config;
    }

    public AccessTokenResponse getAccessToken() {
        return keycloakInstance().tokenManager().getAccessToken();
    }

    public boolean validateClaims(AccessTokenResponse response) {
        String jwt = response.getToken();

        byte[] byteKey = Base64.getDecoder().decode(jwt.getBytes());
        X509EncodedKeySpec xpk = new X509EncodedKeySpec(byteKey);

        PublicKey publicKey;
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = kf.generatePublic(xpk);

            Jws<Claims> claims = Jwts.parser()
                    .setAllowedClockSkewSeconds(1000)
                    .setSigningKey(publicKey)
                    .parseClaimsJws(jwt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return false;
        }

        return true;
    }

    private Keycloak keycloakInstance() {
        System.out.println(keycloakConfig.keycloakUrl);
        return KeycloakBuilder.builder()
            .realm(keycloakConfig.realm)
            .serverUrl(keycloakConfig.keycloakUrl)
            .clientId(keycloakConfig.clientId)
            .clientSecret(keycloakConfig.clientSecret)
            .username(keycloakConfig.username)
            .password(keycloakConfig.password)
            .build();
    }

    private String getKeycloakPublicKey() {
        // TODO: Extract from header / request from KC.
        return "";
    }

}
