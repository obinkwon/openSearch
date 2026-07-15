package openSearch.service;

import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final OpenSearchClient client;

    public void createProductIndex() throws IOException {

        boolean exists = client.indices()
                .exists(e -> e.index("products"))
                .value();

        if (!exists) {
            client.indices().create(c -> c.index("products"));
        }
    }
}
