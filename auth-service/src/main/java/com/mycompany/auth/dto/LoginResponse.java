package com.mycompany.auth.dto;

public class LoginResponse {

	private boolean success;
    private String token;
    private String refreshToken;
    private String message;

    public LoginResponse() {
    	
    }
    
    public LoginResponse(boolean success, String token, String refreshToken, String message) {
		super();
		this.success = success;
		this.token = token;
		this.refreshToken = refreshToken;
		this.message = message;
	}

	public static LoginResponse success(String token, String refreshToken) {
        return new LoginResponse(true, token, refreshToken, "Login successful");
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, null, null, message);
    }
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
