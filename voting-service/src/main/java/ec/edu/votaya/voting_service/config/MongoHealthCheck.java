package ec.edu.votaya.voting_service.config;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoHealthCheck implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MongoHealthCheck.class);
    private final MongoTemplate mongoTemplate;

    public MongoHealthCheck(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            Document ping = mongoTemplate.executeCommand(new Document("ping", 1));
            log.info("MongoDB ping result: {}", ping);
        } catch (Exception ex) {
            log.error("No se pudo conectar a MongoDB usando spring.data.mongodb.uri. Error: {}", ex.getMessage());
            throw new IllegalStateException("MongoDB connection failed", ex);
        }
    }
}
