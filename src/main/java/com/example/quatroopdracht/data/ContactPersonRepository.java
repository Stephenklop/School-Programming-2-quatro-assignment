package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.ContactPerson;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for the ContactPerson entity
 */
public class ContactPersonRepository extends DatabaseConnection{
    /**
     * get all ContactPerson entities
     * @return list of ContactPeople
     */
    public List<ContactPerson> getAllContactPeople() {
        String sql = "SELECT * FROM ContactPerson";
        List<ContactPerson> contactPeople = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    contactPeople.add(new ContactPerson(
                            resultSet.getString("Email"),
                            resultSet.getString("Name")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return contactPeople;
    }

    /**
     * get a ContactPerson
     * @param email id of the ContactPerson to retrieve
     * @return ContactPerson with ContactPerson.email = ContactPerson
     */
    public ContactPerson getContactPerson(String email) {
        AtomicReference<ContactPerson> contactPerson = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM ContactPerson WHERE Email = '%s'",
                email
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    contactPerson.set(new ContactPerson(
                            resultSet.getString("Email"),
                            resultSet.getString("Name")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return contactPerson.get();
    }

    /**
     * create a ContactPerson
     * @param contactPerson the ContactPerson object to persist
     * @return completion of the transaction
     */
    public boolean addContactPerson(ContactPerson contactPerson) {
        try {
            Validator.validateContactPerson(contactPerson);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO ContactPerson VALUES ('%s', '%s')",
                contactPerson.getEmail(),
                contactPerson.getName()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created contact person!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }
}
