package com.bow.spring.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author ViVi
 * @date 2015年5月24日 下午8:57:15
 */

public class SimpleBean implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBean.class);

    private String connectionString;

    private String password;

    private String username;

    public SimpleBean() {

    }

    public SimpleBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setPassword(String password) {
        LOGGER.info("setPassword");
        this.password = password;
    }

    public void setUsername(String username) {
        LOGGER.info("setUsername");
        this.username = username;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleBean{connectionString=").append(this.connectionString);
        sb.append(",username=").append(this.username).append(",password=").append(this.password);
        return sb.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet");
    }


}
