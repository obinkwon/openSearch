package openSearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final OpenSearchClient client;

    public void save(Product product) throws IOException {

        client.index(i -> i
                .index("products")
                .id(product.getId())
                .document(product));
    }

}
