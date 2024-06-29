package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

@Controller
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String myCarMeth(ModelMap model,
                            @RequestParam(value = "count", required = false) Integer count) {
        if (count == null) {
            model.addAttribute("cars", carService.getCars(5));
        } else {
            model.addAttribute("cars", carService.getCars(count));
        }
        return "cars";
    }
}
