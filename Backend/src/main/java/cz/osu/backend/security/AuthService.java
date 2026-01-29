package cz.osu.backend.security;

import cz.osu.backend.model.db.User;
import cz.osu.backend.model.dto.auth.LoginRequestDTO;
import cz.osu.backend.model.dto.auth.RegisterRequestDTO;
import cz.osu.backend.model.enums.UserRole;
import cz.osu.backend.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(RegisterRequestDTO request) throws BadRequestException {
        if (userRepository.getUserByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Chyba: Uživatel s tímto jménem už existuje!");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        UserRole role = request.getRole();
        newUser.setRole(role);

        userRepository.save(newUser);

        return "Uživatel úspěšně registrován!";
    }

    public String loginUser(LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        return token;
    }
}
