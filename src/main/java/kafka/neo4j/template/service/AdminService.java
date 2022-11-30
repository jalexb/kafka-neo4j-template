package kafka.neo4j.template.service;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class AdminService {

    @Autowired
    public Admin admin;

    public void creatTopic(String... topics) {
        Collection<NewTopic> collection = new ArrayList<>();
        Arrays.asList(topics).forEach(topic -> {
            NewTopic newTopic = new NewTopic(topic, Optional.empty(), Optional.empty());
            collection.add(newTopic);
        });

        admin.createTopics(collection);
    }

    public List<String> getTopics() throws ExecutionException, InterruptedException {
        return new ArrayList<>(admin.listTopics().names().get());
    }
}
