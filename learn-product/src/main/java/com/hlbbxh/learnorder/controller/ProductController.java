package com.hlbbxh.learnorder.controller;

import com.hlbbxh.learnorder.VO.ProductInfoVo;
import com.hlbbxh.learnorder.VO.ProductVo;
import com.hlbbxh.learnorder.VO.ResultVo;
import com.hlbbxh.learnorder.entity.ProductCategory;
import com.hlbbxh.learnorder.entity.ProductInfo;
import com.hlbbxh.learnorder.service.ProductCategoryService;
import com.hlbbxh.learnorder.service.impl.ProductInfoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0220:18:01
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/listProduct")
    public ResultVo<ProductVo> listProduct(){
        //查询所有的在售商品
        List<ProductInfo> productLists = productInfoService.findUpAll();
        //获取所有的 商品 type 放到一个新的 list里面
        List<Integer> categoryTypes = productLists.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        //从数据查询 符合条件的 type
        List<ProductCategory> categoryTypeList = productCategoryService.findByCategoryTypeIn(categoryTypes);
        //构造数据
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory:categoryTypeList) {
            ProductVo productVo = new ProductVo();
            //类型名称
            productVo.setCategoryName(productCategory.getCategoryName());
            //类型
            productVo.setCategoryType(productCategory.getCategoryType());
            //种类下面的商品 数据
            List<ProductInfoVo> productInfoVolist = new ArrayList<>();
            for (int i = 0; i < productLists.size(); i++) {
                ProductInfo productInfo = productLists.get(i);
                //挂载到对应的 type下面
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //复制属性值
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    //加入到list
                    productInfoVolist.add(productInfoVo);
                }
            }
            //设置对象
            productVo.setProductInfoVoList(productInfoVolist);
            //放入list
            productVoList.add(productVo);
        }
        ResultVo resultVo = new ResultVo(productVoList);
        return resultVo;
    }
}
