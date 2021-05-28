package com.lzumetal.springboot.utils;

import com.lzumetal.springboot.utils.common.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 * @date 2021-05-28
 */
public class LotteryUtils {


    public static void main(String[] args) throws ServiceException {
        Map<Long, String> groupProbability = new HashMap<>();
        groupProbability.put(1000001L, "0.0001");
        groupProbability.put(1000002L, "0.0002");
        groupProbability.put(1000003L, "0.0005");
        groupProbability.put(1000004L, "0.0008");
        Map<Long, Range> prizeRange = getPrizeRange(groupProbability);
        System.out.println(prizeRange);

        //是否中奖
        long max = 0;
        for (LotteryUtils.Range value : prizeRange.values()) {
            max = Math.max(max, value.getMax());
        }
        final long seq = max;
        long value = RandomUtils.nextLong(0, seq);
        for (Map.Entry<Long, Range> rangeEntry : prizeRange.entrySet()) {
            Long prizeId = rangeEntry.getKey();
            boolean lotteried = LotteryUtils.isLotteried(value, rangeEntry.getValue());
            if (lotteried) {
                System.out.println("抽中奖品：" + prizeId);
            }

        }
    }


    /**
     * @param groupProbability map的key是奖品id，value是中奖概率
     * @return 返回一个map。key是奖品id；value是如果中奖，随机数需要落在的区间
     */
    public static Map<Long, Range> getPrizeRange(Map<Long, String> groupProbability) {
        if (groupProbability == null || groupProbability.isEmpty()) {
            return Collections.emptyMap();
        }

        Collection<String> values = groupProbability.values();
        BigDecimal totalProbability = BigDecimal.ZERO;
        for (String value : values) {
            totalProbability = totalProbability.add(new BigDecimal(value));
        }
        if (totalProbability.compareTo(BigDecimal.ONE) > 0) {
            throw new RuntimeException("中奖概率总和大于1");
        }
        int maxPower = 0;
        for (String value : values) {
            BigDecimal prob = new BigDecimal(value);
            int power = 0;
            while (prob.stripTrailingZeros().scale() > 0) {
                prob = prob.multiply(BigDecimal.TEN);
                power++;
            }
            maxPower = Math.max(power, maxPower);
        }

        Map<Long, Range> result = new HashMap<>();
        BigDecimal factor = BigDecimal.TEN.pow(maxPower);
        long init = 0;
        for (Map.Entry<Long, String> entry : groupProbability.entrySet()) {
            //奖品概率为0，则剔除奖品
            BigDecimal probability = new BigDecimal(entry.getValue());
            if (BigDecimal.ZERO.compareTo(probability) >= 0) {
                continue;
            }
            long value = probability.multiply(factor).longValue();
            long max = init + value;
            result.put(entry.getKey(), new Range(init, max));
            init = max;
        }
        result.put(-1L, new Range(init, factor.longValue() + 1));
        return result;
    }


    /**
     * 判断是否中奖
     *
     * @param value
     * @param range
     * @return
     */
    public static boolean isLotteried(Long value, Range range) {
        if (value == null || range == null) {
            return false;
        }
        return value >= range.getMin() && value < range.getMax();
    }


    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Range {

        private long min;
        private long max;

    }


}
