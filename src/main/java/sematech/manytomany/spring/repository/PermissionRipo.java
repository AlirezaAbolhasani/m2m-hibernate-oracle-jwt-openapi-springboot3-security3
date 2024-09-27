package sematech.manytomany.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sematech.manytomany.spring.model.PermissionEntity;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:14 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public interface PermissionRipo extends JpaRepository<PermissionEntity, Long> {
}
