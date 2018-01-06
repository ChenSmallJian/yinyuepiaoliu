package com.whut.yinyuepiaoliu.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetConstellation {
    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座"};

    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }

    public static String getConstellation(String date) {
        Date da = null;
        try {
            da = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {

        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(da);
        return getConstellation(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getConstellation(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getConstellation(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 根据出生日期计算属相和星座
     */
    @Test
    public void test() throws ParseException {
        System.out.println(getConstellation(8, 1));
        System.out.println(getConstellation("1992-8-1"));
        System.out.println(getConstellation(new SimpleDateFormat("yyyy-MM-dd").parse("1992-8-1")));
    }
}