package jp.co.ais_info.blog.service;

import jp.co.ais_info.blog.dto.AttendanceForm;
import jp.co.ais_info.blog.entity.Attendance;
import jp.co.ais_info.blog.repository.AttendanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    public List<Attendance> index() {
        return attendanceRepository.findAll();
    }
    public Attendance show(Long id){
        return attendanceRepository.findById(id).orElse(null);
    }
    public Attendance create(AttendanceForm dto){
        Attendance attendance = dto.toEntity();
        if (attendance.getId() != null){
            return null;
        }
        return attendanceRepository.save(attendance);
    }

    public Attendance update(Long id, AttendanceForm dto){
        //1. Convert DTO to Entity
        Attendance attendance = dto.toEntity();
        log.info("id: {}, attendance: {}", id, attendance.toString());
        //2. Target照会
        Attendance target = attendanceRepository.findById(id).orElse(null);
        //3. Correct incorrect request
        if (target == null || id != attendance.getId()){
            // 400 Respond incorrect request
            log.info("Incorrect request");
            return null;
        }
        //4. Update and correct response
        target.patch(attendance);
        Attendance updated = attendanceRepository.save(target);
        return updated;
    }

    public Attendance delete(Long id){
        //1. Search target
        Attendance target = attendanceRepository.findById(id).orElse(null);
        //2. Correct incorrect request
        if (target == null){
            return null;
        }
        //3. Delete target
        attendanceRepository.delete(target);
        return target;
    }
}
