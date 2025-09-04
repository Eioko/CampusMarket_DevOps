package code_product.LayerService.Impl;

import code_product.LayerMap.LayerCategoryMapper;
import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import com.Po.Dto.query.sons.CategoryQueryDto;
import com.Po.Entity.Category;
import code_product.LayerService.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类别业务逻辑接口实现类
 */
@Service
public class LayerCategoryServiceImpl implements CategoryService {

    @Resource
    private LayerCategoryMapper layerCategoryMapper;

    /**
     * 查询
     *
     * @param categoryQueryDto 查询参数
     * @return Result<List<Category>> 响应结果
     */
    @Override
    public Result<List<Category>> query(CategoryQueryDto categoryQueryDto) {
        int totalCount = layerCategoryMapper.queryCount(categoryQueryDto);
        List<Category> categoryList = layerCategoryMapper.query(categoryQueryDto);
        return ApiResult.success(categoryList, totalCount);
    }

    public String queryResult(String a) {
        return "成功查找到:" + a;
    }

    /**
     * 批量删除
     *
     * @param ids 待删除的ID列表
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> batchDelete(List<Integer> ids) {
        layerCategoryMapper.batchDelete(ids);
        return ApiResult.success("商品分类删除成功");
    }

    public String deleteResult(String a) {
        return "成功删除" + a;
    }

    /**
     * 修改
     *
     * @param category 商品分类
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> update(Category category) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return saveOrUpdate(category, true);
    }

    /**
     * 新增
     *
     * @param category 商品分类
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> save(Category category) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return saveOrUpdate(category, false);
    }

    /**
     * 保存或修改
     *
     * @param category 商品分类
     * @param isUpdate 是否是更新操作
     * @return Result<String> 响应结果
     */
    private Result<String> saveOrUpdate(Category category, boolean isUpdate) {
        if (!StringUtils.hasText(category.getName())) {
            return ApiResult.error("商品分类名不能为空");
        }
        if (isUpdate) {
            layerCategoryMapper.update(category);
            return ApiResult.success("商品分类修改成功");
        } else {
            layerCategoryMapper.save(category);
            return ApiResult.success("商品分类新增成功");
        }
    }

    public boolean saveOrUpdateResult() {
        return true;
    }
}
