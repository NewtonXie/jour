package com.gzcb.creditcard.utils;

import org.quartz.CronExpression;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author huangxiaofeng
 */
public class CronDateUtils {
    private static final String CRON_DATE_FROMAT ="yyyy-MM-dd HH:mm:ss";
    private static Logger logger = LoggerFactory.getLogger(CronDateUtils.class);


    /**
     * 解析当天的cron表达式成时间类型
     * @param cron 表达式
     * @param cronDate 当天的时间
     * @return
     */
    public static List<String> getDate(String cron,String cronDate){
        List<String> result = new ArrayList<>();
        if(cron==null||cron.length()<1||cronDate==null||cronDate.length()<1){
            return null;
        }
        CronExpression exp =null;
        try{
            //初始化CRON表达式解析器
            exp = new CronExpression(cron);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
        //定义时间范围
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FROMAT);
        //定义开始时间，前一天的23点59分59秒
        String sStart = cronDate;
        Date dStart= new Date();
        try{
            dStart =sdf.parse(sStart);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        c.setTime(dStart);
        c.add(Calendar.SECOND,-1);
        dStart =c.getTime();
        //定义当前结束时间
        c.add(Calendar.DATE,1);
        Date dEnd =c.getTime();
        //生成时间序列
        Date dd = dStart;
        dd =exp.getNextInvalidTimeAfter(dd);
        while((dd.getTime()>=dStart.getTime())&&dd.getTime()<=dEnd.getTime()){
            result.add(sdf.format(dd));
            dd=exp.getNextInvalidTimeAfter(dd);
        }
        exp =null;
        return  result;
    }

    public static  Date getDateByCron(String cronStr) throws Exception{
        Date curTime = new Date();
        CronExpression expression;
        expression = new CronExpression(cronStr);
        Date timeAfter = expression.getTimeAfter(curTime);
        return timeAfter;
    }

    public static List<Date> getAllDateByCron(String cronStr) throws  Exception{
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression(cronStr);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.WEEK_OF_MONTH,1);
        List<Date> dates = TriggerUtils.computeFireTimesBetween(
                cronTrigger,null,now,calendar.getTime()
        );
        return dates;
    }

    public static List<String> getAllDateStrByCron(String cronStr ,int times) throws  Exception{
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression(cronStr);
        List<Date> lists = TriggerUtils.computeFireTimes(
                cronTrigger,null,times);
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < lists.size(); i++) {
            list.add(sdf.format(lists.get(i)));
        }
        return list;
    }
    public static void main(String[] arg )throws  Exception{

        String cron ="0 0/30 0 L * ?";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<Date> lists = CronDateUtils.getAllDateByCron(cron);
//        for(int i =0 ;i<lists.size();i++){
//            System.out.println("============");
//            System.out.println(sdf.format(lists.get(i)));
//        }
        System.out.println(getAllDateStrByCron(cron,5));

    }
}
