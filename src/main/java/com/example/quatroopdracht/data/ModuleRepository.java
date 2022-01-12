package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ModuleRepository extends DatabaseConnection{
    private final CourseRepository courseRepository;
    private final ContactPersonRepository contactPersonRepository;

    public ModuleRepository() {
        courseRepository = new CourseRepository();
        contactPersonRepository = new ContactPersonRepository();
    }

    public List<Module> getAllAvailableModules() {
        String sql = "SELECT * FROM Module INNER JOIN Content ON Module.ContentID = Content.ContentID WHERE CourseName IS NULL";
        List<Module> modules = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    modules.add(new Module(
                            resultSet.getInt("ContentID"),
                            resultSet.getDate("PublicationDate"),
                            resultSet.getString("Status"),
                            resultSet.getString("Title"),
                            resultSet.getInt("Version"),
                            resultSet.getString("Description"),
                            courseRepository.getCourse(resultSet.getString("CourseName")),
                            contactPersonRepository.getContactPerson(resultSet.getString("EmailContactperson")),
                            resultSet.getString("SerialNumberCourse"),
                            resultSet.getInt("SerialNumberCourse")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return modules;
    }

    public Module getModule(int ContentID) {
        AtomicReference<Module> module = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Module WHERE ContentID = '%s' INNER JOIN Content ON Module.ContentID = Content.ContentID",
                ContentID
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    module.set(new Module(
                            ContentID,
                            resultSet.getDate("PublicationDate"),
                            resultSet.getString("Status"),
                            resultSet.getString("Title"),
                            resultSet.getInt("Version"),
                            resultSet.getString("Description"),
                            courseRepository.getCourse(resultSet.getString("CourseName")),
                            contactPersonRepository.getContactPerson(resultSet.getString("EmailContactperson")),
                            resultSet.getString("SerialNumberCourse"),
                            resultSet.getInt("SerialNumberCourse")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return module.get();
    }

    public List<Module> getModulesForCourse(Course course) {
        String sql = String.format("SELECT * FROM Module INNER JOIN Content ON Module.ContentID = Content.ContentID WHERE CourseName = '%s'", course.getName());
        List<Module> modules = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    modules.add(new Module(
                            resultSet.getInt("ContentID"),
                            resultSet.getDate("PublicationDate"),
                            resultSet.getString("Status"),
                            resultSet.getString("Title"),
                            resultSet.getInt("Version"),
                            resultSet.getString("Description"),
                            courseRepository.getCourse(resultSet.getString("CourseName")),
                            contactPersonRepository.getContactPerson(resultSet.getString("EmailContactperson")),
                            resultSet.getString("SerialNumberCourse"),
                            resultSet.getInt("SerialNumberCourse")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return modules;
    }

    public boolean addModule(Module module) {
        try {
            Validator.validateContent(module);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Module (PublicationDate, Status, Description, Title, Version, EmailContactPerson, Ser) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') INNER JOIN Content ON Module.ContentID = Content.ContentID",
                module.getContentItemId(),
                module.getPublicationDate(),
                module.getStatus(),
                module.getDescription(),
                module.getTitle(),
                module.getVersion(),
                module.getContactPerson().getEmail(),
                module.getSerialNumber(),
                module.getCourse().getName()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created module!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    public boolean updateModule(Module module) {
        try {
            Validator.validateContent(module);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Module SET CourseName = '%s' " +
                        "WHERE  Title = '%s' AND Version = '%s' AND ContentID = '%s'",
                module.getCourse().getName(),
                module.getTitle(),
                module.getVersion(),
                module.getContentItemId()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find module!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated module!");
                return true;
        }
    }

    public boolean deleteModule(int ContentID, String Title, int Version) {
        String sql = String.format(
                "DELETE FROM Module WHERE Title = '%s' AND Version = '%s' AND ContentID = '%s' INNER JOIN Content ON Module.ContentID = Content.ContentID",
                Title,
                Version,
                ContentID
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find module!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully deleted module!");
                return true;
        }
    }
}
