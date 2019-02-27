package com.stackroute.plasma.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

<<<<<<< HEAD
    @Value("${javainuse1.rabbitmq.queue}")
    String queueName1;

    @Value("${javainuse.rabbitmq.exchange}")
    String exchange;

    @Value("${javainuse1.rabbitmq.routingkey}")
    private String routingkey1;

    //Receiving message rabbitMQ
    @Bean
    public MessageConverter consumerJsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
=======
    //Receiving message rabbitMQ
    @Bean
    public MessageConverter consumerJsonMessageConverter(){
    return new Jackson2JsonMessageConverter();
}
>>>>>>> 71dc1e0c1a0703e453ba9ffa73af6921c5bf58e6

    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
                                                           SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(consumerJsonMessageConverter());
        return factory;
    }

<<<<<<< HEAD
     //Sending message to rabbitMQ
=======
    //Sending message to rabbitMQ
    @Value("${javainuse1.rabbitmq.queue}")
    String queueName1;

    @Value("${javainuse.rabbitmq.exchange}")
    String exchange;

    @Value("${javainuse1.rabbitmq.routingkey}")
    private String routingkey1;


>>>>>>> 71dc1e0c1a0703e453ba9ffa73af6921c5bf58e6
    @Bean
    Queue queue() {
        return new Queue(queueName1, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }


    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey1);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
