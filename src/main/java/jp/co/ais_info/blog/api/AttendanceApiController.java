package jp.co.ais_info.blog.api;

import jp.co.ais_info.blog.dto.AttendanceForm;
import jp.co.ais_info.blog.entity.Attendance;
import jp.co.ais_info.blog.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AttendanceApiController {
    @Autowired
    private AttendanceService attendanceService;

    //GET
    @GetMapping("/api/attendance")
    public List<Attendance> index(){
        return attendanceService.index();
    }

    @GetMapping("/api/attendance/{id}")
    public Attendance show(@PathVariable Long id){
        return attendanceService.show(id);
    }

    @PostMapping("/api/attendance")
    public ResponseEntity<Attendance> create(@RequestBody AttendanceForm dto){
        Attendance created = attendanceService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/attendance/{id}")
    public ResponseEntity<Attendance> update(@PathVariable Long id, @RequestBody AttendanceForm dto){
        Attendance updated = attendanceService.update(id, dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/attendance/{id}")
    public ResponseEntity<Attendance> delete(@PathVariable Long id){
        Attendance deleted = attendanceService.delete(id);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}