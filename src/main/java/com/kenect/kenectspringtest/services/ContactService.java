package com.kenect.kenectspringtest.services;

import com.kenect.kenectspringtest.data.dto.ContactDto;
import com.kenect.kenectspringtest.data.entities.Contact;
import com.kenect.kenectspringtest.utils.NotFoundException;

public interface ContactService {
    Contact save(ContactDto newContact) throws NotFoundException;
    Contact getContactById(Long id) throws NotFoundException;
    void deleteContactById(Long id) throws NotFoundException;
}
