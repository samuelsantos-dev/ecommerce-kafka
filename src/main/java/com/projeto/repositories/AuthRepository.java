package com.projeto.repositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.projeto.domain.Auth;

public interface AuthRepository extends JpaRepositoryImplementation<Auth, Integer>{

}
