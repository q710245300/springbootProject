package cn.wyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 下面用到的注解都是jpa带的，第一个表示标记实体，第二个指明table，第三个指明主键，最后一个说明主键是自增的
 */
@ToString
@NoArgsConstructor
@Data
public class Blog {

    private Long id;


    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentable;
    private Boolean published;
    private Boolean recommend;
    private Date createTime;
    private Date updateTime;

    private Type type;

    private List<Tag> tags = new ArrayList<>();

    private User user;

    public List<Comment> comments = new ArrayList<>();




}
