package com.mtcarpenter.mall.domain;

import com.mtcarpenter.mall.model.PmsProduct;
import lombok.Data;
import com.mtcarpenter.mall.dto.PromotionDetail;

@Data
public class PromotionProduct extends PmsProduct {
  /** Tutti i dettagli di promozione in un unico campo */
  private PromotionDetail promotionDetail;
}