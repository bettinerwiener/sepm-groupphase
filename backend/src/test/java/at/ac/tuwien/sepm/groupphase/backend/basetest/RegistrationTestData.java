package at.ac.tuwien.sepm.groupphase.backend.basetest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationTestData {
    Long ID = 6L;
    String TEST_FIRST_NAME = "Name";
    String TEST_LAST_NAME = "Nachname";
    String TEST_EMAIL = "name.nachname@email.com";
    String TEST_PASSWORD= "langesPassword";
    Boolean TEST_LOCKED= false;
    Boolean TEST_IS_EMPLOYEE = false;

    String USER_REGISTRATION_URI ="/api/v1/user/register";

    String  POST_REQUEST_VALID = "{\"firstName\":\"Peter\",\"lastName\":\"Mueller\",\"email\":\"peter.mueller@gmail.com\",\"password\":\"0123456789\"}";
       //String  POST_REQUEST2 = "{\"id\": null,\"email\":\"johnn77@gmail.com\", \"firstName\":\"Michl\",\"is_employee\":\"false\",\"lastName\":\"john\",\"password\":\"1213231321\",\"locked\":\"false\" }";
     String  POST_REQUEST_EXISTING_EMAIL = "{\"firstName\":\"hugo\",\"lastName\":\"hugoson\",\"email\":\"hugo@gmail.com\",\"password\":\"0123456789\"}";
    String  POST_REQUEST_WRONG_EMAIL = "{\"firstName\":\"hans\",\"lastName\":\"papa\",\"email\":\"hansi.papa\",\"password\":\"0123456789\"}";
    String  POST_REQUEST_BLANK_FIRST_NAME = "{\"firstName\":\"\",\"lastName\":\"Mueller\",\"email\":\"peter.mueller@gmail.com\",\"password\":\"0123456789\"}";
    String  POST_REQUEST_BLANK_LAST_NAME = "{\"firstName\":\"Peter\",\"lastName\":\"\",\"email\":\"peter.mueller@gmail.com\",\"password\":\"0123456789\"}";
    String  POST_REQUEST_SHORT_PASSWORD = "{\"firstName\":\"Peter\",\"lastName\":\"Mueller\",\"email\":\"peter.mueller@gmail.com\",\"password\":\"abc\"}";
}
