package com.jpmc.midascore;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.jpmc.midascore.foundation.Transaction;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    @Value("${general.kafka-topic}")
    private String topic;

    public KafkaProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // For real application use
    public void sendMessage(Transaction transaction) {
        kafkaTemplate.send(topic, transaction);
    }

    // For test compatibility - ADD THIS METHOD
    public void send(String transactionLine) {
        String[] transactionData = transactionLine.split(", ");
        Transaction transaction = new Transaction(
                Long.parseLong(transactionData[0]),
                Long.parseLong(transactionData[1]),
                Float.parseFloat(transactionData[2])
        );
        kafkaTemplate.send(topic, transaction);
    }
}