package pl.przemek.czerw.springjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.przemek.czerw.springjwt.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    public String signup(User user) throws Exception {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtUtil.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new Exception("Username is already in use");
        }
    }

}
