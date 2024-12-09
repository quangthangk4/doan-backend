package com.doan.cnpm.repository;

import com.doan.cnpm.entity.Include;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncludeRepository extends JpaRepository<Include, Integer> {
}
