package de.htwberlin.accountservice.service;

import de.htwberlin.accountservice.dto.AccountDto;
import de.htwberlin.accountservice.entities.Account;

public interface IAccountService {

    Account createAccount(AccountDto accountDto);


    AccountDto authenticateAccount(String email, String password);


    AccountDto updateAccount(AccountDto accountDto);

    boolean deleteAccount(String email, String password);



}
