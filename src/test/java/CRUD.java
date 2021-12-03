import java.util.List;

public interface CRUD<T> {
    public T get();
    public T getById(Integer id);
    public  List<T> getAll();
    public void post();
    public void put();
    public void delete();
}
