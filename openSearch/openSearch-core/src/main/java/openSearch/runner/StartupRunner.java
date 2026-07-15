package openSearch.runner;

import lombok.RequiredArgsConstructor;
import openSearch.service.IndexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final IndexService indexService;

    @Override
    public void run(String... args) throws Exception {
        indexService.createProductIndex();
    }
}
