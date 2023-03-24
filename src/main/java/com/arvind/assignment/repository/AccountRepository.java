package com.arvind.assignment.repository;

import com.arvind.assignment.domain.CurrentAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<CurrentAccount, Long> {
}
