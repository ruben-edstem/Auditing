package com.edstem.auditing.repository;

import com.edstem.auditing.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
