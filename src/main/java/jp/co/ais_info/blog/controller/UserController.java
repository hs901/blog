package jp.co.ais_info.blog.controller;

import jp.co.ais_info.blog.dto.UserForm;
import jp.co.ais_info.blog.entity.User;
import jp.co.ais_info.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user/new")
    public String newMemberForm() {
        return "user/new";
    }

    @PostMapping("/user/create")
    public String registerUser(UserForm form){
        log.info(form.toString());
        //System.out.println(form.toString());
        //1. Convert DTO to Entity
        User user = form.toEntity();
        log.info(user.toString());
        //System.out.println(member.toString());
        //2. Save Entity to DB at Repository
        User saved = userRepository.save(user);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/user/" + saved.getId();
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1. Reference ID and bring data
        User userEntity = userRepository.findById(id).orElse(null);
        //2. Register data to model
        model.addAttribute("user", userEntity);
        //3. Return view page
        return "user/show";
    }

    @GetMapping("/user")
    public String index(Model model){
        //1. Bring all data
        ArrayList<User> userEntityList = userRepository.findAll();
        //2. Register data to model
        model.addAttribute("userList", userEntityList);
        //3. Set view page
        return "user/index";
    }

    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //Bring editing data
        User userEntity = userRepository.findById(id).orElse(null);
        //Register data to model
        model.addAttribute("user",userEntity);
        //Set view page
        return "user/edit";
    }

    @PostMapping("user/update")
    public String update(UserForm form){
        log.info(form.toString());
        //1. Convert DTO to entity
        User userEntity = form.toEntity();
        log.info(userEntity.toString());
        //2. Save entity to DB
        //2-1. Bring existing data from DB
        User target = userRepository.findById(userEntity.getId()).orElse(null);
        //2-2.
        if (target != null){
            userRepository.save(userEntity); //Save entity to DB
        }
        //3. Redirect modified page
        return "redirect:/user/" + userEntity.getId();
    }

    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete request received");
        //1. Bring deletion candidate
        User target = userRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. Delete the entity
        if (target != null){
            userRepository.delete(target);
            rttr.addFlashAttribute("msg", "User Deleted");
        }
        //3. Redirect to final page
        return "redirect:/user";
    }
}