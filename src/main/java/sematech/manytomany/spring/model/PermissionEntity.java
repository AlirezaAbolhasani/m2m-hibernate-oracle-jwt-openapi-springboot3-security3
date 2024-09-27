package sematech.manytomany.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:10 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Table(name = "SPR_PERMISSION")
@Entity
@Data
@Component(value = "permission")
public class PermissionEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long permission_id;

    @Column(name = "DESCRIPTION")
    private String permission_desc;


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_PERMISSION_ROLE",
            joinColumns = { @JoinColumn(name = "permission_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<RoleEntity> pr = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_P_TO_U",
            joinColumns = { @JoinColumn(name = "permission_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<UserEntity> pu = new ArrayList<>();
}
