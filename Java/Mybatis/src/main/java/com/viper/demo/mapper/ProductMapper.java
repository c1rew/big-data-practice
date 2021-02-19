package com.viper.demo.mapper;

import com.viper.demo.domain.ProductDO;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-02-19 18:28
 */
public interface ProductMapper {

    // 根据id查询商品信息
    ProductDO find(long id);
    // 根据名称搜索商品信息
    List<ProductDO> findByName(String name);
    // 保存商品信息
    long save(ProductDO product);
}
