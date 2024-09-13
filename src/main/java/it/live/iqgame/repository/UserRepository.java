package it.live.iqgame.repository;

import it.live.iqgame.entity.User;
import it.live.iqgame.payload.UserDTOs.AllUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT new it.live.iqgame.payload.UserDTOs.AllUserDTO(u.id, u.name, u.surname, u.education.name, u.phoneNumber, u.avaName, u.createdAt) FROM users u order by u.createdAt desc")
    Page<AllUserDTO> findAllByPage(Pageable pageable);

    @Query("SELECT new it.live.iqgame.payload.UserDTOs.AllUserDTO(u.id , u.name , u.surname , u.education.name , u.phoneNumber , u.avaName ,u.createdAt) FROM users u WHERE " +
            "(LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) " +
            "AND (u.phoneNumber LIKE CONCAT('%', :phoneNumber, '%') OR :phoneNumber IS NULL)")
    Page<AllUserDTO> searchByNameOrPhoneNumber(@Param("name") String name,
                                               @Param("phoneNumber") String phoneNumber,
                                               Pageable pageable);
}