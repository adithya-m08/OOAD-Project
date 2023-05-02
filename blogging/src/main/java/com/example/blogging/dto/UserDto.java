package com.example.blogging.dto;

import com.example.blogging.models.UserRole;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;

    public UserDto() {}

    public UserDto(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{"
            + "id=" + id + ", username='" + username + '\'' + ", password='" +
            password + '\'' + ", role='" + role + '\'' + '}';
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
