package com.test.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Product;
import com.test.repo.CRepo;
import com.test.repo.PRepo;
import com.test.service.ServiceImpl;

@RestController
@RequestMapping("/api/Product")
public class PControl {
	
	@Autowired
	CRepo prepo;
	@Autowired
	PRepo brepo;

	@Autowired
	ServiceImpl ser;
	
	@GetMapping()
	public ResponseEntity<List<Product>> getAllUsers(){
		List<Product> users = brepo.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/sorting")
	public Iterable<Product> getAllByCols (@RequestParam String field) {
		return brepo.findAll(Sort.by(Direction.DESC, field));
	}

	//sorting in Asc
	@GetMapping("/sorting1")
	public Iterable<Product> getAllByCols1 (@RequestParam String field) {
		return brepo.findAll(Sort.by(Direction.ASC, field));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getUserById(@PathVariable Long id){
		Optional<Product> user = brepo.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
