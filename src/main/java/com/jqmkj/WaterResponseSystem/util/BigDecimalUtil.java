package com.jqmkj.WaterResponseSystem.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    public static boolean isBigDecimal(String str){
        try {
            BigDecimal b = BigDecimal.valueOf(Double.valueOf(str));
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;

    }
}
