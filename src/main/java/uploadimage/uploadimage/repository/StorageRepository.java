package uploadimage.uploadimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uploadimage.uploadimage.entity.ImageData;


@Repository
public interface StorageRepository extends JpaRepository<ImageData,Long> {
}
