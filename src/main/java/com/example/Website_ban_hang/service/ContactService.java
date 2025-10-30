package com.example.Website_ban_hang.service;

import com.example.Website_ban_hang.model.Contact;
import com.example.Website_ban_hang.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void save(Contact contact) {
        contactRepository.save(contact);
    }
}
