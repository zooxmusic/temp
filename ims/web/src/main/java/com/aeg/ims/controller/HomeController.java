package com.aeg.ims.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Book> home() {

        List<Book> books=
                Arrays.asList(
                        new Book("Java ve Yazılım Tasarımı", 35d, "Altuğ B. Altıntaş", true),
                        new Book("Java Mimarisiyle Kurumsal Çözümler", 23d, "Rahman Usta", true),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", true),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", false),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", true));

        return books;
    }*/


    /*@RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        List<Book> books=
                Arrays.asList(
                        new Book("Java ve Yazılım Tasarımı", 35d, "Altuğ B. Altıntaş", true),
                        new Book("Java Mimarisiyle Kurumsal Çözümler", 23d, "Rahman Usta", true),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", false),
                        new Book("Veri Yapıları ve Algoritmalar", 40d, "Rifat Çölkesen", true),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", false),
                        new Book("Mobil Pazarlama - SoLoMo", 15d, "Kahraman-Pelin Arslan", true));

        modelAndView.addObject("books", books);
        return modelAndView;
    }*/
}