package codecademy;

import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.domain.*;

import java.util.ArrayList;
import java.util.Date;

public class TestHelper {
    protected static final Date CURRENT_DATE = new Date();

    protected static final Student VALID_STUDENT = new Student(
            "valid@email.test",
            "Student Name",
            "Female",
            CURRENT_DATE,
            "Address",
            "Residence",
            "Country",
            "1000AA"
    );

    protected static final ContactPerson VALID_CONTACT_PERSON = new ContactPerson(
            "valid@email.test",
            "Contact Name"
    );

    protected static final Course VALID_COURSE = new Course(
            new ArrayList<>(),
            "Name",
            "Subject",
            "Introduction Text",
            "Level"
    );

    protected static final Certificate VALID_CERTIFICATE = new Certificate(
            0,
            0,
            "Name"
    );

    protected static final StudentEnrollment VALID_ENROLLMENT = new StudentEnrollment(
            VALID_STUDENT,
            VALID_COURSE,
            VALID_CERTIFICATE,
            CURRENT_DATE
    );

    protected static final Webcast VALID_WEBCAST = new Webcast(
            0,
            CURRENT_DATE,
            "Status",
            "Description",
            "Title",
            "0",
            "URL",
            "SpeakerName",
            "SpeakerOrg"
    );

    protected static final Module VALID_MODULE = new Module(
            0,
            CURRENT_DATE,
            "Status",
            "Title",
            0,
            "Description",
            VALID_COURSE,
            VALID_CONTACT_PERSON,
            "SerialNumber",
            0
    );

    protected static final View VALID_VIEW = new View(
            VALID_MODULE,
            VALID_STUDENT,
            0
    );

    protected static final Student INVALID_STUDENT = new Student(
            "invalid@email-test",
            "Student Name",
            "Female",
            null,
            "Address",
            "Residence",
            "Country",
            "1000AA"
    );

    protected static final ContactPerson INVALID_CONTACT_PERSON = new ContactPerson(
            "invalid@email-test",
            "Name"
    );

    protected static final Course INVALID_COURSE = new Course(
            null,
            "Name",
            "Subject",
            "Introduction Text",
            "Level"
    );

    protected static final Certificate INVALID_CERTIFICATE = new Certificate(
            0,
            0,
            ""
    );

    protected static final StudentEnrollment INVALID_ENROLLMENT = new StudentEnrollment(
            VALID_STUDENT,
            VALID_COURSE,
            INVALID_CERTIFICATE,
            CURRENT_DATE
    );

    protected static final Webcast INVALID_WEBCAST = new Webcast(
            0,
            CURRENT_DATE,
            "Status",
            "Description",
            "Title",
            "0",
            "",
            "",
            "SpeakerOrg"
    );

    protected static final Module INVALID_MODULE = new Module(
            0,
            CURRENT_DATE,
            "Status",
            "Title",
            0,
            "Description",
            INVALID_COURSE,
            VALID_CONTACT_PERSON,
            "SerialNumber",
            0
    );

    protected static final View INVALID_VIEW = new View(
            INVALID_MODULE,
            VALID_STUDENT,
            0
    );
}