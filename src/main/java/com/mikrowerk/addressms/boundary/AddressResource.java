package com.mikrowerk.addressms.boundary;

import com.mikrowerk.addressms.control.AddressRepository;
import com.mikrowerk.addressms.entity.ErrorMessage;
import com.mikrowerk.addressms.entity.Person;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author gfr
 */
@RestController
@RequestMapping("/persons")
public class AddressResource {
    
    @Autowired
    private AddressRepository addressRepository;
    
    @GetMapping()
    public List<Person> list() {
        return addressRepository.findAll();
    }
    
    @GetMapping("/{uuid}")
    public Person get(@PathVariable String uuid) {
        return addressRepository.getOne(uuid);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping("/")
    public ResponseEntity<Person> put( @RequestBody Person input) {
        return ResponseEntity.ok(addressRepository.save(input)) ;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Person input) {
        return ResponseEntity.ok(addressRepository.save(input)) ;
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        addressRepository.deleteById(uuid);
        return ResponseEntity.ok().build();
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public ErrorMessage handleError(HttpServletRequest req, Exception ex) {
        return ErrorMessage.builder().message(ex.getMessage()).build();
    }
    
}
