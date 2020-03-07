package com.pedro.bookstore.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1902713118413912949L;
	
	private final String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

}
