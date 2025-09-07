@Component
public class KafkaProducer {

    @Value("${general.kafka-topic}")
    private String topic;

    // rest of your code
}