package cn.wyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BlogQuery {

    private String title;
    private Long typeId;
    private Boolean recommend;

}
