package cz.osu.backend.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    private final JwtUtil jwtUtil = new JwtUtil();

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