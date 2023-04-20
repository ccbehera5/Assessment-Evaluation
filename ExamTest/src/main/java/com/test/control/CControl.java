package com.test.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.test.entity.Category;
import com.test.repo.CRepo;
import com.test.repo.PRepo;
import com.test.service.ServiceImpl;



@RestController
@RequestMapping("/api/category")
public class CControl {

	@Autowired
	CRepo prepo;
	@Autowired
	PRepo brepo;

	@Autowired
	ServiceImpl ser;


	@PostMapping()
	public ResponseEntity<Category> createUser(@RequestBody Category user){
		Category savedUser = prepo.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<Category>> getAllUsers(){
		List<Category> users = prepo.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getUserById(@PathVariable Long id){
		Optional<Category> user = prepo.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//sorting in Desc
	@GetMapping("/sorting")
	public Iterable<Category> getAllByCols (@RequestParam String field) {
		return prepo.findAll(Sort.by(Direction.DESC, field));
	}

	//sorting in Asc
	@GetMapping("/sorting1")
	public Iterable<Category> getAllByCols1 (@RequestParam String field) {
		return prepo.findAll(Sort.by(Direction.ASC, field));
	}

	@GetMapping("/api/{pageNo}/{pageSize}")
	public List<Category> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
		return ser.findPaginated(pageNo, pageSize);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateUser(@PathVariable("id") Long id,
			@RequestBody Category u){
		Optional<Category> user = prepo.findById(id);
		if(user.isPresent()) {
			user.get().setType(u.getType());
			user.get().setProduct(u.getProduct());
			return new ResponseEntity<>(prepo.save(user.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		Optional<Category> user = prepo.findById(id);
		if(user.isPresent()) {
			prepo.deleteById(id);
			return "Deleted";
		}
		else {
			return "not found";
		}
	}

}
