package cn.wyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/12.
 */
@ToString
@NoArgsConstructor
@Data
public class Tag {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();


}
