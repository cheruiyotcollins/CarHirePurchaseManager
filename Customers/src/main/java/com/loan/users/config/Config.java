/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.config;

import lombok.Value;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 *
 * @author hp pc
 */
@Configuration
public class Config {
    
//    @Value("${activemq.broker-url}")
//    private String brokerURL;
    private String brokerURL="tcp://localhost:61616";
    
    @Bean
    public ActiveMQQueue queue(){
    return new ActiveMQQueue("standalone.queue");
    }
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
    ActiveMQConnectionFactory factory= new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerURL);
    return factory;
    }
    @Bean
    public JmsTemplate JmsTemplate(){
    return new JmsTemplate(activeMQConnectionFactory());
    }
    
}
