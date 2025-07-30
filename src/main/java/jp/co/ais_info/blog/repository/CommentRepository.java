package jp.co.ais_info.blog.repository;

import jp.co.ais_info.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Show all replies on specific post
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    // Show all replies of specific user
    List<Comment> findByNickname(String nickname);
}