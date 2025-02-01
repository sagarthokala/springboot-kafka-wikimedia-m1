package com.sagar.m1;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.extern.slf4j.Slf4j;
import okio.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikiMediaChangesProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.wiki.kafka.name}")
    private String topicName;

    public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = topicName;

        //to read realtime stream data from wikimedia
        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";


        BackgroundEventHandler handler = new WikimediaChangesHandler(kafkaTemplate, topic);
        BackgroundEventSource eventSource = new BackgroundEventSource.Builder(handler,new EventSource.Builder(URI.create(url))).build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
