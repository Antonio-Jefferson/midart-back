package midart.api.midart.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Slf4j
@Configuration
public class S3Config {
    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client S3Client() {
        log.info("Autenticando S3...");
        S3Client client = S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create("https://s3.us-west-2.amazonaws.com"))
                .forcePathStyle(true)
                .build();

        return client;
    }
}
