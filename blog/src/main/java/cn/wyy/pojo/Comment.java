package cn.wyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/12.
 */
@ToString
@NoArgsConstructor
@Data
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    // 头像
    private String avatar;
    private Date createTime;

    private Blog blog;

    // 子评论
    private List<Comment> replyComments = new ArrayList<>();

    // 父评论
    private Comment parentComment;


}
