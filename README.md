package com.jiuzhi.common.resource.jmx.listener;

import javax.annotation.Resource;

import com.appleframework.jms.kafka.producer.KafkaMessageProducer2;
import com.jiuzhi.biz.social.manage.StarSocialDynamicsCommentManage;
import com.jiuzhi.biz.social.manage.StarSocialDynamicsManage;
import com.jiuzhi.biz.yaya.service.CommentPraiseService;


public class JmxResourceListener implements JmxResourceListenerMBean {

    @Resource
    private StarSocialDynamicsManage starSocialDynamicsManage;

    @Resource
    private StarSocialDynamicsCommentManage starSocialDynamicsCommentManage;

    @Resource
    private KafkaMessageProducer2 messageProducer2;

    @Resource
    private CommentPraiseService commentPraiseService;

    @Override
    public void receive(String id, String type, Boolean isSuccessed) {

        if("dynamic".equals(type)){
            starSocialDynamicsManage.updateForResourceCrawlingStatus(Long.valueOf(id), isSuccessed);
        }else if("comment".equals(type)) {
            starSocialDynamicsCommentManage.updateForResourceCrawlingStatus(Long.valueOf(id), isSuccessed);
        }
    }

    public void setStarSocialDynamicsManage(StarSocialDynamicsManage starSocialDynamicsManage) {
        this.starSocialDynamicsManage = starSocialDynamicsManage;
    }
}



	<!-- resource回调通知 -->
	<bean id="jmxResourceListener" class="com.jiuzhi.common.resource.jmx.listener.JmxResourceListener">
		<property name="starSocialDynamicsManage" ref="starSocialDynamicsManage"></property>
	</bean>
  
	<bean id="agent" class="com.jiuzhi.common.resource.jmx.agent.ResourceAgent">
		<property name="jmxResourceListener" ref="jmxResourceListener"></property>
	</bean>
