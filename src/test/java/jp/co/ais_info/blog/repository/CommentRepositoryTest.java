package jp.co.ais_info.blog.repository;

import jp.co.ais_info.blog.entity.Article;
import jp.co.ais_info.blog.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("Show all replies under specific article")
    void findByArticleId() {
        /* Case 1. Show all replies on article #4 */
        {
        //1. Prepare input data
        Long articleId = 4L;
        //2. Real Data
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //3. Prediction
        Article article = new Article(4L, "Whats your favorite movie?", "Reply");
        Comment a = new Comment(1L, article, "Park", "Good Duel Hunting");
        Comment b = new Comment(2L, article, "Kim", "I am Sam");
        Comment c = new Comment(3L, article, "Choi", "Prison Break");
        List<Comment> expected = Arrays.asList(a, b, c);
        //4. Compare
        assertEquals(expected.toString(), comments.toString(), "Output all replies in board #4");
        }
    }

    @Test
    void findByNickname() {
        /* Case 2. Show all replies on article #1 */
        {
            //1. Prepare input data
            String nickname = "Park";
            //2. Real Data
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //3. Prediction
            Comment a = new Comment(1L, new Article(4L, "Whats your favorite movie?", "Reply"), nickname, "Good Duel Hunting");
            Comment b = new Comment(4L, new Article(5L, "Whats your best cuisine ever?", "Reply"), nickname, "Chicken");
            Comment c = new Comment(7L, new Article(6L, "Whats your hobby?", "Reply"), nickname, "Jogging");
            List<Comment> expected = Arrays.asList(a, b, c);
            //4. Comparison
            assertEquals(expected.toString(),comments.toString(), "Outputted all replies from Park");
        }
    }
}