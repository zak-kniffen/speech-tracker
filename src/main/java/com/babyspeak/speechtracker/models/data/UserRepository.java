package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
