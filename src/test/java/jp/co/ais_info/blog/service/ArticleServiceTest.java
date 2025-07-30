package jp.co.ais_info.blog.service;

import jp.co.ais_info.blog.dto.ArticleForm;
import jp.co.ais_info.blog.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //1. Prediction
        Article a = new Article(1L, "Attempt1", "One");
        Article b = new Article(2L, "Attempt2", "Two");
        Article c = new Article(3L, "Attempt3", "Three");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //2. Real Data
        List<Article> articles = articleService.index();
        //3. Compare and Study
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_InputExistingID() {
        //1. Prediction
        Long id = 1L;
        Article expected = new Article(id, "Attempt1", "One");
        //2. Real Data
        Article article = articleService.show(id);
        //3. Comparison
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_failed_InputNonExistingID(){
        //1. Prediction
        Long id = -1L;
        Article expected = null;
        //2. Real Data
        Article article = articleService.show(id);
        //3. Comparison
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_Success_InputTitleContentDtoOnly() {
        //1. Prediction
        String title = "aeiou";
        String content = "aiueo";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //2. Real Data
        Article article = articleService.create(dto);
        //3. Comparison
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_Failed_InputIDIncludedDTO() {
        //1. Prediction
        Long id = 4L;
        String title = "aeiou";
        String content = "44444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. Real Data
        Article article = articleService.create(dto);
        //3. Comparison
        assertEquals(expected, article);
    }

    @Test
    void update_success_InputDTOwithIDTitleContent() {
        Long id = 1L;
        String title = "abcde";
        String content = "fghij";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, title, content);
        Article article = articleService.update(id, dto);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void update_success_InputDTOwithIDTitle() {
        Long id = 1L;
        String title = "abcde";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article expected = new Article(1L, title, "One");
        Article article = articleService.update(id, dto);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void update_failed_InputDTOwithNonExistingID() {
        Long id = -1L;
        String title = "abcde";
        String content = "fghij";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        Article article = articleService.update(id, dto);
        assertEquals(expected, article);
    }

    @Test
    void delete_success_InputExitingID() {
        Long id = 1L;
        Article expected = new Article(id, "Attempt1", "One");
        Article article = articleService.delete(id);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void delete_failed_InputNonExitingID() {
        Long id = -1L;
        Article expected = null;
        Article article = articleService.delete(id);
        assertEquals(expected,article);
    }
}