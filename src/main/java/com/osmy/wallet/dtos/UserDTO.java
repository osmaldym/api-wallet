package com.osmy.wallet.dtos;

public class UserDTO {
    private Long id = null; 
    private String names;
    private String password;
    private String email;
    private String img;

    public UserDTO() {}

    public UserDTO(Long id, String email, String img, String names, String password) {
        this.id = id;
        this.email = email;
        this.img = img;
        this.names = names;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNames() {
        return names;
    }
    public void setNames(String names) {
        this.names = names;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    
}