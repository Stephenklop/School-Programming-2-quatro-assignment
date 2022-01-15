package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Certificate;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CertificateRepository extends DatabaseConnection{
    public List<Certificate> getAllCertificates(){
        String sql = "SELECT * FROM Certificate";
        List<Certificate> certificates = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    certificates.add(new Certificate(
                            resultSet.getInt("CertificateID"),
                            resultSet.getFloat("Grade"),
                            resultSet.getString("EmployeeName")
                    ));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return certificates;

    }

    public Certificate getCertificate(int certificateID) {
        AtomicReference<Certificate> certificate = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Certificate WHERE CertificateID = '%s'",
                certificateID
        );
        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    certificate.set(new Certificate(
                            certificateID,
                            resultSet.getFloat("Grade"),
                            resultSet.getString("EmployeeName")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return certificate.get();
    }

    public boolean addCertificate(Certificate certificate) {
        try {
            Validator.validateCertificate(certificate);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Certificate VALUES ('%s', '%s', '%s')",
                certificate.getCertificateId(),
                certificate.getGrade(),
                certificate.getEmployeeName()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created certificate!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    public boolean updateCertificate(Certificate certificate) {
        try {
            Validator.validateCertificate(certificate);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Certificate SET Grade = '%s', EmployeeName = '%s' WHERE CertificateID = '%s'",
                certificate.getGrade(),
                certificate.getEmployeeName(),
                certificate.getCertificateId()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find certificate!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated certificate!");
                return true;
        }
    }

    public boolean deleteCertificate(int certificateID) {
        String sql = String.format(
                "DELETE FROM Certificate WHERE CertificateID = '%s'",
                certificateID
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find certificate!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully deleted certificate!");
                return true;
        }
    }
}
