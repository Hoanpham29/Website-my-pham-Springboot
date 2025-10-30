package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
