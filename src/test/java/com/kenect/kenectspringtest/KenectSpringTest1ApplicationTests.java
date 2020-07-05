package com.kenect.kenectspringtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenect.kenectspringtest.controllers.ContactController;
import com.kenect.kenectspringtest.data.dto.AddressDto;
import com.kenect.kenectspringtest.data.dto.ContactDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KenectSpringTest1ApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectmapper;

	@Test
	@Sql("/dataTest.sql")
	public void testGetContact() throws Exception {
		String response = mockMvc.perform(get("/contact" + "/{id}", 1))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.name", is("Test")))
				.andExpect(jsonPath("$.phones[0].number", is("0958963597")))
				.andExpect(jsonPath("$.emails[0].email", is("test@test.com")))
				.andExpect(jsonPath("$.addresses[0].street", is("test")))
				.andReturn()
				.getResponse()
				.getContentAsString();

		logger.info("response: " + response);
	}

	@Test
	public void testAddContact() throws Exception {
		ContactDto contact = new ContactDto();
		List<String> phones = new ArrayList<>();
		List<String> emails = new ArrayList<>();
		List<AddressDto> addresses = new ArrayList<>();
		AddressDto addressDto = new AddressDto();

		phones.add("0958963597");
		phones.add("0958963598");
		phones.add("0958963599");

		emails.add("test@test.com");
		emails.add("test1@test.com");
		emails.add("test2@test.com");

		addressDto.setStreet("test");
		addressDto.setStreetNumber("test");
		addressDto.setReference("test");
		addressDto.setCity(Long.valueOf(1));

		addresses.add(addressDto);

		contact.setName("test");
		contact.setDescription("test");
		contact.setPhones(phones);
		contact.setEmails(emails);

		contact.setAddresses(addresses);

		String response = mockMvc
				.perform(post("/contact")
						.content(objectmapper.writeValueAsString(contact))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

		logger.info(response);
	}

	@Test
	@Sql("/dataTest.sql")
	public void testDeleteContact() throws Exception {
		String response = mockMvc
				.perform(delete("/contact" + "/{id}", 1))
				.andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

		logger.info(response);
	}
}
