package com.kenect.kenectspringtest.controllers;

import com.kenect.kenectspringtest.data.dto.ContactDto;
import com.kenect.kenectspringtest.services.ContactService;
import com.kenect.kenectspringtest.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ContactDto contact) {
        try {
            return ResponseEntity.ok(this.contactService.save(contact));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
