package com.jiuzhi.common.resource.notify;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jiuzhi.common.resource.jmx.constants.ResourceConstants;
import com.jiuzhi.common.resource.manage.BusinessAppManage;
import com.jiuzhi.common.resource.manage.FileJobManage;
import com.jiuzhi.common.resource.model.BusinessAppBo;
import com.jiuzhi.common.resource.model.FileJobBo;
import com.jiuzhi.common.resource.utils.Callback;
import com.jiuzhi.common.resource.utils.DelayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service("jmxNotifyService")
public class JmxNotifyServiceImpl {

    private String url = "service:jmx:rmi:///jndi/rmi://{host}:{port}/jmxrmi";

    private static final Logger logger = LoggerFactory.getLogger(JmxNotifyServiceImpl.class);

    @Resource
    BusinessAppManage businessAppManage;

    @Resource
    FileJobManage fileJobManage;

    Map<String, DelayUtil<List<BusinessAppBo>>> appMap = new HashMap<>();

    public void notify(final FileJobBo bo) throws MBeanException, InstanceNotFoundException, ReflectionException {

        DelayUtil<List<BusinessAppBo>> util;
        if(appMap.containsKey(bo.getBusinessApp())){
            util = appMap.get(bo.getBusinessApp());
        }else{
            util = new DelayUtil(Long.valueOf(120 * 1000), new Callback<List<BusinessAppBo>>(){
                @Override
                public List<BusinessAppBo> invoke() {
                    return businessAppManage.findByAppName(bo.getBusinessApp());
                }
            });
            appMap.put(bo.getBusinessApp(), util);
        }
        List<BusinessAppBo> bos = util.get();
        if(CollectionUtils.isEmpty(bos))
            return;

        Boolean isSuccessed = Boolean.FALSE;
        for(BusinessAppBo appBo : bos) {
            try {
                String jmxUrl = url.replace("{host}", appBo.getIp()).replace("{port}", appBo.getJmxPort() + "");
                sendNotify(jmxUrl, bo.getBusinessId(), bo.getBusinessType(), !StringUtils.hasText(bo.getFailProcessIds()));
                isSuccessed = Boolean.TRUE;
                break;
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }

        fileJobManage.updateByCompletedNotify(bo, isSuccessed);
    }

    private void sendNotify(String jmxUrl, String businessId, String businessType, Boolean isSuccessed) throws Exception{
        JMXConnector jmxConnector = null;
        try {
            JMXServiceURL serviceUrl = new JMXServiceURL(jmxUrl);
            jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
            MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();
            ObjectName mBeanName = new ObjectName(ResourceConstants.DOMAIN+":"+ResourceConstants.ID+"="+ResourceConstants.VERSION);
            Object params[] = {businessId, businessType, isSuccessed};
            String signature[] = {"java.lang.String","java.lang.String","java.lang.Boolean"};
            Object object = mbeanConn.invoke(mBeanName, "receive", params, signature);
        } finally {
            if (jmxConnector != null) {
                try {
                    jmxConnector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
