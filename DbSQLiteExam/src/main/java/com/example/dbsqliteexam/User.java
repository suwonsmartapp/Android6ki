package com.example.dbsqliteexam;

/**
 * Created by junsuk on 2017. 9. 6..
 */

public class User {
    private String email;
    private String password;

    // 반드시 빈 생성자가 있어야 한다

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
