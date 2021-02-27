package pl.sda.todoapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    // @GetMapping("/index")
    public String index(Model model) {
        Fruit fruits[] = {
                new Fruit("Jabłko"),
                new Fruit("Gruszka"),
                new Fruit("Śliwka") };

        model.addAttribute("items", fruits);

        return "index";
    }

    private class Fruit {
        private String name;

        public Fruit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
