package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for the Module entity
 */
public class ModuleRepository extends DatabaseConnection{
    private final CourseRepository courseRepository;
    private final ContactPersonRepository contactPersonRepository;

    public ModuleRepository() {
        /*
         * dependency instantiation
         */
        courseRepository = new CourseRepository();
        contactPersonRepository = new ContactPersonRepository();
    }

    /**
     * get all Modules
     * @return list of Modules
     */
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

    /**
     * get a Module
     * @param ContentID the contentID property of the module to retrieve
     * @return Module with Module.contentID = contentID
     */
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

    /**
     * get all Modules for a Course
     * @param course the Course to retrieve modules for
     * @return list of Modules with Module.course = course
     */
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

    /**
     * create a Module
     * @param module the Module object to persist
     * @return completion of the transaction
     */
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

    /**
     * update a Module
     * @param module the modified module object
     * @return completion of the transaction
     */
    public boolean updateModule(Module module) {
        try {
            Validator.validateContent(module);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Module SET CourseName = %s " +
                        "WHERE  Title = '%s' AND Version = '%s' AND ContentID = '%s'",
                module.getCourse() != null ? String.format("'%s'", module.getCourse().getName()) : "NULL",
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
                // Disabled to prevent alert spam
                //Util.displaySuccess("Successfully updated module!");
                return true;
        }
    }

    /**
     * delete a Module
     * @param contentID property of the Module entity to remove
     * @param title title property of the Module entity to remove
     * @param version version property of the Module entity to remove
     * @return completion of the transaction
     */
    public boolean deleteModule(int contentID, String title, int version) {
        String sql = String.format(
                "DELETE FROM Module WHERE Title = '%s' AND Version = '%s' AND ContentID = '%s' INNER JOIN Content ON Module.ContentID = Content.ContentID",
                title,
                version,
                contentID
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
