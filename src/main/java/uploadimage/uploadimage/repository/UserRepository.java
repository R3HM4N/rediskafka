package uploadimage.uploadimage.repository;

import org.springframework.data.repository.CrudRepository;
import uploadimage.uploadimage.entity.User;

public interface UserRepository extends CrudRepository<User,String> {
}
