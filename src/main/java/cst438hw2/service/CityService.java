package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.*;

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
