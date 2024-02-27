package midart.api.midart.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local"})
public class AWSConfig {

    @Value("${aws.access.accessKey}")
    private String awsAccessKey;

    @Value("${aws.access.secretKey}")
    private String awsSecretKey;

    @Value("${aws.access.region}")
    private String region;

    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(Regions.fromName(region))
                .build();
    }
}
