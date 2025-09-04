package code_product.LayerService.Impl;

import com.Interceptor.LocalThreadHolder;
import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import code_product.LayerService.InteractionService;
import code_product.LayerService.StarService;

import com.Po.Entity.Interaction;
import com.Po.Entity.Message;
import com.Po.Entity.User;
import com.Po.Vo.ProductVO;
import com.Po.Vo.StarVo;
import com.Po.Dto.query.sons.InteractionQueryDto;
import com.Po.Dto.query.sons.ProductQueryDto;
import com.Po.Dto.query.sons.StarQueryDto;
import com.Po.Em.InteractionType;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.LocalDateTime;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import code_product.LayerMap.LayerInteractionMapper;
import code_product.LayerMap.LayerMessageMapper;
import code_product.LayerMap.LayerProductMapper;
import code_product.LayerMap.LayerUserMapper;



@Service
public class LayerInteractionServiceImpl implements InteractionService {

    private static final String REPEAT_OPERATION_MSG = "请勿重复操作";
    private static final String PRODUCT_NOT_FOUND_MSG = "商品信息查询异常";
    private static final String SELF_OPERATION_MSG = "别自卖自夸!";
    private static final String SUCCESS_MSG = "卖家已感受到你的热情，快下单吧!";
    private static final String COLLECT_SUCCESS_MSG = "收藏成功";
    private static final String CANCEL_COLLECT_MSG = "取消收藏成功";
    private static final String MESSAGE_TEMPLATE = "用户【%s】对你的【%s】感兴趣!";
    private static final String NEW_PRODUCT_TEMPLATE = "你关注的用户【%s】发布了新商品【%s】，快去看看吧!";

    @Resource
    private LayerInteractionMapper layerInteractionMapper;
    @Resource
    private LayerProductMapper layerProductMapper;
    @Resource
    private LayerMessageMapper layerMessageMapper;
    @Resource
    private LayerUserMapper layerUserMapper;
    @Resource
    private StarService starService;

    @Override
    public Result<String> likeProduct(Integer productId) {
        ProductVO product = getProductById(productId);
        if (product == null) {
            System.out.println("没找到产品");
            System.out.println("返回错误");
            return ApiResult.error(PRODUCT_NOT_FOUND_MSG);
        }

        if (isInteractionExist(productId, InteractionType.LOVE)) {
            System.out.println("有重复操作");
            System.out.println("返回错误");
            return ApiResult.error(REPEAT_OPERATION_MSG);
        }

        if (isCurrentUserProduct(product)) {
            System.out.println("操作自己商品");
            System.out.println("返回错误");
            return ApiResult.error(SELF_OPERATION_MSG);
        }
        System.out.println("正确执行");
        sendLikeNotification(product);

        saveInteraction(productId, InteractionType.LOVE);

        return ApiResult.success(SUCCESS_MSG);
    }
    public String likeResult(String a,String b)
    {
        return a+"喜欢商品:"+b;
    }

    @Override
    public Result<String> newProduct(Integer productId) {
        ProductVO product = getProductById(productId);
        if (product == null) {
            System.out.println("没找到产品");
            System.out.println("返回错误");
            return ApiResult.error(PRODUCT_NOT_FOUND_MSG);
        }

        else {
            System.out.println("正确执行");
            List<Integer> followerIds = getCurrentUserFollowers();
            if (followerIds.isEmpty()) {
                System.out.println("无人关注");
                return ApiResult.success(SUCCESS_MSG);
            }
            else {
                System.out.println("发送信息");
                sendNewProductNotifications(product, followerIds);
            }
            return ApiResult.success(SUCCESS_MSG);
        }

    }

    @Override
    public Result<String> save(Interaction interaction) {
        if(interaction!= null) {
            System.out.println("正确执行");
            layerInteractionMapper.save(interaction);
            return ApiResult.success("互动行为记录成功");
        }
        System.out.println("没找到交互");
        System.out.println("返回错误");
        return ApiResult.success("互动行为记录成功");
    }
    public String saveResult(String a,String b)
    {
        return a+"对"+b+"的交互成功";
    }
    @Override
    public Result<String> batchDelete(List<Integer> ids) {
        if(ids!=null) {
            if (!CollectionUtils.isEmpty(ids)) {
                System.out.println("正确执行");
                layerInteractionMapper.batchDelete(ids);
            }
            return ApiResult.success("互动行为删除成功");
        }
        System.out.println("没找到交互");
        System.out.println("返回错误");
        return ApiResult.success("互动行为删除成功");
    }

    @Override
    public Result<Boolean> saveOperation(Integer productId) {
        if(productId != null) {
            boolean isCollected = isInteractionExist(productId, InteractionType.SAVE);

            if (isCollected) {
                cancelCollection(productId);
                System.out.println("重复收藏");
                System.out.println("返回错误");
                return ApiResult.success(CANCEL_COLLECT_MSG, false);
            } else {
                System.out.println("正确执行");
                saveInteraction(productId, InteractionType.SAVE);
                return ApiResult.success(COLLECT_SUCCESS_MSG, true);
            }
        }
        System.out.println("没找到产品");
        System.out.println("返回错误");
        return ApiResult.success(CANCEL_COLLECT_MSG, false);
    }

    @Override
    public Result<List<Interaction>> query(InteractionQueryDto queryDto) {
        if(queryDto!=null) {
            int totalCount = layerInteractionMapper.queryCount(queryDto);
            List<Interaction> interactions = layerInteractionMapper.query(queryDto);
            System.out.println("正确执行");
            return ApiResult.success(interactions, totalCount);
        }
        System.out.println("没找到查询类");
        System.out.println("返回错误");
        return ApiResult.success(layerInteractionMapper.query(queryDto), layerInteractionMapper.queryCount(queryDto));
    }

    @Override
    public Result<List<ProductVO>> queryUser() {
        List<Integer> productIds = getInteractionProductIds(InteractionType.SAVE);
        if (productIds.isEmpty()) {
            System.out.println("正确执行");
            return ApiResult.success(Collections.emptyList());
        }
        System.out.println("没找到查询产品");
        System.out.println("返回错误");
        return ApiResult.success(layerProductMapper.queryProductList(productIds));
    }
    public String queryResult(String a)
    {
        return "成功查找到:"+a;
    }
    @Override
    public Result<Void> view(Integer productId) {
        if(productId!=null) {
            System.out.println("正确执行");
            if (!isInteractionExist(productId, InteractionType.VIEW)) {
                saveInteraction(productId, InteractionType.VIEW);
            }
            return ApiResult.success();
        }
        System.out.println("没找到产品");
        System.out.println("返回错误");
        return ApiResult.success();
    }

    @Override
    public Result<List<ProductVO>> myView() {
        List<Integer> productIds = getInteractionProductIds(InteractionType.VIEW);
        if (productIds.isEmpty()) {
            System.out.println("正确执行");
            return ApiResult.success(Collections.emptyList());
        }
        System.out.println("没找到产品");
        System.out.println("返回错误");
        return ApiResult.success(layerProductMapper.queryProductList(productIds));
    }

    @Override
    public Result<String> batchDeleteInteraction() {
        List<Integer> ids = getInteractionIds(InteractionType.VIEW);
        if (!ids.isEmpty()) {
            System.out.println("正确执行");
            layerInteractionMapper.batchDelete(ids);
        }
        return ApiResult.success();
    }


    private ProductVO getProductById(Integer productId) {
        ProductQueryDto queryDto = new ProductQueryDto();
        queryDto.setId(productId);
        List<ProductVO> products = layerProductMapper.query(queryDto);
        return CollectionUtils.isEmpty(products) ? null : products.get(0);
    }

    private boolean isInteractionExist(Integer productId, InteractionType type) {
        InteractionQueryDto queryDto = createInteractionQueryDto(productId, type);
        return layerInteractionMapper.queryCount(queryDto) > 0;
    }

    private boolean isCurrentUserProduct(ProductVO product) {
        return Objects.equals(product.getUserId(), LocalThreadHolder.getUserId());
    }

    private void sendLikeNotification(ProductVO product) {
        User currentUser = getCurrentUser();
        String content = String.format(MESSAGE_TEMPLATE,
                currentUser.getUserName(), product.getName());
        createAndSaveMessage(product.getUserId(), content);
    }

    private void sendNewProductNotifications(ProductVO product, List<Integer> followerIds) {
        String content = String.format(NEW_PRODUCT_TEMPLATE,
                product.getUserName(), product.getName());

        followerIds.forEach(followerId ->
                createAndSaveMessage(followerId, content)
        );
    }

    private void createAndSaveMessage(Integer userId, String content) {
        Message message = new Message();
        message.setUserId(userId);
        message.setIsRead(false);
        message.setCreateTime(LocalDateTime.now());
        message.setContent(content);
        layerMessageMapper.save(message);
    }

    private void saveInteraction(Integer productId, InteractionType type) {
        Interaction interaction = new Interaction();
        interaction.setUserId(LocalThreadHolder.getUserId());
        interaction.setType(type.getType());
        interaction.setProductId(productId);
        interaction.setCreateTime(LocalDateTime.now());
        layerInteractionMapper.save(interaction);
    }

    private void cancelCollection(Integer productId) {
        InteractionQueryDto queryDto = createInteractionQueryDto(productId, InteractionType.SAVE);
        List<Integer> ids = layerInteractionMapper.query(queryDto).stream()
                .map(Interaction::getId)
                .collect(Collectors.toList());

        if (!ids.isEmpty()) {
            layerInteractionMapper.batchDelete(ids);
        }
    }

    private List<Integer> getCurrentUserFollowers() {
        StarQueryDto starQueryDto = new StarQueryDto();
        starQueryDto.setUser2Id(LocalThreadHolder.getUserId());
        return starService.getStarVos(starQueryDto).stream()
                .map(StarVo::getUser1Id)
                .collect(Collectors.toList());
    }

    private List<Integer> getInteractionProductIds(InteractionType type) {
        InteractionQueryDto queryDto = new InteractionQueryDto();
        queryDto.setUserId(LocalThreadHolder.getUserId());
        queryDto.setType(type.getType());
        return layerInteractionMapper.query(queryDto).stream()
                .map(Interaction::getProductId)
                .collect(Collectors.toList());
    }

    private List<Integer> getInteractionIds(InteractionType type) {
        InteractionQueryDto queryDto = new InteractionQueryDto();
        queryDto.setUserId(LocalThreadHolder.getUserId());
        queryDto.setType(type.getType());
        return layerInteractionMapper.query(queryDto).stream()
                .map(Interaction::getId)
                .collect(Collectors.toList());
    }

    private InteractionQueryDto createInteractionQueryDto(Integer productId, InteractionType type) {
        InteractionQueryDto queryDto = new InteractionQueryDto();
        queryDto.setUserId(LocalThreadHolder.getUserId());
        queryDto.setType(type.getType());
        queryDto.setProductId(productId);
        return queryDto;
    }

    private User getCurrentUser() {
        User user = new User();
        user.setId(LocalThreadHolder.getUserId());
        return layerUserMapper.getByActive(user);
    }
}