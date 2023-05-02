package com.example.blogging.dto;

public class PostDto {

    private Long id;

    private String title;

    private String subtitle;

    private byte[] bfile;

    private String username;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public byte[] getFile() {
        return bfile;
    }

    public void setFile(byte[] file) {
        this.bfile = file;
    }
}

