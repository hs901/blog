package jp.co.ais_info.blog.entity;

import jakarta.persistence.*;
import jp.co.ais_info.blog.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // Exception
        if (dto.getId() != null)
            throw new IllegalArgumentException("Failed to generate replies, Replies' ID shoudnt exist");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("Failed to generate replies, Article IDs are incorrect");
        // Generate entity and return
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        // Exception
        if (this.id != dto.getId()){
            throw new IllegalArgumentException("Failed to modify replies, Incorrect IDs inputted");}
        // Update Object
        if (dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
