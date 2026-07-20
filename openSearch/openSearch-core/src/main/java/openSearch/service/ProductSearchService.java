package openSearch.service;

import lombok.RequiredArgsConstructor;
import openSearch.document.ProductDocument;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.DeleteResponse;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private static final String INDEX = "products";
    private final OpenSearchClient openSearchClient;

    // 저장
    public String save(ProductDocument product) throws IOException {

        IndexResponse response = openSearchClient.index(i -> i.index(INDEX).id(product.getId()).document(product));

        return response.id();
    }

    // ID 조회
    public ProductDocument findById(String id) throws IOException {

        return openSearchClient.get(g -> g.index(INDEX).id(id), ProductDocument.class).source();
    }

    // 이름 검색
    public List<ProductDocument> searchByName(String keyword) throws IOException {

        SearchResponse<ProductDocument> response =
                openSearchClient.search(s -> s
                                .index(INDEX)
                                .query(q -> q
                                        .match(m -> m
                                                .field("name")
                                                .query(FieldValue.of(keyword))
                                        )
                                ),
                        ProductDocument.class
                );

        return response.hits().hits().stream().map(Hit::source).toList();
    }

    // 전체 조회
    public List<ProductDocument> findAll() throws IOException {

        SearchResponse<ProductDocument> response =
                openSearchClient.search(s -> s.index(INDEX).query(q -> q.matchAll(ma -> ma)), ProductDocument.class);

        return response.hits().hits().stream().map(Hit::source).toList();
    }

    // 삭제
    public String delete(String id) throws IOException {

        DeleteResponse response = openSearchClient.delete(d -> d.index(INDEX).id(id));

        return response.id();
    }
}