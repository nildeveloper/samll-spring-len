package com.lhl.spring.common;

import com.lhl.spring.context.ApplicationListener;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 14:05
 * @describe:
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {


    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
