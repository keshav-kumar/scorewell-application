package com.scorewell.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

	private String uiServerUrl;
	private String forgotPasswordUrl;

	public String getUiServerUrl() {
		return uiServerUrl;
	}

	public void setUiServerUrl(String uiServerUrl) {
		this.uiServerUrl = uiServerUrl;
	}

	public String getForgotPasswordUrl() {
		return forgotPasswordUrl;
	}

	public void setForgotPasswordUrl(String forgotPasswordUrl) {
		this.forgotPasswordUrl = forgotPasswordUrl;
	}
	
}
