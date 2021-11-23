package com.eurosoftware.finance.repository;

import com.eurosoftware.finance.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

    List<Finance> findAllByProjectId(Long projectId);

    void deleteAllByProjectId(Long projectId);

}
