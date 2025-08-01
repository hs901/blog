package jp.co.ais_info.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.co.ais_info.blog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    @JsonProperty("article_id")
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getId(), comment.getArticle().getId(), comment.getNickname(), comment.getBody()
        );
    }
}