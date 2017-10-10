package com.sinolease.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by AlbertLy on 2016/11/24.
 */
public class CommonTools {

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //-------------------人民币小写转大写  start----------------------------
    /**
     * 人民币大写单位制
     */
    private static HashMap<Integer, String> dws;
    /**
     * 数字对应的中文
     */
    private static String[] jes;

    // 初始化执行
    static {
        dws = new HashMap<Integer, String>();
        dws.put(-2, "分");
        dws.put(-1, "角");
        dws.put(0, "元");
        dws.put(1, "拾");
        dws.put(2, "佰");
        dws.put(3, "仟");
        dws.put(4, "万");//
        dws.put(5, "拾");
        dws.put(6, "佰");
        dws.put(7, "仟");
        dws.put(8, "亿");//
        dws.put(9, "拾");
        dws.put(10, "佰");
        dws.put(11, "仟");
        dws.put(12, "万");
        jes = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    }

    /**
     * 数字转换人民币大写
     *
     * @param number 数字 不支持科学数字
     * @return
     */
    public static String numToChinese(String number) {
        StringBuffer su = new StringBuffer();
        // 整数部分
        number = delInvalidZero(number);
        String str = null;
        // 小数部分
        String decimal = null;
        if (number.contains(".")) {
            // 截取整数位
            str = number.split("\\.")[0];
            decimal = number.split("\\.")[1];
        } else {
            str = number;
        }
        // 判断是否存在整数位
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                String context = str.substring(i, i + 1);
                int pow = str.length() - i - 1;
                Integer val = Integer.parseInt(context.toString());
                // 获取中文单位
                String sign = dws.get(pow);
                // 获取中文数字
                String name = jes[Integer.parseInt(context)];
                if (val == 0) {
                    if (pow % 4 != 0) {// 删除单位
                        sign = "";
                    }
                    if (i < str.length() - 1) {
                        Integer val1 = Integer.parseInt(str.substring(i + 1, i + 2));
                        if (val == 0 && val == val1) {
                            name = "";
                        }
                    } else if (i == str.length() - 1) {
                        name = "";
                    }
                }
                su.append(name + sign);
            }
        }
        // 判断是否存在小数位
        if (decimal != null) {
            str = decimal.substring(0, 1);
            if (!"0".equals(str)) {
                su.append(jes[Integer.parseInt(str)] + dws.get(-1));
            }
            if (decimal.length() == 2) {
                str = decimal.substring(1, 2);
                if (!"0".equals(str)) {
                    su.append(jes[Integer.parseInt(str)] + dws.get(-2));
                }
            }
        } else {
            su.append("整");
        }
        return su.toString();
    }
    //----------------end------------------------------------------

    /**
     * 清理数字特殊字符
     *
     * @param str
     * @return
     */
    private static String delInvalidZero(String str) {
        if ("0".equals(str.substring(0, 1))) {
            return delInvalidZero(str.substring(1, str.length()));
        } else if (str.contains(",")) {
            return delInvalidZero(str.replaceAll(",", ""));
        } else {
            return str;
        }
    }

    /**
     * 2位小数， 四舍五入
     *
     * @param value
     * @return
     */
    public static Double formatDecimal(Double value) {
        BigDecimal bg = new BigDecimal(value).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }

    /**
     * 转化成会计计数方式
     *
     * @param number
     * @return
     */
    public static String currencyFormat(String number) {
        NumberFormat currencyResult = NumberFormat.getCurrencyInstance();
        BigDecimal result = new BigDecimal(delInvalidZero(number));
        return currencyResult.format(result).substring(1);
    }

    /**
     * 获取当月第一个天的日期
     *
     * @param date
     * @return
     */
    public static Date getMonthStartDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date startDate = df.parse(date);

        return startDate;
    }

    /**
     * 获取当月最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getMonthEndDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date endDate = df.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.set(Calendar.DAY_OF_MONTH, getDayOfMonth(endDate));
        return calendar.getTime();
    }

    /**
     * 获取下一个期项的日期
     *
     * @param date
     * @param monthIncrement
     * @param payDay
     * @return
     */
    public static Date getNextPayDate(Date date, int monthIncrement, int payDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, monthIncrement);
        calendar.set(Calendar.DATE, payDay);
        return calendar.getTime();
    }
    
    /**
     * 获取逾期租期上一个季度需要确认的利息截止日期
     * @param beginDate
     * @param monthIncrement
     * @param payDay
     * @return
     * @throws ParseException 
     */
    public static Date getDueReceivedDate(Date beginDate, int monthIncrement, int payDay) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
    	DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date endDate=null;
        while(true) {
            calendar.setTime(beginDate);
        	int month = calendar.get(Calendar.MONTH)+1;
        	if(month==3||month==6 ||month==9||month==12) {
        		endDate = getMonthEndDate(df.format(calendar.getTime()));
        		break;
        	}
        	beginDate=getNextPayDate(beginDate, monthIncrement, payDay);
        }
        return endDate;
    }
    /**
     * 判断是否为同一年同一个月
     * @param date1
     * @param date2
     * @return
     */
    public static boolean judgeSameMonth(Date date1,Date date2) {
    	Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 获取当月总天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.setTime(date);
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day;
    }


    /**
     * 获取当前日期经过天数
     *
     * @param date
     * @return
     */
    public static int getPassedDays(Date date) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.setTime(date);
        return time.get(Calendar.DATE);
    }

    /**
     * 获取当月剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDays(Date date) {
        return getDayOfMonth(date) - getPassedDays(date);
    }

    /**
     * 月数差
     *
     * @param start
     * @param end
     * @return
     */
    public static int getMonthDiff(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 天数差
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDayDiff(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        startCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(java.util.Calendar.MINUTE, 0);
        startCalendar.set(java.util.Calendar.SECOND, 0);
        endCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(java.util.Calendar.MINUTE, 0);
        endCalendar.set(java.util.Calendar.SECOND, 0);

        //得到两个日期相差的天数
        int days = ((int) (endCalendar.getTime().getTime() / 1000) - (int) (startCalendar
                .getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 获取增值税乘积参数（计算增值税附加税后结果）
     *
     * @param rate
     * @return
     */
    public static double getRatePara(double rate) {
        return rate * 1.13 / (1 + rate);
    }

    /**
     * 计算印花税
     *
     * @param income
     * @param rate
     * @return
     */
    public static double getValueAddedCost(double income, double rate) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(income * getRatePara(rate)));
    }

    /**
     * 计算当月本金为0
     *
     * @param remainFunds
     * @param FTP
     * @param date
     * @return
     */
    public static double getCostWithZeroFunds(double remainFunds, double FTP, Date date) {
        double cost = 0.00;
        DecimalFormat df = new DecimalFormat("#.00");
        cost = remainFunds * FTP * getDayOfMonth(date) / 360;
        return Double.parseDouble(df.format(cost));
    }

    /**
     * 计算当月本金不为0
     *
     * @param remainFunds
     * @param preRemainFunds
     * @param FTP
     * @param date
     * @return
     */
    public static double getCostWithNonZeroFunds(double remainFunds, double preRemainFunds, double FTP, Date date) {
        double cost = 0.00;
        cost = remainFunds * FTP * getRemainDays(date) / 360 + preRemainFunds * FTP * getPassedDays(date) / 360;
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(cost));
    }

    /**
     * 获取收入
     *
     * @param date
     * @param interest
     * @param totalDays
     * @param post_interest
     * @param post_totalDays
     * @param needSplit
     * @return
     */
    public static double getIncome(Date date, double interest, int totalDays, double post_interest, int post_totalDays, boolean needSplit) {
        double income = 0.00;
        if (!needSplit)
            income = interest * getDayOfMonth(date) / totalDays;
        else income = interest * getPassedDays(date) / totalDays + post_interest * getRemainDays(date) / post_totalDays;
        return income;
    }


    public static double getArithmeticAverage(ArrayList<Double> list){
        if(list==null || list.size()==0){
            return 0.00;
        }
        else {
            Double sum=0.00;
            for(int i=0;i<list.size();i++){
                sum = sum + list.get(i);
            }
            return sum/list.size();
        }
    }

    public static double getArithmeticAverageSpecial(ArrayList<Double> list,Integer n){
        if(list==null || list.size()==0 || list.size()<=2*n){
            return 0.00;
        }
        else {
            Double sum=0.00;
            for(int i=0;i<list.size()-1;i++){
                for(int j=i+1;j<list.size();j++){
                    double temp=0.0;
                    if(list.get(i)>list.get(j)){
                        temp=list.get(i);
                        list.set(i,list.get(j));
                        list.set(j,temp);
                    }
                }
            }
            for(int i=n;i<list.size()-n;i++){
                sum=sum+list.get(i);
            }
            return sum/(list.size()-2*n);
        }
    }

    public static double getWeightedAverage(ArrayList<Double> list){
        if(list==null || list.size()==0){
            return 0.00;
        }
        else {
            Double sum=0.00;
            for(int i=0;i<list.size();i++){
                sum = sum + list.get(i)*list.get(i);
            }
            return Math.sqrt(sum/list.size());
        }
    }





    /*public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println("日期转字符串：" + CommonTools.DateToStr(date));
        System.out.println("字符串转日期：" + CommonTools.StrToDate(CommonTools.DateToStr(date)));
        System.out.println(getRatePara(0.17));
        System.out.println(getDayOfMonth(date));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date start = df.parse("2015-12-04");
        Date end = df.parse("2016-03-16");
        System.out.println("###start:==" + start);
        System.out.println("###end:==" + end);

        getMonthDiff(start, end);
        System.out.println("###getMonth():=" + getMonthDiff(start, end));
        System.out.println(currencyFormat("5000000000"));
        //System.out.println(getCostWithNonZeroFunds(63960995.43, 63960995.43, 4.86, date));
    }*/
}
