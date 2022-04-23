package pl.jnews.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jnews.core.crypto.Crypto;
import pl.jnews.core.crypto.CryptoServiceImplement;
import pl.jnews.core.news.News;
import pl.jnews.core.news.NewsServiceImplement;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Controller
@RequiredArgsConstructor
@RequestMapping("/")
class UserController {

    private final NewsServiceImplement newsService;
    private final CryptoServiceImplement cryptoService;

    //// wyszukiwanie wiadomości
    @GetMapping
    public String home(Model model){

            model.addAttribute("news",newsService.getNewsWhenInputEmpty());
            return "index";
    }


    @PostMapping
    public String homeWithCategory(Model model,
                                   @Param("category") String category){

        if(newsService.check(category)){
            List<News> newsWithCategory = newsService.getNewsWithCategory(category);
            if(newsWithCategory.size()==0 ){
                model.addAttribute("error","Bad request, please try to describe differently.");
            }
            model.addAttribute("news", newsWithCategory);
        } else {
            model.addAttribute("error","Bad request, please try to describe differently.");
        }
        return "index";
    }


    ////kontrolery kryptowalut
    @GetMapping("/cryptocurrency")
    public String cryptocurrencyGetForm(Model model,
                                        HttpSession session){


        if(cryptoService.countAllCrypto()<1){
            cryptoService.addCryptoToDatabase();
        }

        List<Crypto> byNameASC = cryptoService.findByNameASC();

        if(session.getAttribute("cryptoLastUpdate")==null){
            session.setAttribute("cryptoLastUpdate",byNameASC.get(0).getUpdated());
        }

        model.addAttribute("cryptos",cryptoService.findByNameASC());

        return "cryptocurrency";
    }

    @PostMapping("/cryptocurrency")
    public String cryptocurrencyPostForm(Model model,
                                         @Param("filter") String filter,
                                         @Param("name")String name){
        List<Crypto> cryptos = new ArrayList<>();

        //jeżeli nie wpisano nazwy kryptowaluty sortujemy po wybranym wskaźniku
        if(name.isEmpty()){
            if (filter.contains("priceHighToLow")) {
                cryptos=cryptoService.findByPriceDESC();
            } else if (filter.contains("priceLowToHigh")) {
                cryptos=cryptoService.findByPriceASC();
            } else {
                cryptos=cryptoService.findByNameASC();
            }
        } else {

            cryptos=cryptoService.findByNameStartsWith(name);
        }

        if(cryptos.size()==0){
            model.addAttribute("nonCrypto","Our database does not contain such cryptocurrency.");
        }
        model.addAttribute("cryptos",cryptos);

        return "cryptocurrency";
    }

    @GetMapping("/contact")
    public String getContacts(){
        return "contact";
    }
}
