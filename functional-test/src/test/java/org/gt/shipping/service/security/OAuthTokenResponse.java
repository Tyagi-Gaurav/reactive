package org.gt.shipping.service.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import static org.gt.shipping.service.security.ImmutableOAuthTokenResponse.*;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Style(builder = "new") 
@JsonSerialize
@JsonDeserialize(builder = Builder.class)
public interface OAuthTokenResponse {
    @JsonProperty("access_token") String accessToken();

    @JsonProperty("token_type") String tokenType();

    @JsonProperty("refresh_token") String refreshToken();

    @JsonProperty("expires_in") int expiresIn();

    String scope();

    String organization();

    String jti();
}
