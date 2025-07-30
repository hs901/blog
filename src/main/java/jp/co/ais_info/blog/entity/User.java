package jp.co.ais_info.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Table(name = "`USER`")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String number;
    @Column
    private String email;
    @Column
    private String password;

    public void patch(User user) {
        if (user.name != null){
            this.name = user.name;
        }
        if (user.number != null){
            this.number = user.number;
        }
        if (user.email != null){
            this.email = user.email;
        }
        if (user.password != null){
            this.password = user.password;
        }
    }
}