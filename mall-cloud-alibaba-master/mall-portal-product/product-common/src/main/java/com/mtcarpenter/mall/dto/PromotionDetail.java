package com.mtcarpenter.mall.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mtcarpenter.mall.model.PmsProductFullReduction;
import com.mtcarpenter.mall.model.PmsProductLadder;
import com.mtcarpenter.mall.model.PmsSkuStock;
import lombok.Data;

import java.util.List;

@Data
public class PromotionDetail {
  private List<PmsSkuStock>             skuStockList;
  private List<PmsProductLadder>        productLadderList;
  private List<PmsProductFullReduction> productFullReductionList;
  
  @JsonUnwrapped    
  private PromotionDetail promotionDetail;
}