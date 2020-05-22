package cn.wyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/12.
 */
@ToString
@NoArgsConstructor
@Data
public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();


}
