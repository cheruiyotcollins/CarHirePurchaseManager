package com.loan.users.repository;

import com.loan.users.model.CustomerPassportPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<CustomerPassportPhotos,Long> {

}
