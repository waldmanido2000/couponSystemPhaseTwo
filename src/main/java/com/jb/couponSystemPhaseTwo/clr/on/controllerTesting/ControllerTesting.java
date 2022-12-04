package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Order(10)
public class ControllerTesting implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${url}")
    private String url;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("rest template");
        List<Company> companies = new ArrayList<>(List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/companies", Company[].class))));
        for (Company c:
             companies) {
            System.out.println(c);
        }
    }
}
