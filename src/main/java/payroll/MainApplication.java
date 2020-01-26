package payroll;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String... args){
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    @KafkaListener(topics = "transactionTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception{
        logger.info(cr.toString());
        // String transactionTopic = cr.value
        this.template.send("engineOneTopic", cr.toString());
    }
}
