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

@Table(name = "SPR_ROLE")
@Entity
@Data
@Component(value = "role")
public class RoleEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long role_id;


    @Column(name="DESCRIPTION")
    private String role_Desc;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_R_TO_P",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id") })
    private List<PermissionEntity> rp = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_R_TO_U",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<UserEntity> ru = new ArrayList<>();

}
