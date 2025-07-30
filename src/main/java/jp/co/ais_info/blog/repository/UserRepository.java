package jp.co.ais_info.blog.repository;

import jp.co.ais_info.blog.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    ArrayList<User> findAll();
}