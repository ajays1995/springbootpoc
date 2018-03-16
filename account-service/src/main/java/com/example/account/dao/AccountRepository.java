package com.example.account.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.account.models.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
