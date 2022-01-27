package com.manager.loan.loans.repository;

import com.manager.loan.loans.model.FailedEmails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEmailsRepository extends JpaRepository<FailedEmails, Long> {
}
