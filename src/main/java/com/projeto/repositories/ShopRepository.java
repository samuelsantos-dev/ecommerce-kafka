package com.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.projeto.domain.Shop;

public interface ShopRepository extends JpaRepositoryImplementation<Shop, Integer> {

	Optional<Shop> findByIdentifier(String identifier);

}
