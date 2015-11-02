package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.model.Stock;
import edu.sjsu.cmpe275.lab2.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/hello")
    public String greeting() {
        return "Hello World";
    }
    @RequestMapping("/find")
    public Stock find(@RequestParam(value="stockId", defaultValue="1") long stockId) {
        return stockService.findByStockId(stockId);
    }

//    @RequestMapping(value="/create", method=RequestMethod.POST)
//    public ModelAndView createNewShop(@ModelAttribute Stock shop,
//                                      final RedirectAttributes redirectAttributes) {
//
//        ModelAndView mav = new ModelAndView();
//        String message = "New shop "+shop.getName()+" was successfully created.";
//
//        shopService.create(shop);
//        mav.setViewName("redirect:/index.html");
//
//        redirectAttributes.addFlashAttribute("message", message);
//        return mav;
//    }

//    @RequestMapping(value="/list", method=RequestMethod.GET)
//    public ModelAndView shopListPage() {
//        ModelAndView mav = new ModelAndView("shop-list");
//        List<Stock> shopList = shopService.findAll();
//        mav.addObject("shopList", shopList);
//        return mav;
//    }

//    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
//    public ModelAndView editShopPage(@PathVariable Integer id) {
//        ModelAndView mav = new ModelAndView("shop-edit");
//        Stock shop = shopService.findById(id);
//        mav.addObject("shop", shop);
//        return mav;
//    }

//    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
//    public ModelAndView editShop(@ModelAttribute Stock shop,
//                                 @PathVariable Integer id,
//                                 final RedirectAttributes redirectAttributes) throws ShopNotFound {
//
//        ModelAndView mav = new ModelAndView("redirect:/index.html");
//        String message = "Stock was successfully updated.";
//
//        shopService.update(shop);
//
//        redirectAttributes.addFlashAttribute("message", message);
//        return mav;
//    }

//    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
//    public ModelAndView deleteShop(@PathVariable Integer id,
//                                   final RedirectAttributes redirectAttributes) throws ShopNotFound {
//
//        ModelAndView mav = new ModelAndView("redirect:/index.html");
//
//        Stock shop = shopService.delete(id);
//        String message = "The shop "+shop.getName()+" was successfully deleted.";
//
//        redirectAttributes.addFlashAttribute("message", message);
//        return mav;
//    }

}