package com.projetointegrador.repository;
import com.projetointegrador.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersistence extends JpaRepository<User, String> {

    User findByUser(String user);
}