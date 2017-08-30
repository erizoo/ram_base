package by.boiko.crm.controller;


import by.boiko.crm.model.Goods;
import by.boiko.crm.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping(value = "/sales")
    public ModelAndView getSalesPage() {
        return new ModelAndView("sales");
    }

    @GetMapping(value = "/get_goods/{category}")
    @ResponseBody
    public List<Goods> getGoodsForCategory(@PathVariable(value = "category") int category) {
        return salesService.getGoods(category);
    }

    @GetMapping(value = "/get_count/{category}")
    public int getCount(@PathVariable(value = "category") int category) {
        return salesService.getCount(category);
    }
}
