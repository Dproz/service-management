package com.ceitechs.dproz.servicemanagement.adapter.rest;

import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import com.ceitechs.dproz.servicemanagement.domain.ServicesService;
import com.ceitechs.dproz.shared.rest.util.ExceptionHandlerUtil;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> serviceDetails(@RequestParam(required=false) String lang, @RequestParam(required=false) String keyword, 
    		@RequestParam(value="category",required=false) String categoryId){
    	try {
    		if(StringUtils.isEmpty(categoryId)) {
        		return ResponseEntity.ok(servicesService.retrieveServicesByKeywordAndLanguage(keyword,lang));
        	} else {
    			return ResponseEntity.ok(servicesService.retrieveServicesByCategoryAndLanguage(categoryId, lang));
    		}
    		
    	}catch (Exception ex) {
    		HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST: 
				HttpStatus.INTERNAL_SERVER_ERROR;
    		return ExceptionHandlerUtil.handleExcepetion(status, null, new Exception(ex.getMessage(), ex.getCause()));
		}
    	
        

    }
    @GetMapping(value = "/service-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices(@RequestHeader(value = "user-token") String userToken) {
    	try {
    		List<ServiceCategory> serviceCategories = servicesService.retrieveCategories();
    		return ResponseEntity.ok(serviceCategories);
    	} catch (Exception ex) {
    		HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST: 
				HttpStatus.INTERNAL_SERVER_ERROR;
    		return ExceptionHandlerUtil.handleExcepetion(status, null, new Exception(ex.getMessage(), ex.getCause()));
		}
    }
    
    
}
