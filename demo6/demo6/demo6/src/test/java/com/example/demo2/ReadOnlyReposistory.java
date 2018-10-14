package com.example.demo2;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

public interface ReadOnlyReposistory<T> extends Repository<T, Long>  
{
	Optional<T> findOne(Long id);
	boolean exists(Long id);
	List<T> findAll();
	List<T> findAll(Iterable<Long> ids);
	List<T> findAll(Sort sort);
	Page<T> findAll(Pageable pageable);
	Long count();
		
}
