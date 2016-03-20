package com.shuman.rest;

import com.shuman.data.Contact;
import com.shuman.mapper.ContactModelMapper;
import com.shuman.model.ContactModel;
import com.shuman.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/contacts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {
    @Inject
    ContactRepository contactService;


    @RequestMapping(value = "/getContacts", method = RequestMethod.GET)
    public ResponseEntity<List<ContactModel>> getContacts() throws Exception {
        List<ContactModel> contacts = ContactModelMapper.toModel(contactService.findAll());
        return new ResponseEntity<List<ContactModel>>(contacts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<List<ContactModel>> editContact(ContactModel contactModel) throws Exception {
        System.out.println("EDITED User:" + contactModel.getName());
        Contact contact = contactService.getContact(contactModel.getUuid());
        ContactModelMapper.mergeModel(contactModel, contact);
        contactService.save(contact);

        List<ContactModel> contactModels = ContactModelMapper.toModel(contactService.findAll());
        return new ResponseEntity<List<ContactModel>>(contactModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<ContactModel>> addContact(ContactModel model) throws Exception {
        System.out.println("ADD User:" + model.getName());
        Contact contact = new Contact();
        ContactModelMapper.mergeModel(model, contact);
        contact.setUuid(UUID.randomUUID().toString());
        contactService.save(contact);
        List<ContactModel> contactModels = ContactModelMapper.toModel(contactService.findAll());
        return new ResponseEntity<List<ContactModel>>(contactModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<List<ContactModel>> deleteContact(@PathVariable("id") String uuid) {
        contactService.deleteContact(uuid);
        List<ContactModel> contactModels = ContactModelMapper.toModel(contactService.findAll());
        return new ResponseEntity<List<ContactModel>>(contactModels, HttpStatus.OK);
    }
}
