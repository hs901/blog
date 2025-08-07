package jp.co.ais_info.blog.dto;

import jp.co.ais_info.blog.entity.Attendance;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class AttendanceForm {
    private Long id;
    private String employeeName;
    private String attendanceTime;

    public Attendance toEntity() {
        return new Attendance(id, employeeName, attendanceTime);
    }
}