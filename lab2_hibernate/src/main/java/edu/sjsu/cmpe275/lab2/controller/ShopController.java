package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.model.Shop;
import edu.sjsu.cmpe275.lab2.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/find")
    public Shop greeting(@RequestParam(value="id", defaultValue="1") int id) {
        return shopService.findById(id);
    }

//    @RequestMapping(value="/create", method=RequestMethod.POST)
//    public ModelAndView createNewShop(@ModelAttribute Shop shop,
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
//        List<Shop> shopList = shopService.findAll();
//        mav.addObject("shopList", shopList);
//        return mav;
//    }

//    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
//    public ModelAndView editShopPage(@PathVariable Integer id) {
//        ModelAndView mav = new ModelAndView("shop-edit");
//        Shop shop = shopService.findById(id);
//        mav.addObject("shop", shop);
//        return mav;
//    }

//    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
//    public ModelAndView editShop(@ModelAttribute Shop shop,
//                                 @PathVariable Integer id,
//                                 final RedirectAttributes redirectAttributes) throws ShopNotFound {
//
//        ModelAndView mav = new ModelAndView("redirect:/index.html");
//        String message = "Shop was successfully updated.";
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
//        Shop shop = shopService.delete(id);
//        String message = "The shop "+shop.getName()+" was successfully deleted.";
//
//        redirectAttributes.addFlashAttribute("message", message);
//        return mav;
//    }

}