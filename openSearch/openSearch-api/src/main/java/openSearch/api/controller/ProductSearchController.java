package openSearch.api.controller;

import lombok.RequiredArgsConstructor;
import openSearch.document.ProductDocument;
import openSearch.service.ProductSearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @PostMapping
    public String save(@RequestBody ProductDocument product) throws IOException {
        return productSearchService.save(product);
    }

    @GetMapping("/{id}")
    public ProductDocument findById(@PathVariable String id) throws IOException {
        return productSearchService.findById(id);
    }

    @GetMapping
    public List<ProductDocument> findAll() throws IOException {
        return productSearchService.findAll();
    }

    @GetMapping("/search")
    public List<ProductDocument> search(@RequestParam String keyword) throws IOException {
        return productSearchService.searchByName(keyword);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) throws IOException {
        return productSearchService.delete(id);
    }

}
