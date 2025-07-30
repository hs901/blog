package jp.co.ais_info.blog.service;

import jp.co.ais_info.blog.dto.UserForm;
import jp.co.ais_info.blog.entity.User;
import jp.co.ais_info.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> index() {
        return userRepository.findAll();
    }
    public User show(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User create(UserForm dto){
        User user = dto.toEntity();
        if (user.getId() != null){
            return null;
        }
        return userRepository.save(user);
    }

    public User update(Long id, UserForm dto){
        //1. Convert DTO to Entity
        User user = dto.toEntity();
        log.info("id: {}, user: {}", id, user.toString());
        //2. Inquire Target
        User target = userRepository.findById(id).orElse(null);
        //3. Correct incorrect request
        if (target == null || id != user.getId()){
            // 400 Respond incorrect request
            log.info("Incorrect request");
            return null;
        }
        //4. Update and correct response
        target.patch(user);
        User updated = userRepository.save(target);
        return updated;
    }

    public User delete(Long id){
        //1. Search target
        User target = userRepository.findById(id).orElse(null);
        //2. Correct incorrect request
        if (target == null){
            return null;
        }
        //3. Delete target
        userRepository.delete(target);
        return target;
    }
}
