package source.domain;


import java.io.Serializable;

public class Token implements Serializable {
    private String token;
    private String userId;

    public Token(String token, long userId) {
        this.token = token;
        this.userId = userId + "";
    }

    public Token() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId + "";
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
