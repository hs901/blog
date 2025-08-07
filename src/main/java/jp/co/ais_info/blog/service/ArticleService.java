package jp.co.ais_info.blog.service;

import jp.co.ais_info.blog.dto.ArticleForm;
import jp.co.ais_info.blog.entity.Article;
import jp.co.ais_info.blog.repository.ArticleRepository;
import jp.co.ais_info.blog.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;
    public List<Article> index() {
        return articleRepository.findAll();
    }
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }
    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }
    public Article update(Long id, ArticleForm dto){
        //1. Convert DTO to Entity
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        //2. Target照会
        Article target = articleRepository.findById(id).orElse(null);
        //3. Correct incorrect request
        if (target == null || id != article.getId()){
            // 400 Respond incorrect request
            log.info("Incorrect request");
            return null;
        }
        //4. Update and correct response
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
    @Transactional
    public Article delete(Long id){
        //1. Search target
        Article target = articleRepository.findById(id).orElse(null);
        //2. Correct incorrect request
        if (target == null){
            return null;
        }
        log.info("Deleting comments for article id = {}", id);
        commentRepository.deleteByArticleId(id);
        log.info("Deleting article id = {}", id);
        //3. Delete target
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        //1. Convert DTOs to entities
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //2. Save entities into DB
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //3. Enforce exception forcibly
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("Failed to generate"));
        //4. Return value
        return articleList;
    }
}