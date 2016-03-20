package com.shuman.repository;

import com.shuman.data.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Modifying
    @Query("DELETE FROM Contact c WHERE c.uuid = :uuid")
    void deleteContact(@Param("uuid") String uuid);

    @Query("SELECT c FROM Contact c WHERE c.uuid = :uuid")
    Contact getContact(@Param("uuid") String uuid);
}
