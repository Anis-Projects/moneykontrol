package util;

public class AuthenticationResult {
	private boolean isAuthenticated;
	private int userId;

	public AuthenticationResult(boolean isAuthenticated, int userId) {
		this.isAuthenticated = isAuthenticated;
		this.userId = userId;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

