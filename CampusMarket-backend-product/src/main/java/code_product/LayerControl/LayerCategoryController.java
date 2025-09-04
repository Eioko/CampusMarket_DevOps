package code_product.LayerControl;

import com.Aop.Pager;
import com.Aop.Protector;
import com.Po.Api.Result;
import com.Po.Entity.Category;
import com.Po.Dto.query.sons.CategoryQueryDto;
import code_product.LayerService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.System;

/**
 * 商品类别控制器
 */
@RestController
@RequestMapping("/category")
public class LayerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询
     *
     * @param categoryQueryDto 查询参数
     * @return Result<List < Category>> 响应结果
     */
    @Pager
    @PostMapping("/query")
    public Result<List<Category>> query(@RequestBody CategoryQueryDto categoryQueryDto) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return categoryService.query(categoryQueryDto);
    }

    /**
     * 批量删除
     */
    @Protector(role = "管理员") // 只有管理员能够去进行操作
    @PostMapping("/batchDelete")
    public Result<String> batchDelete(@RequestBody List<Integer> ids) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return categoryService.batchDelete(ids);
    }

    /**
     * 修改
     *
     * @param category 参数
     * @return Result<String> 响应结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Category category) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return saveOrUpdate(category, true);
    }

    /**
     * 新增
     *
     * @param category 参数
     * @return Result<String> 响应结果
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody Category category) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return saveOrUpdate(category, false);
    }

    private Result<String> saveOrUpdate(Category category, boolean isUpdate) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return isUpdate ? categoryService.update(category) : categoryService.save(category);
    }
}
