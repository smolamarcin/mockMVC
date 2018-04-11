package com.smola.demo.repository;

import com.smola.demo.model.Greeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloWorldRepository extends CrudRepository<Greeting, Long> {}
