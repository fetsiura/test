package pl.jnews.infrastructure.cryptodata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.jnews.core.crypto.Crypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoDataClient {
    private final String urlCryptoList = "https://api.coingecko.com/api/v3/coins/list";
    private final String urlCryptoID_START= "https://api.coingecko.com/api/v3/simple/price?ids=";
    private final String urlCryptoID_END = "&vs_currencies=USD";
    private final RestTemplate restTemplate;

    public List<Crypto> cryptoHandler(){
        Long id = 1L;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(" ","");
        HttpEntity entity = new HttpEntity(httpHeaders);

        List<String> currenciesList = readFromFile("currency.txt");

        List<Crypto> cryptos = new ArrayList<>();

        for (String currency : currenciesList){

        ResponseEntity<String> exchange = restTemplate.exchange(urlCryptoID_START+currency+urlCryptoID_END,
                HttpMethod.GET,
                entity,
                String.class);
            String[] split = exchange.getBody().split(":");
            String price = split[2].substring(0, split[2].length() - 2);

            Crypto crypto = new Crypto();
            crypto.setId(id);
            crypto.setName(currency);
            crypto.setPrice(Double.valueOf(price));
            crypto.preUpdate();
            cryptos.add(crypto);
            id++;
        }
        return cryptos;


    }


    public static List<String> readFromFile(String fileName){
        List<String> list = new ArrayList<>();
        Path path = Paths.get(fileName);
        if(Files.exists(path)){
            try {
                for (String line : Files.readAllLines(path)){
                    list.add(line);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return list;
    }
}
