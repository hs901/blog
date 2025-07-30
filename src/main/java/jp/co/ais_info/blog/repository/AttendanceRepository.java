package jp.co.ais_info.blog.repository;

import jp.co.ais_info.blog.entity.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    @Override
    ArrayList<Attendance> findAll();
}
