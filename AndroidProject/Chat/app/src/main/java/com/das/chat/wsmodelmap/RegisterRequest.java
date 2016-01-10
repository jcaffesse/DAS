package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class RegisterRequest
{
    private String username;
    private String email;
    private String password;

    public String getForm()
    {
        String res = new String();

        res += "nombre=";
        res += username;
        res += "&";

        res += "email=";
        res += email;
        res += "&";

        res += "id_rol=";
        res += "100";
        res += "&";

        res += "password=";
        res += password;

        return res;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
