package com.bjike.kafka;


import com.bjike.to.msg.MessageTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IKafkaProducer {

    default void produce(MessageTO messageTO) {

    }

}
