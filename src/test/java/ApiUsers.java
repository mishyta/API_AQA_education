import java.util.List;

public class ApiUsers extends AbstractApiCRUD<User> implements CRUD<User>{

    public ApiUsers(){
        setBaseUri("https://reqres.in/api");
        setBasePath("/users");
    }



    @Override
    public List<User> getAll() {
        return getAll(User.class);
    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return getById(User.class ,id);
    }

    @Override
    public void post() {

    }

    @Override
    public void put() {

    }

    @Override
    public void delete() {

    }
}
