package com.sagar.kafka.consumer;

import com.sagar.kafka.consumer.entity.WikiMediaData;
import com.sagar.kafka.consumer.repository.WikiMediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikiMediaDataRepository dataRepository;

    public KafkaDatabaseConsumer(WikiMediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recentChange",
            groupId = "myGroup"
    )
    public void consume(String eventMessage){
        log.info("Event Message received -> {}", eventMessage);

        WikiMediaData wikiMediaData = new WikiMediaData();
        wikiMediaData.setWikiEventData(eventMessage);

        dataRepository.save(wikiMediaData);

    }
}
