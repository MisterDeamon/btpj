package com.jack.security.service;

import com.jack.security.service.thread.RecieveMailThread;
import com.jack.utils.MailUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by wajiangk on 12/9/2016.
 */
@Service
public class MailService implements ApplicationListener<ContextRefreshedEvent>
{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
            //spring容器初始化完成后就会执行该方法。
//            MailUtil.connectMail();
//            RecieveMailThread rth = new RecieveMailThread();
//            Thread thread = new Thread(rth);
//            thread.start();
        }

        /*//或者下面这种方式
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"))
        {
            System.out.println("\n\n\n_________\n\n加载一次的 \n\n ________\n\n\n\n");
        }*/

    }

}
