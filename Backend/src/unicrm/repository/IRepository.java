package unicrm.repository;
import java.util.List;
public interface IRepository<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}
