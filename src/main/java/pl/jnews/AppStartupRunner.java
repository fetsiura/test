package pl.jnews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.jnews.core.crypto.CryptoServiceImplement;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final CryptoServiceImplement cryptoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        cryptoService.runUpdateCryptoTimer();

    }
}