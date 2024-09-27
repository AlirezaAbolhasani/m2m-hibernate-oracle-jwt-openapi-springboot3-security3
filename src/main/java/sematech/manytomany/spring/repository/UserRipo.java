package sematech.manytomany.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sematech.manytomany.spring.model.UserEntity;

import java.util.Optional;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:14 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public interface UserRipo extends JpaRepository<UserEntity, Long> {

    @Query("select p from user p where p.username =:username")
    public UserEntity findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
