package com.kenect.kenectspringtest.controllers;

import com.kenect.kenectspringtest.data.dto.ContactDto;
import com.kenect.kenectspringtest.services.ContactService;
import com.kenect.kenectspringtest.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.contactService.getContactById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            this.contactService.deleteContactById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
