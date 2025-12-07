package com.umc.umc9th.domain.auth.jwt.repository;

import com.umc.umc9th.domain.auth.jwt.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {
    
    Boolean existsByRefresh(String refresh);
    
    void deleteByRefresh(String refresh);
    
    void deleteByUserEmail(String userEmail);
}
