package payroll;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String... args){
        SpringApplication.run(MainApplication.class, args);
    }

    //Inject KafkaTempate Class
    @Autowired
    private KafkaTemplate<String, String> template;

    //Listen to topic from Sender
    @KafkaListener(topics = "transactionTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception{
        logger.info(cr.toString());
        //publish topic with only a Value
        this.template.send("publishDataTopic", cr.value().toString());
    }
}
