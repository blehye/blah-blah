package kr.co.lutes.blahblah;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConf {
    public static final String F_ID = "_id";
    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public MongoDatabase mongoDB() {
        final CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries( MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        final MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder().
                                                            applyConnectionString(new ConnectionString(uri)).
                                                            codecRegistry(codecRegistry).
                                                            build());

        return mongoClient.getDatabase(dbName);
    }

    
}
