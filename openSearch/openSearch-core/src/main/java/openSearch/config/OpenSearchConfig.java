package openSearch.config;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig {

    @Value("${opensearch.host}")
    private String host;

    @Value("${opensearch.port}")
    private int port;

    @Value("${opensearch.scheme}")
    private String scheme;

    @Bean
    public OpenSearchClient openSearchClient() {

        HttpHost httpHost = new HttpHost(scheme, host, port);

        OpenSearchTransport transport =
                ApacheHttpClient5TransportBuilder.builder(httpHost)
                        .build();

        return new OpenSearchClient(transport);
    }
}
