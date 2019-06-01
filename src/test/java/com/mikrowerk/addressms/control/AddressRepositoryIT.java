package com.mikrowerk.addressms.control;

import com.mikrowerk.addressms.entity.Person;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author gfr
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryIT {

    
    @Autowired
    private AddressRepository addressRepository;
    
    private Person p1 = Person.builder().name("Person1").build();
    private Person p2 = Person.builder().name("Person2").build();
    private Person p3 = Person.builder().name("Person3").build();
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        addressRepository.deleteAll();
        assertEquals("Repository cleanup failed after test", addressRepository.count(), 0l);
        
    }

    @Test
    public void saveAndReadPersons() {
        Person r1 = addressRepository.save(p1);
        assertNotNull(r1);
        assertNotNull(r1.getUuid());
        assertEquals(p1.getName(), r1.getName());
        Person t1 = addressRepository.getOne(r1.getUuid());
        assertEquals(r1, t1);
    }
    
    @Test
    public void saveAndFindPersons() {
        addressRepository.save(p1);
        addressRepository.save(p2);
        addressRepository.save(p3);
        assertEquals("Repository did not have the expected 3 entries", addressRepository.count(), 3l);
        List<Person> rl= addressRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        assertEquals(rl.get(0).getName(), p1.getName());
        assertEquals(rl.get(1).getName(), p2.getName());
        assertEquals(rl.get(2).getName(), p3.getName());
        
    }
    
}
