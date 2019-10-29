package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketAuthorizationSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
        // You can customize your authorization mapping here.
        messages
                .simpDestMatchers("/secured/**").authenticated()
                .simpSubscribeDestMatchers("/topic/**").authenticated()
                .anyMessage().authenticated();
//        messages
//                .nullDestMatcher().authenticated()  //1
//                .simpSubscribeDestMatchers("/user/queue/errors").permitAll() //2
//                .simpDestMatchers("/app/**").hasRole("USER") //3
//                .simpSubscribeDestMatchers("/user/**", "/topic/friends/*").hasRole("USER") //4
//                //.simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll() //5
//                .anyMessage().denyAll(); //6


//1. Any message without a destination (i.e. anything other than Message type of MESSAGE or SUBSCRIBE) will require the user to be authenticated
//2. Anyone can subscribe to /user/queue/errors
//3. Any message that has a destination starting with "/app/" will be require the user to have the role ROLE_USER
//4. Any message that starts with "/user/" or "/topic/friends/" that is of type SUBSCRIBE will require ROLE_USER
//5. Any other message of type MESSAGE or SUBSCRIBE is rejected. Due to 6 we do not need this step, but it illustrates how one can match on specific message types.
//6. Any other Message is rejected. This is a good idea to ensure that you do not miss any messages.
    }

    // TODO: For test purpose (and simplicity) i disabled CSRF, but you should re-enable this and provide a CRSF endpoint.
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}