package pl.jnews.core.crypto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jnews.infrastructure.cryptodata.CryptoDataClient;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoServiceImplement implements CryptoService{

    private final CryptoRepository cryptoRepository;
    private final CryptoDataClient cryptoDataClient;

    @Override
    public void addCryptoToDatabase() {
        log.info("start update cryptodatabase {}",LocalTime.now());
        List<Crypto> cryptos = cryptoDataClient.cryptoHandler();
        cryptoRepository.saveAll(cryptos);
        log.info("Crypto download to database {}",LocalTime.now());
    }


    public Integer countAllCrypto(){
        return cryptoRepository.countAllCrypto();
    }

    public List<Crypto> findByNameASC(){
        return cryptoRepository.findCryptoByNameAtoZ();
    }


    public List<Crypto> findByPriceASC(){
        return cryptoRepository.findCryptoByPriceHighToLow();
    }
    public List<Crypto> findByPriceDESC(){
        return cryptoRepository.findCryptoByPriceLowToHigh();
    }

    public List<Crypto> findByNameStartsWith(String name){
        return cryptoRepository.findByNameStartsWith(name.toLowerCase(Locale.ROOT));
    }

    public  void runUpdateCryptoTimer(){
        TimerTask crypto = new TimerTask() {
            public void run() {
                addCryptoToDatabase();
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        /////////////////////sec    min    god
//        long period = 1000L * 60L * 60L * 24L;

        /////update kursu co 30 min
        long period = 1000L * 60L * 30L;
        timer.scheduleAtFixedRate(crypto, delay, period);
    }

}
