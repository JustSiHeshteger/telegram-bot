package ru.zvrg.telegrambot.config.kafka;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

import static ru.zvrg.telegrambot.utils.constants.Constants.EncodingConstants.GSON;
import static ru.zvrg.telegrambot.utils.constants.Constants.GsonConstants.TYPE_TOKEN;
import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.BOOTSTRAP_SERVER;
import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.GROUP_ID;

@Configuration
public class KafkaConfig<T> {

    @Bean
    public KafkaProducer<String, T> kafkaProducer() {
        return new KafkaProducer<>(producerConfigs());
    }

    @Bean
    public KafkaConsumer<String, T> kafkaConsumer() {
        return new KafkaConsumer<>(consumerConfigs());
    }

    private Properties producerConfigs() {
        final Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(GSON, new Gson());
        return props;
    }

    private Properties consumerConfigs() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(GSON, new Gson());
        props.put(TYPE_TOKEN, new TypeToken<T>(){});
        return props;
    }
}
