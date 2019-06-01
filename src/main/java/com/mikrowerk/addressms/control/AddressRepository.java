package com.mikrowerk.addressms.control;

import com.mikrowerk.addressms.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gfr
 */
public interface AddressRepository extends JpaRepository<Person, String> {
    
}
