package com.ceitechs.dproz.servicemanagement.adapter.rest;

import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import com.ceitechs.dproz.servicemanagement.domain.ServicesService;
import com.ceitechs.dproz.servicemanagement.domain.User;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by iddymagohe on 11/4/17.
 */

@RestController
@RequestMapping("/api/dproz/services")
public class ServiceController {

    private ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> serviceDetails(@RequestParam String lang, @RequestParam String keyword){
        return ResponseEntity.ok(servicesService.retrieveServicesByKeywordAndLanguage(keyword,lang));

    }
}
