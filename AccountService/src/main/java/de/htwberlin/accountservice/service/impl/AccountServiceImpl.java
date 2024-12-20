package de.htwberlin.accountservice.service.impl;

import de.htwberlin.accountservice.dto.AccountDto;
import de.htwberlin.accountservice.entities.Account;
import de.htwberlin.accountservice.exception.ResourceNotFoundException;
import de.htwberlin.accountservice.mapper.AccountMapper;
import de.htwberlin.accountservice.repository.AccountRepository;
import de.htwberlin.accountservice.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        if(accountDto!=null){
            Account account = AccountMapper.mapToAccount(accountDto, new Account());
            return accountRepository.save(account);
        }else{
            return null;
        }

    }



    @Override
    public AccountDto authenticateAccount(String email, String password) {
        Account account = accountRepository.findByEmailAndPassword(email,password).orElseThrow(
                ()->new ResourceNotFoundException("Email", "email",""+email)
        );

        AccountDto accountDto = AccountMapper.mapToAccountDto(account,new AccountDto());
        accountDto.setId(account.getId());
        return accountDto;
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        boolean isUpdated = false;


        if(accountDto != null) {
            // Prüfen, ob Ticket existiert, andernfalls Exception werfen
            Account account = accountRepository.findByEmailAndPassword(accountDto.getEmail(),accountDto.getPassword()).orElseThrow(
                    () -> new  ResourceNotFoundException("Email", "email",""+accountDto.getEmail())
            );

            // Mapping vom TicketDto auf Ticket
            account = AccountMapper.mapToAccount(accountDto, account);

            // Ticket in der Datenbank aktualisieren
            AccountDto accountDto1 = AccountMapper.mapToAccountDto(accountRepository.save(account),new AccountDto());
            return  accountDto1;
        } else {
            throw new IllegalArgumentException("TicketDto or flightId must not be null");
        }




    }

    @Override
    public boolean deleteAccount(String email, String password) {
        Account account = accountRepository.findByEmailAndPassword(email,password).orElseThrow(
                ()->new ResourceNotFoundException("Email", "email",""+email)
        );

        accountRepository.delete(account);
        return true;
    }


}
