package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cst438hw2.domain.CityInfo;
import cst438hw2.service.CityService;

@Controller
public class CityController {

  @Autowired
  private CityService cityService;

  @GetMapping("/cities/{city}")
  public String getCityInfo(@PathVariable("city") String cityName, Model model) {
    CityInfo cityInfo = (CityInfo) cityService.getCityInfo(cityName);
    if (cityInfo != null) {
      model.addAttribute("cityInfo", cityInfo);
      return "weather";
    }
    return null;
  }
}
