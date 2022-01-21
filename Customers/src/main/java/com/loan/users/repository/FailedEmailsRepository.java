package com.loan.users.repository;

import com.loan.users.model.FailedEmails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEmailsRepository extends JpaRepository<FailedEmails,Long> {

}
