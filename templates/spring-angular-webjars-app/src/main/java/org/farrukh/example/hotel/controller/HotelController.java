package org.farrukh.example.hotel.controller;

import org.farrukh.example.hotel.domain.City;
import org.farrukh.example.hotel.domain.HotelSummary;
import org.farrukh.example.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/city")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() {
        return this.cityService.getCity("Bath", "UK")
                               .getName();
    }

    @Transactional(readOnly = true)
    @RequestMapping("/{countryName}/{cityName}")
    public Page<HotelSummary> getHotels(@PathVariable String countryName,
                                        @PathVariable String cityName,
                                        @RequestParam(required = false, defaultValue = "0") final int page,
                                        @RequestParam(required = false, defaultValue = "1") final int size) {
        City city = cityService.getCity(cityName, countryName);
        Page<HotelSummary> hotels = cityService.getHotels(city, new PageRequest(page, size, new Sort("id")));
        return hotels;
    }

}
