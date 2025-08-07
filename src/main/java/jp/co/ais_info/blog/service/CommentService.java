package jp.co.ais_info.blog.service;

import jp.co.ais_info.blog.dto.CommentDto;
import jp.co.ais_info.blog.entity.Article;
import jp.co.ais_info.blog.entity.Comment;
import jp.co.ais_info.blog.repository.ArticleRepository;
import jp.co.ais_info.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    public List<CommentDto> comments(Long articleId){
        //3. Return results
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //1. Inquire Article and handle exception
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Failed to generate replies" + "Target article void"));
        //2. Generate replies
        Comment comment = Comment.createComment(dto, article);
        //3. Save Reply entities into DB
        Comment created = commentRepository.save(comment);
        //4. Convert DTO and return
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1. Inquire Article and handle exception
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Failed to update replies" + "Target article void"));
        //2. Update replies
        target.patch(dto);
        //3. Update Reply entities into DB
        Comment updated = commentRepository.save(target);
        //4. Convert DTO and return
        return CommentDto.createCommentDto(updated);
    }
    @Transactional
    public CommentDto delete(Long id) {
        //1. Inquire Article and handle exception
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Failed to delete replies" + "Target article void"));
        //2. Delete replies
        commentRepository.delete(target);
        //3. Convert deleted replies to DTOs and return
        return CommentDto.createCommentDto(target);
    }
}