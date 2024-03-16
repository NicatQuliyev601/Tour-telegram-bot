package az.nicat.tourtelegrambot.redis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RedisDataRepo extends JpaRepository<RedisEntity, Long> {

}
