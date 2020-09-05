package com.pvdnc.notebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionExpiredEvent;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*10)//10分钟后会话过期
public class SpringSessionConfiguration {
    private static Logger LOG= LoggerFactory.getLogger(SpringSessionConfiguration.class);

    @EventListener
    public void onSessionCreated(SessionCreatedEvent createdEvent) {
        String sessionId = createdEvent.getSessionId();
        LOG.info("session:"+sessionId+" created");
    }

    @EventListener
    public void onSessionExpired(SessionExpiredEvent event){
        String sessionId=event.getSession().getId();
        LOG.info("session:"+sessionId+" expired");
    }
}
