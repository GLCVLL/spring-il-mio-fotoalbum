package org.java.spring.db.repo;

import java.util.List;

import org.java.spring.db.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	List<Category> findByName(String name);

}
