package uploadimage.uploadimage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("useries")
@Data
@EqualsAndHashCode
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String teamName;
}