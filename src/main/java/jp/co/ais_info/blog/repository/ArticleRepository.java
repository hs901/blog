package jp.co.ais_info.blog.repository;

import jp.co.ais_info.blog.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long>{

    @Override
    ArrayList<Article> findAll();
}