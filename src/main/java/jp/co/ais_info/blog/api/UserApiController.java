package jp.co.ais_info.blog.api;

import jp.co.ais_info.blog.dto.UserForm;
import jp.co.ais_info.blog.entity.User;
import jp.co.ais_info.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserApiController {
    @Autowired
    private UserService userService;

    //GET
    @GetMapping("/api/user")
    public List<User> index(){
        return userService.index();
    }

    @GetMapping("/api/user/{id}")
    public User show(@PathVariable Long id){
        return userService.show(id);
    }

    @PostMapping("/api/user")
    public ResponseEntity<User> create(@RequestBody UserForm dto){
        User created = userService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/user/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserForm dto){
        User updated = userService.update(id, dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        User deleted = userService.delete(id);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
