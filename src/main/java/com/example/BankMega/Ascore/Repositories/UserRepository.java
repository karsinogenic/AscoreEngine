package com.example.BankMega.Ascore.Repositories;

import com.example.BankMega.Ascore.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
//    @Query("SELECT * FROM users u where u.name='naufal' ")
    List<Users> findByName(String name);

    @Query("SELECT u FROM Users u WHERE u.id = ?1 and u.name = ?2")
    List<Users> findUserByIdAndName(Integer id, String name);
}
