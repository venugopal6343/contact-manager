package com.ust;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ContactRepository {

    private List<Contact> contacts = new LinkedList();
    private long id =0;

    private long generateId() {

         return ++id;
    }

    public Contact save(Contact contact) {
        if (contact.getId() == 0) {  // Assuming 0 means not set
            contact.setId(generateId());
        }
        contacts.add(contact);
        return contact;
    }

    public Contact findById(String id) throws ContactNotFoundException {
        return contacts.stream()
                .filter(contact -> {
                    return Objects.equals(contact.getId(), id);
                })
                .findFirst()
                .orElseThrow(() -> new ContactNotFoundException("Contact with ID " + id + " not found."));
    }


   public List<Contact> findById(long id) throws ContactNotFoundException {
        List<Contact> result = new LinkedList<>();
        for(Contact contact: contacts){
            if(contact.getId() == id){
                result.add(contact);
            }
        }
       if (result.isEmpty()) {
           throw new ContactNotFoundException("No contact found with id" +id);
       }

   }



    public List<Contact> findByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        List<Contact> result = new LinkedList<>();
        for (Contact contact : contacts) {
            if (contact.getPhoneNumbers().equals(phoneNumber)) {
                result.add(contact);
            }
        }
        if (result.isEmpty()) {
            throw new ContactNotFoundException("No contact with phone number " + phoneNumber + " found.");
        }
        return result;

    }

    public List<Contact> findByName(String name) throws ContactNotFoundException {
        List<Contact> result = new LinkedList<>();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                result.add(contact);
            }
        }
        if (result.isEmpty()) {
            throw new ContactNotFoundException("No contact with name " + name + " found.");
        }
        return result;
    }

}
