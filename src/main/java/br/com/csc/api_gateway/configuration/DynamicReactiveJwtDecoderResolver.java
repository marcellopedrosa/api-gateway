package br.com.csc.api_gateway.configuration;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Resolves and validates JWT tokens dynamically based on the issuer.
 * It uses a cache to store decoders for each issuer and validates the issuer against allowed values
 */
@Component
public class DynamicReactiveJwtDecoderResolver {

    private final Map<String, ReactiveJwtDecoder> cache = new ConcurrentHashMap<>();
    private final Set<String> allowedIssuers;

    public DynamicReactiveJwtDecoderResolver(
            @Value("${SPI_URI_SERVER}") String issuer1
            /*,@Value("${SPI_URI_SERVER_QA}") String issuer2*/) {
        //this.allowedIssuers = Set.of(issuer1, issuer2);
        this.allowedIssuers = Set.of(issuer1);
    }

    /**
     * Pode validar por uma lista predefinida de issues (varios oauth2 server / separação física)
     * @param token
     * @return
     */
    public Mono<Jwt> decodeV2(String token) {
        try {
            // 1. Extrai o issuer do token (sem validar ainda)
            SignedJWT parsed = SignedJWT.parse(token);
            String issuer = parsed.getJWTClaimsSet().getIssuer();

            if (issuer == null || issuer.isBlank()) {
                return Mono.error(new JwtException("Issuer (iss) não encontrado no token"));
            }

            // 2. Validação explícita do issuer com base nas variáveis de ambiente
            if (!allowedIssuers.contains(issuer)) {
                return Mono.error(new JwtException("Issuer não autorizado: " + issuer));
            }

            // 3. Resolver ou criar o decoder com JWKS
            ReactiveJwtDecoder decoder = cache.computeIfAbsent(issuer, iss -> {
                String jwksUri = iss + "/protocol/openid-connect/certs";
                NimbusReactiveJwtDecoder nimbusDecoder = NimbusReactiveJwtDecoder.withJwkSetUri(jwksUri).build();

                // 4. Adiciona um validador que reforça o issuer esperado
                OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(iss);
                nimbusDecoder.setJwtValidator(validator);

                return nimbusDecoder;
            });

            // 5. Decodifica e valida o token
            return decoder.decode(token);

        } catch (ParseException e) {
            return Mono.error(new JwtException("Token JWT malformado", e));
        }
    }

    /**
     * Apenas pega o iss do token e bypassa (utiliza isolamento lógico usando realms)
     * @param token
     * @return
     */
    public Mono<Jwt> decodeV1(String token) {
        try {
            // Extrai o issuer do token (sem validar assinatura ainda)
            SignedJWT signedJwt = SignedJWT.parse(token);
            String issuer = signedJwt.getJWTClaimsSet().getIssuer();

            if (issuer == null || issuer.isBlank()) {
                return Mono.error(new JwtException("Issuer (iss) ausente no token"));
            }

            // Gera a URI do JWKS
            String jwksUri = issuer + "/protocol/openid-connect/certs";

            // Usa cache para evitar overhead
            ReactiveJwtDecoder decoder = cache.computeIfAbsent(issuer,
                    iss -> NimbusReactiveJwtDecoder.withJwkSetUri(jwksUri).build());

            return decoder.decode(token);

        } catch (ParseException e) {
            return Mono.error(new JwtException("Token malformado", e));
        }
    }
}
