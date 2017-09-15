package com.jiuzhi.common.resource.jmx.agent;

import com.jiuzhi.common.resource.jmx.constants.ResourceConstants;
import com.jiuzhi.common.resource.jmx.listener.JmxResourceListenerMBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017/3/28.
 */
public class ResourceAgent  implements ApplicationListener<ContextRefreshedEvent> {

    private JmxResourceListenerMBean jmxResourceListener;

    private void init(){
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(ResourceConstants.ID, ResourceConstants.VERSION);
        ObjectName oname = null;
        try {
            oname = ObjectName.getInstance(ResourceConstants.DOMAIN, properties);
            mbs.registerMBean(jmxResourceListener, oname);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }


    public void setJmxResourceListener(JmxResourceListenerMBean jmxResourceListener) {
        this.jmxResourceListener = jmxResourceListener;
    }
}
