package by.boiko.crm.controller;

import by.boiko.crm.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Erizo on 30.08.2017.
 */
@Controller
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/load_price")
    public void loadPriceFile() throws IOException {
        priceService.loadPrice();
    }

    @GetMapping(value = "/price")
    public ModelAndView parserFile() throws IOException {
        priceService.save(priceService.parserPrice());
        return new ModelAndView("sales");
    }
}
