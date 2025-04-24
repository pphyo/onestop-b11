package com.jdc.balance.core.payload.output;

public record AuthOutput(String name, String username, Boolean admin, Boolean setUpSetting, String accessToken,
		String refreshToken) {

	public static AuthOutputBuilder builder() {
		return new AuthOutputBuilder();
	}

	public static class AuthOutputBuilder {
		private String name;
		private String username;
		private Boolean admin;
		private Boolean setUpSetting;
		private String accessToken;
		private String refreshToken;

		public AuthOutput build() {
			return new AuthOutput(name, username, admin, setUpSetting, accessToken, refreshToken);
		}

		public AuthOutputBuilder name(String name) {
			this.name = name;
			return this;
		}

		public AuthOutputBuilder username(String username) {
			this.username = username;
			return this;
		}

		public AuthOutputBuilder admin(Boolean admin) {
			this.admin = admin;
			return this;
		}

		public AuthOutputBuilder setUpSetting(Boolean setUpSetting) {
			this.setUpSetting = setUpSetting;
			return this;
		}

		public AuthOutputBuilder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public AuthOutputBuilder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}
	}

}
