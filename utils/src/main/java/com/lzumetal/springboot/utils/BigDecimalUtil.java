package com.lzumetal.springboot.utils;

import java.math.BigDecimal;

/**
 * @author liaosi
 * @date 2020-09-09
 */
public class BigDecimalUtil {


    public static void main(String[] args) {
        /**
         * stripTrailingZeros() 去掉末尾的零
         * toPlainString()  非科学计算法的字符串
         */
        BigDecimal b = new BigDecimal("500000.00368000");
        System.out.println(b);
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

        BigDecimal a = new BigDecimal("0.00");
        System.out.println(a.stripTrailingZeros().toPlainString());

        System.out.println(getDiscountDesc(new BigDecimal(0.92)));
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


    public static String getDiscountDesc(BigDecimal discount) {
        String tip = new BigDecimal("100").multiply(discount)
                .setScale(1, BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
        //去掉后面的0
        tip = tip.replaceAll("(0)+$", "");
        return tip + "折";
    }


}
