package cst438hw2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438hw2.domain.City;
import cst438hw2.domain.CityInfo;
import cst438hw2.domain.CityRepository;
import cst438hw2.domain.Country;
import cst438hw2.domain.CountryRepository;
import cst438hw2.domain.TempAndTime;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private WeatherService weatherService;

  public CityInfo getCityInfo(String cityName) {
    // Grab data from database that holds city information
    List<City> cities = cityRepository.findByName(cityName);
    if (cities != null) {
      City city = cities.get(0);
      Country country = countryRepository.findByCode(city.getCountryCode());
      TempAndTime tempAndTime = weatherService.getTempAndTime(cityName);

      return new CityInfo(city, country.getName(), tempAndTime.getTemp(),
          tempAndTime.getTimeString());
    }
    return null;
  }

}
