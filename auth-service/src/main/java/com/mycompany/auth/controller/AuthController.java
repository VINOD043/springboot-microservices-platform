package com.mycompany.auth.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.auth.dto.LoginRequest;
import com.mycompany.auth.dto.LoginResponse;
import com.mycompany.auth.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private final Map<String, String> refreshTokenStore = new ConcurrentHashMap();
	
	@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtUtil.generateToken(request.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());
            
            refreshTokenStore.put(refreshToken, request.getUsername());
            
            return ResponseEntity.ok(LoginResponse.success(token, refreshToken));
        } catch (AuthenticationException e) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(LoginResponse.failure("Invalid username or password"));
        }
    }
	
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
	    try {
	    	System.out.println("Validating token");
	    	System.out.println("Received Authorization header: " + authHeader);
	        String token = authHeader.replace("Bearer ", "");
	        if (jwtUtil.validateToken(token)) {
	            String username = jwtUtil.extractUsername(token);
	            return ResponseEntity.ok(Map.of("valid", true, "username", username));
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body(Map.of("valid", false, "message", "Invalid token"));
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body(Map.of("valid", false, "message", "Token validation failed"));
	    }
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
	    String refreshToken = body.get("refreshToken");
	    if (refreshToken == null || !jwtUtil.validateRefreshToken(refreshToken)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("success", false, "message", "Invalid refresh token"));
	    }

	    String username = jwtUtil.extractUsername(refreshToken);
	    if (!refreshTokenStore.containsKey(refreshToken)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("success", false, "message", "Refresh token not found"));
	    }

	    String newAccessToken = jwtUtil.generateToken(username);
	    return ResponseEntity.ok(Map.of(
	            "success", true,
	            "token", newAccessToken,
	            "refreshToken", refreshToken
	    ));
	}


}
