package com.das.chat.wsmodelmap;


public class LoginRequest
{
    private String username;
    private String password;

    public String getForm()
    {
        String res = new String();

        res += "nombre_usuario=";
        res += username;
        res += "&";

        res += "password=";
        res += password;

        return res;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
