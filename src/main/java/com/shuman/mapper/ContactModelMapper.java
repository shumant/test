package com.shuman.mapper;

import com.shuman.data.Contact;
import com.shuman.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactModelMapper {


    public static void mergeModel(final ContactModel model, Contact contact) {
        contact.setName(model.getName());
        contact.setSex(model.getSex());
        contact.setPosition(model.getPosition());
        contact.setAge(model.getAge());
        contact.setPhoto(model.getPhoto());
        contact.setDesc(model.getDesc());
    }

    public static ContactModel toModel(final Contact contact) {
        ContactModel contactModel = new ContactModel();
        contactModel.setUuid(contact.getUuid());
        contactModel.setName(contact.getName());
        contactModel.setSex(contact.getSex());
        contactModel.setPosition(contact.getPosition());
        contactModel.setAge(contact.getAge());
        contactModel.setPhoto(contact.getPhoto());
        contactModel.setDesc(contact.getDesc());
        return contactModel;
    }

    public static List<ContactModel> toModel(final Iterable<Contact> contacts) {
        List<ContactModel> contactModels = new ArrayList<ContactModel>();
        for (Contact contact : contacts) {
            contactModels.add(toModel(contact));
        }
        return contactModels;
    }


}
