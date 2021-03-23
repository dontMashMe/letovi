package com.example.letovi.repositories;

import com.example.letovi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

   List<User> findByIpAddress(String ip);

}
