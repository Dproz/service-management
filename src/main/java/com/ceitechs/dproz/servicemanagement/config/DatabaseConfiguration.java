package com.ceitechs.dproz.servicemanagement.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.github.mongobee.Mongobee;

import io.github.jhipster.domain.util.JSR310DateConverters.DateToZonedDateTimeConverter;
import io.github.jhipster.domain.util.JSR310DateConverters.ZonedDateTimeToDateConverter;

import static java.util.stream.Collectors.toList;

@Configuration
@EnableMongoRepositories("com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo")
@Import(value = MongoAutoConfiguration.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DatabaseConfiguration extends AbstractMongoConfiguration {

    private final static String HOSTS_SEPARATOR = ",";

    private final static String HOST_PORT_SEPARATOR = ":";

    @Value("${servicemanagement.db.password}")
    private String dbPassword;

    @Value("${servicemanagement.db.user}")
    private String dbuser;

    @Value("${servicemanagement.db.host}") // host:port
    private String host;

    @Value("${servicemanagement.db.name}")
    private String dbName;

    private static MongoClient mongoClient = null;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add(ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    @Bean
    public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        log.debug("Configuring Mongobee" + uri);
         mongoClient = mongoClient();
        Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setDbName(getDatabaseName());
        mongobee.setMongoTemplate(mongoTemplate);
        // package to scan for migrations
        mongobee.setEnabled(true);
        mongobee.setChangeLogsScanPackage("com.ceitechs.dproz.config.dbmigrations");

        return mongobee;
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    /**
     * Return the {@link MongoClient} instance to connect to. Annotate with {@link Bean} in case you want to expose a
     * {@link MongoClient} instance to the {@link ApplicationContext}.
     *
     * @return
     */
    @Override
    public MongoClient mongoClient() {
        if (mongoClient != null) return mongoClient;

        List<ServerAddress> addresses = Stream.of(host.split(HOSTS_SEPARATOR)).map(addr -> {
            String[] hostAndport = addr.split(HOST_PORT_SEPARATOR);
            return new ServerAddress(hostAndport[0], Integer.valueOf(hostAndport[1]));
        }).collect(toList());

        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(dbuser, dbName, dbPassword.toCharArray());

        MongoClientOptions clientOptions = MongoClientOptions.builder().writeConcern(WriteConcern.ACKNOWLEDGED).build();

        mongoClient = new MongoClient(addresses, Arrays.asList(mongoCredential), clientOptions);
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
