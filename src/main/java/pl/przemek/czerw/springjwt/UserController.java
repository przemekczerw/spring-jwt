package pl.przemek.czerw.springjwt;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.przemek.czerw.springjwt.models.AuthenticationRequest;
import pl.przemek.czerw.springjwt.models.AuthenticationResponse;
import pl.przemek.czerw.springjwt.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtilToken;

    @RequestMapping("/hello")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/helloUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser() {
        return "Hello user";
    }

    @RequestMapping("/helloAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin() {
        return "Hello admin";
    }

//    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtilToken.generateToken(userDetails);

        response.addHeader("Authorization", "Baerer " + jwt);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
