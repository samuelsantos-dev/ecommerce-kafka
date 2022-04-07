package com.projeto.repositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.projeto.domain.ShopItem;

public interface ShopItemRepository extends JpaRepositoryImplementation<ShopItem, Integer>{

}
