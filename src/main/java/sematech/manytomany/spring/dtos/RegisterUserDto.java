package sematech.manytomany.spring.dtos;

import lombok.Data;

/**
 * @auteur ALireza Abolhasani
 * @date: 9/26/2024
 * @time: 4:07 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

@Data
public class RegisterUserDto {

    private String email;

    private String password;

    private String fullName;


}
