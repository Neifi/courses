package com.example.courses.infrastructure.streaming;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class InteractiveQueryService {

    private final StreamsBuilderFactoryBean factoryBean;

    public InteractiveQueryService(StreamsBuilderFactoryBean factoryBean) {
        this.factoryBean = factoryBean;
    }

    public <T> T getQueryableStore(String storeName, QueryableStoreType<T> queryableStoreType) {
        KafkaStreams kafkaStreams =  factoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        return kafkaStreams.store(StoreQueryParameters.fromNameAndType(storeName, queryableStoreType));
    }
}
