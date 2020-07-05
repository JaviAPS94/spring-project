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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	public void testAddGermany() throws Exception {
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

}
