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
public class Attendance {
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;
    @Column //
    private String employeeName;
    @Column
    private String attendanceTime;

    public void patch(Attendance attendance) {
        if (attendance.employeeName != null){
            this.employeeName = attendance.employeeName;
        }
        if (attendance.attendanceTime != null){
            this.attendanceTime = attendance.attendanceTime;
        }
    }
}