package jp.co.ais_info.blog.dto;

import jp.co.ais_info.blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    private Long id;
    private String name;
    private String number;
    private String email;
    private String password;

    public User toEntity(){
        return new User(id, name, number, email, password);
    }
}