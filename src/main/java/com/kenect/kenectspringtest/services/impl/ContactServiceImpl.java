package com.kenect.kenectspringtest.services.impl;

import com.kenect.kenectspringtest.data.dto.AddressDto;
import com.kenect.kenectspringtest.data.dto.ContactDto;
import com.kenect.kenectspringtest.data.entities.*;
import com.kenect.kenectspringtest.data.repositories.CityRepository;
import com.kenect.kenectspringtest.data.repositories.ContactRepository;
import com.kenect.kenectspringtest.services.ContactService;
import com.kenect.kenectspringtest.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public Contact save(ContactDto newContact) throws NotFoundException {
        Contact contact = new Contact();
        contact.setName(newContact.getName());
        contact.setDescription(newContact.getDescription());

        for (String number: newContact.getPhones()) {
            Phone phoneEntity = new Phone();
            phoneEntity.setNumber(number);
            contact.addPhone(phoneEntity);
        }

        for (String email: newContact.getEmails()) {
            Email emailEntity = new Email();
            emailEntity.setEmail(email);
            contact.addEmail(emailEntity);
        }

        for (AddressDto addressDto: newContact.getAddresses()) {
            Address address = new Address();
            address.setStreet(addressDto.getStreet());
            address.setStreetNumber(addressDto.getStreetNumber());
            address.setReference(addressDto.getReference());
            Optional<City> city = cityRepository.findById(addressDto.getCity());
            if (city.isPresent()) {
                contact.addAddress(address);
            } else {
                throw new NotFoundException("City cannot be found");
            }
        }

        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(Long id) throws NotFoundException {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new NotFoundException("Contact cannot be found");
        }
    }

    @Override
    public void deleteContactById(Long id) throws NotFoundException{
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.deleteById(id);
        } else {
            throw new NotFoundException("Contact cannot be found");
        }
    }
}
