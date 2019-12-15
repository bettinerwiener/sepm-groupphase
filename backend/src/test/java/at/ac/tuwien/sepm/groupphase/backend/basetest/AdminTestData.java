package at.ac.tuwien.sepm.groupphase.backend.basetest;

public interface AdminTestData {

    String ADMIN_URI = "/api/v1/admin";
    String UPDATE_REQUEST = "{\"id\": 1,\"firstName\":\"Peter\",\"lastName\":\"Mueller\"," +
        "\"email\":\"peter.mueller@gmail.com\",\"password\":\"0123456789\",\"locked\": false,\"isEmployee\": false}";

}
