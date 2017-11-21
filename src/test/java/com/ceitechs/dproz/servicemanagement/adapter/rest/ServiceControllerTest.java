/**
 * 
 */
package com.ceitechs.dproz.servicemanagement.adapter.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.ceitechs.dproz.servicemanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * @author vowino
 *
 */
@WebMvcTest (ServiceController.class)
public class ServiceControllerTest extends AbstractDprozServiceIntegrationTest{
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ServiceController serviceController;
	
	static final String USER_TOKEN = "user-token";
	static final String USER = "admin";
	static final String PASSWORD = "admin";
	
	@SuppressWarnings("unchecked")
	@Test
	public void serviDetailsWhenGivenCategoryIdTest() throws Exception {
		List<ServiceDetail> serviceDetails = new ArrayList<>();
		
		ServiceCategory serviceCategory0 = new ServiceCategory();
		serviceCategory0.setCategoryId(40);
		serviceCategory0.setCategoryName("Foundations");
		serviceCategory0.setCategoryDescription("Base for buildings");
		serviceCategory0.setDiscipline("Buildings");
		
		ServiceDetail serviceDetail0 = new ServiceDetail();
		serviceDetail0.setServiceId("59fe4216a78dc5dd7bee08af");
		serviceDetail0.setServiceNumber(4001);
		serviceDetail0.setLang("en");
		serviceDetail0.setServiceDescription("Settings");
		serviceDetail0.setCategory(serviceCategory0);
		
		serviceDetails.add(serviceDetail0);
		@SuppressWarnings("rawtypes")
		ResponseEntity responseObject = new ResponseEntity(serviceDetails, HttpStatus.OK);
		given(serviceController.serviceDetails(null, null, "40")).willReturn(responseObject);
		
		mvc.perform(get("/api/dproz/services/")
				.with(user(USER).password(PASSWORD))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("category", "40"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].category.categoryId", is((int)serviceCategory0.getCategoryId())))
		.andExpect(jsonPath("$[0].serviceId", is(serviceDetail0.getServiceId())))
		.andExpect(jsonPath("$[0].lang", is(serviceDetail0.getLang())));
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void serviDetailsWhenGivenKeywordAndLangTest() throws Exception {
		List<ServiceDetail> serviceDetails = new ArrayList<>();
		
		ServiceCategory serviceCategory0 = new ServiceCategory();
		serviceCategory0.setCategoryId(40);
		serviceCategory0.setCategoryName("Foundations");
		serviceCategory0.setCategoryDescription("Base for buildings");
		serviceCategory0.setDiscipline("Buildings");
		
		ServiceDetail serviceDetail0 = new ServiceDetail();
		serviceDetail0.setServiceId("59fe4216a78dc5dd7bee08af");
		serviceDetail0.setServiceNumber(4001);
		serviceDetail0.setLang("sw");
		serviceDetail0.setServiceDescription("Settings");
		serviceDetail0.setCategory(serviceCategory0);
		
		serviceDetails.add(serviceDetail0);
		@SuppressWarnings("rawtypes")
		ResponseEntity responseObject = new ResponseEntity(serviceDetails, HttpStatus.OK);
		given(serviceController.serviceDetails("sw", "set", null)).willReturn(responseObject);
		
		mvc.perform(get("/api/dproz/services/")
				.with(user(USER).password(PASSWORD))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("keyword", "set")
				.param("lang", "sw"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].category.categoryId", is((int)serviceCategory0.getCategoryId())))
		.andExpect(jsonPath("$[0].serviceId", is(serviceDetail0.getServiceId())))
		.andExpect(jsonPath("$[0].lang", is(serviceDetail0.getLang())));
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getServicesTest() throws Exception {
		List<ServiceCategory> services = new ArrayList<>();
		
		ServiceCategory serviceCategory0 = new ServiceCategory();
		serviceCategory0.setCategoryId(40);
		serviceCategory0.setCategoryName("Foundations");
		serviceCategory0.setCategoryDescription("Base for buildings");
		serviceCategory0.setDiscipline("Buildings");
		
		ServiceCategory serviceCategory1 = new ServiceCategory();
		serviceCategory1.setCategoryId(10);
		serviceCategory1.setCategoryName("Electricity");
		serviceCategory1.setCategoryDescription("Electricity");
		serviceCategory1.setDiscipline("Buildings");
		
		services.add(serviceCategory0);
		services.add(serviceCategory1);
		
		@SuppressWarnings({"rawtypes"})
		ResponseEntity responseObject = new ResponseEntity(services,HttpStatus.OK);
		given(serviceController.getServices(USER_TOKEN)).willReturn(responseObject);
		
		mvc.perform(get("/api/dproz/services/service-categories")
				.with(user(USER).password(PASSWORD))
				.header("user-token", USER_TOKEN)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].categoryId", is((int)serviceCategory0.getCategoryId())))
		.andExpect(jsonPath("$[1].categoryId", is((int)serviceCategory1.getCategoryId())))
		.andDo(print());
		
	}

}
