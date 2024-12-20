package de.htwberlin.accountservice.mapper;

import de.htwberlin.accountservice.dto.AccountDto;
import de.htwberlin.accountservice.entities.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setCity(account.getCity());
        accountDto.setCountry(account.getCountry());
        accountDto.setPhoneNumber(account.getPhoneNumber());
        accountDto.setAddress(account.getAddress());
        accountDto.setPostalCode(account.getPostalCode());
        accountDto.setGender(account.getGender());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account) {
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setCity(accountDto.getCity());
        account.setCountry(accountDto.getCountry());
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setAddress(accountDto.getAddress());
        account.setPostalCode(accountDto.getPostalCode());
        account.setGender(accountDto.getGender());
        return account;
    }


}
