package jp.co.ais_info.blog.controller;

import jp.co.ais_info.blog.dto.AttendanceForm;
import jp.co.ais_info.blog.entity.Attendance;
import jp.co.ais_info.blog.repository.AttendanceRepository;
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
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @GetMapping("/attendance/new")
    public String newAttendanceForm(){
        return "attendance/new";
    }

    @PostMapping("/attendance/create")
    public String createAttendance(AttendanceForm form){
        log.info(form.toString());
        //System.out.println(form.toString());
        //1. Convert DTO to Entity
        Attendance attendance = form.toEntity();
        log.info(attendance.toString());
        //System.out.println(article.toString());
        //2. Save Entity to DB at Repository
        Attendance saved = attendanceRepository.save(attendance);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/attendance/" + saved.getId();
    }

    @GetMapping("/attendance/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1. Reference ID and bring data
        Attendance attendanceEntity = attendanceRepository.findById(id).orElse(null);
        //2. Register data to model
        model.addAttribute("attendance", attendanceEntity);
        //3. Return view page
        return "attendance/show";
    }

    @GetMapping("/attendance")
    public String index(Model model){
        //1. Bring all data
        ArrayList<Attendance> attendanceEntityList = attendanceRepository.findAll();
        //2. Register data to model
        model.addAttribute("attendanceList", attendanceEntityList);
        //3. Set view page
        return "attendance/index";
    }

    @GetMapping("/attendance/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //Bring editing data
        Attendance attendanceEntity = attendanceRepository.findById(id).orElse(null);
        //Register data to model
        model.addAttribute("attendance",attendanceEntity);
        //Set view page
        return "attendance/edit";
    }

    @PostMapping("/attendance/update")
    public String update(AttendanceForm form){
        log.info(form.toString());
        //1. Convert DTO to entity
        Attendance attendanceEntity = form.toEntity();
        log.info(attendanceEntity.toString());
        //2. Save entity to DB
        //2-1. Bring existing data from DB
        Attendance target = attendanceRepository.findById(attendanceEntity.getId()).orElse(null);
        //2-2.
        if (target != null){
            attendanceRepository.save(attendanceEntity); //Save entity to DB
        }
        //3. Redirect modified page
        return "redirect:/attendance/" + attendanceEntity.getId();
    }

    @GetMapping("/attendance/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete request received");
        //1. Bring deletion candidate
        Attendance target = attendanceRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. Delete the entity
        if (target != null){
            attendanceRepository.delete(target);
            rttr.addFlashAttribute("msg", "Attendance Deleted");
        }
        //3. Redirect to final page
        return "redirect:/attendance";
    }
}
