
package com.mtcarpenter.mall.portal.controller;

import com.mtcarpenter.mall.common.api.CommonPage;
import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.domain.CartProduct;
import com.mtcarpenter.mall.domain.PromotionProduct;
import com.mtcarpenter.mall.model.PmsProduct;
import com.mtcarpenter.mall.portal.domain.PmsPortalProductDetail;
import com.mtcarpenter.mall.portal.domain.PmsProductCategoryNode;
import com.mtcarpenter.mall.portal.service.product.ProductCartService;
import com.mtcarpenter.mall.portal.service.product.ProductCategoryService;
import com.mtcarpenter.mall.portal.service.product.ProductDetailService;
import com.mtcarpenter.mall.portal.service.product.ProductSearchService;
import com.mtcarpenter.mall.portal.service.promotion.PromotionService;
import com.mtcarpenter.mall.portal.service.stock.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台商品管理Controller
 * Refactored to split responsibilities across multiple services.
 */
@Controller
@Api(tags = "PmsPortalProductController", description = "前台商品管理")
@RequestMapping("/product")
public class PmsPortalProductController {

    @Autowired
    private ProductSearchService productSearchService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private StockService stockService;

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> search(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) Long brandId,
                                                       @RequestParam(required = false) Long productCategoryId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProduct> productList = productSearchService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("以树形结构获取所有商品分类")
    @RequestMapping(value = "/categoryTreeList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryNode>> categoryTreeList() {
        List<PmsProductCategoryNode> list = productCategoryService.categoryTreeList();
        return CommonResult.success(list);
    }

    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = productDetailService.detail(id);
        return CommonResult.success(productDetail);
    }

    @ApiOperation("获取商品详情")
    @RequestMapping(value = "/getPmsProductById/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProduct> getPmsProductById(@PathVariable Long productId) {
        PmsProduct pmsProduct = productDetailService.getPmsProductById(productId);
        return CommonResult.success(pmsProduct);
    }

    @ApiOperation("获取购物车中某个商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = productCartService.getCartProduct(productId);
        return CommonResult.success(cartProduct);
    }

    @ApiOperation("获取促销商品")
    @RequestMapping(value = "/getPromotionProductList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<PromotionProduct>> getPromotionProductList(@RequestBody List<Long> productIdList) {
        List<PromotionProduct> promotionProductOutput = promotionService.getPromotionProductList(productIdList);
        return CommonResult.success(promotionProductOutput);
    }

    @ApiOperation("锁定下单商品的所有库存")
    @RequestMapping(value = "/lockStock", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult lockStock(@RequestParam(required = false) Long productSkuId,
                                  @RequestParam(required = false) Integer quantity) {
        stockService.lockStock(productSkuId, quantity);
        return CommonResult.success(null);
    }

}
