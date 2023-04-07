package com.smithexperience.springbootrestfulwebservices.repository;

import com.smithexperience.springbootrestfulwebservices.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository: generic interface that takes in the "type" of entity [object] and the "data type" of the Primary Key of the entity [object]
 *
 * By EXTENDING this interface to JpaRepository, it automatically inherits the CRUD method implementations
 * --JpaRepository EXTENDS CrudRepository which provides the CRUD stub methods
 * --SimpleJpaRepository provides the implementation of those CRUD stub methods via JpaRepositoryImplementation interface
 * --JpaRepositoryImplementation interface EXTENDS JpaRepository\
 *
 * NOTE: because JpaRepository by default declares the interfaces and implementation class as @Repository and @Transactions
 *       we don't need to declare it in our custom repositories.
 */

// @Repository //not necessary but best practice is to use it to let others know it is a repository
// @Transactional //also not necessary
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Remember: standard CRUD methods are EXTENDED to this interface through JpaRepository, so we dont declare them here,
     * instead, we just call those methods in the SERVICE layer.  i.e. .save(), .findAllById(), etc
     *
     * We do howevery have to declare [stub] any custom DB Queries here
     */

    /**
     * Custom JPA method to query the DB for emails for use in EmailAlreadyExistException handling
     * Logic handle in the Service class
     */
    Optional<User> findByEmail(String email);

}
