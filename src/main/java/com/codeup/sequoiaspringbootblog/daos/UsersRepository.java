package com.codeup.sequoiaspringbootblog.daos;

import com.codeup.sequoiaspringbootblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    //    HQL
    User findByUsername(String username);
}
