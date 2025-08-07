package jp.co.ais_info.blog.controller;

import jp.co.ais_info.blog.dto.ArticleForm;
import jp.co.ais_info.blog.dto.CommentDto;
import jp.co.ais_info.blog.entity.Article;
import jp.co.ais_info.blog.repository.ArticleRepository;
import jp.co.ais_info.blog.repository.CommentRepository;
import jp.co.ais_info.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("/articles/new")
        public String newArticleForm(){
            return "articles/new";
        }

@PostMapping("/articles/create")
public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString());
        //1. Convert DTO to Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2. Save Entity to DB at Repository
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1. Reference ID and bring data
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        //2. Register data to model
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        //3. Return view page
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. Bring all data
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. Register data to model
        model.addAttribute("articleList", articleEntityList);
        //3. Set view page
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //Bring editing data
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //Register data to model
        model.addAttribute("article",articleEntity);
        //Set view page
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. Convert DTO to entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. Save entity to DB
        //2-1. Bring existing data from DB
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2.
        if (target != null){
            articleRepository.save(articleEntity); //Save entity to DB
        }
        //3. Redirect modified page
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete request received");
        //1. Bring deletion candidate
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. Delete the entity
        if (target != null){
            commentRepository.deleteByArticleId(id);
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Article Deleted");
        }
        //3. Redirect to final page
        return "redirect:/articles";
    }
}