package com.example.awssqs.repository;

import com.example.awssqs.entity.RecipientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends R2dbcRepository<RecipientEntity, String> {
}
