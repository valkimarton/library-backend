package hu.bme.szoftarch.library.libbackend.security;

public final class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "F)J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkYp2s5v8y/B?E(H+MbQeThWmZq4t6w9z";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final int TOKEN_EXPIRATION = 10;   // minutes

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
