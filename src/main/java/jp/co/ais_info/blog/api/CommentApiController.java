package jp.co.ais_info.blog.api;

import jp.co.ais_info.blog.dto.CommentDto;
import jp.co.ais_info.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1. Inquire Replies
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // Let service
        List<CommentDto> dtos = commentService.comments(articleId);
        // Response to result
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2. Generate Replies
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        // Let service
        CommentDto createdDto = commentService.create(articleId, dto);
        // Response to result
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //3. Modify Replies
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        // Let Service
        CommentDto updatedDto = commentService.update(id, dto);
        // Response to result
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    //4. Delete Replies
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // Let Service
        CommentDto deletedDto = commentService.delete(id);
        // Response to result
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
