package cn.wyy.controller.admin;

import cn.wyy.pojo.Type;
import cn.wyy.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/5/19.
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 10);
        List<Type> allTypes = typeService.getAllTypes();
        PageInfo<Type> pageInfo = new PageInfo<>(allTypes);
        model.addAttribute("allTypesPageInfo", pageInfo);
        return "admin/types";
    }

    // 跳转到新增页面
    @GetMapping("types/add")
    public String add(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-add";
    }

    // 新增一个type
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes) {
        if (typeService.getTypeByName(type.getName()) != null) {
            attributes.addFlashAttribute("message", "不能重复添加分类");
            return "redirect:/admin/types/add";
        }
        Integer integer = typeService.saveType(type);

        if (integer == 0) {
            // 新增失败
            attributes.addFlashAttribute("message", "新增失败");
        }
        else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    // 修改一个type的页面
    @GetMapping("types/{id}/update")
    public String updatePage(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));

        return "admin/type-add";
    }

    // 修改一个type
    @PostMapping("/types/{id}")
    public String post(Type updatedType, @PathVariable Long id, RedirectAttributes attributes) {
        updatedType.setId(id);
        Integer integer = typeService.updateType(updatedType);

        if (integer == 0) {
            // 新增失败
            attributes.addFlashAttribute("message", "更新失败");
        }
        else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    // 删除一个type
    @GetMapping("types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
