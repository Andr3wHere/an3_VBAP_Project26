package cz.osu.backend.component;

import cz.osu.backend.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "MySuperSecretKeyForJwtSigning1234567890");
        ReflectionTestUtils.setField(jwtUtil,"EXPIRATION_TIME",86400000);
        jwtUtil.init();
    }


    @Test
    void testTokenGenerationAndExtraction() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertEquals(username, jwtUtil.extractUsername(token));
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "9fda36c34c7158acb547c87255d21f32";
        assertFalse(jwtUtil.validateToken(invalidToken));
    }
}