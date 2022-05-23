package com.platzi.springboot.repository;

import com.platzi.springboot.dto.UserDto;
import com.platzi.springboot.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findMyUserByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findMyUserByEmailNative(String email);

    //metodo para ordenar a partir de un parametro
    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name, Sort sort);

    //Query Methods
//    List<User> findByNameOrderById(String name); hasta aqui

    List<User> findByName(String name);
    Optional<User> findByEmailAndName(String email, String name);

    //… where x.firstname like ?1
    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    //… where x.name = ?1 and x.email = ?2
    //Optional<User> findUsersByNameAndAndEmail(String name, String email);

    //… where x.name = ?1 or x.email = ?2
    //Optional<User> findUsersByNameOrAndEmail(String name, String email);

    //… where x.birthDate between ?1 and ?2
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameLikeOrderByIdDesc(String name);

    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameContainingOrderByIdDesc(String name);//ES EL MISMO RESULTADO de arriba (s contiene a ese usuario)

    //Using Named Parameters
    /*
    @Query("select u from User u where u.name = :name or u.email = :email")
    Optional<User> findByNameOrEmail(@Param("name") String name,
                                     @Param("email") String email);
    */

    @Query("SELECT new com.platzi.springboot.dto.UserDto(u.id, u.name, u.birthDate)" +
            "FROM User u " +
            "WHERE u.birthDate = :birthDate " +
            "and u.email = :email")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("birthDate") LocalDate birthDate,
                                                @Param("email") String email);
}