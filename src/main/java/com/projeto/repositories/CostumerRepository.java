package com.projeto.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.projeto.domain.Costumer;

public interface CostumerRepository extends JpaRepositoryImplementation<Costumer, Integer> {

}
