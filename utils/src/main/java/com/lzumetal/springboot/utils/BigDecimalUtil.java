package com.lzumetal.springboot.utils;

import java.math.BigDecimal;

/**
 * @author liaosi
 * @date 2020-09-09
 */
public class BigDecimalUtil {


    public static void main(String[] args) {
        BigDecimal b = new BigDecimal("0.0000000368000");
        System.out.println(b.scale());      //13
        System.out.println(b.stripTrailingZeros());     //3.68E-8
        System.out.println(b.stripTrailingZeros().toPlainString());     //0.0000000368
        System.out.println(b.stripTrailingZeros().scale());     //10

        String costPrice = "150";
        String costPriceYuan = new BigDecimal(costPrice)
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
        System.out.println(costPriceYuan);
    }


    /**
     * 获取小数点的位数（不去掉末尾的0）
     *
     * @param value
     * @return
     */
    public int getDecimalPlaces(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.scale();
    }


    /**
     * 获取小数点的位数（去掉末尾的0）
     *
     * @param value
     * @return
     */
    public int getDecimalPlacesWithTirm(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.stripTrailingZeros().scale();
    }

}
