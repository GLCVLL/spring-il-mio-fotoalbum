package org.java.spring.db.repo;

import org.java.spring.db.pojo.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepo extends JpaRepository<ContactMessage, Integer> {

}
