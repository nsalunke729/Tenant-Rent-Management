package com.springRest.RestAPI.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate the username and password
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            // Return a dummy token for now
            return ResponseEntity.ok(new LoginResponse("dummy-token"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> logout() {
        // Logout logic here
        return ResponseEntity.ok("Logged out successfully");
    }

    // Inner class for LoginRequest
    public static class LoginRequest {
        private String username;
        private String password;
        public Object getUsername() {
            return username;
        }
        public Object getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Inner class for LoginResponse
    public static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        // Getter
        public String getToken() {
            return token;
        }
    }
}
