import org.testng.annotations.Test;

import java.util.List;

public class CRUDTest {

    @Test
    public void GetUsers(){

        ApiUsers apiUsers = new ApiUsers();

        List users = apiUsers.getAll();

        users.forEach(System.out::println);

        System.out.println(apiUsers.getById(1));
    }
}
