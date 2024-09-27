package sematech.manytomany.spring.model;

import lombok.Data;

/**
 * @auteur ALireza Abolhasani
 * @date: 9/26/2024
 * @time: 5:56 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Data
public class LoginResponse {

    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }
}
