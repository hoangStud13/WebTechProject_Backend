package de.htwberlin.accountservice.controller;


import de.htwberlin.accountservice.constants.AccountConstants;
import de.htwberlin.accountservice.dto.AccountDto;
import de.htwberlin.accountservice.dto.ResponseDto;
import de.htwberlin.accountservice.entities.Account;
import de.htwberlin.accountservice.mapper.AccountMapper;
import de.htwberlin.accountservice.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/AccountAPI")
public class AccountController {

    private IAccountService iAccountService;

    @Autowired
    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @GetMapping("/accounts") // im Body daten geben
    public ResponseEntity<AccountDto> getAccount(
            @RequestParam String email,
            @RequestParam String password) {
        AccountDto accountDto = iAccountService.authenticateAccount(email, password);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }


    @PostMapping("/accounts")
    public ResponseEntity<ResponseDto> addAccount(@RequestBody AccountDto accountDto){
        iAccountService.createAccount(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.MESSAGE_201,AccountConstants.STATUS_201));
    }

    @PutMapping("/accounts")
    public ResponseEntity<AccountDto> updateTicket(@RequestBody AccountDto accountDto){
        AccountDto accountDto1 = iAccountService.updateAccount(accountDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accountDto1);

    }


    @DeleteMapping("/accounts")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestBody AccountDto accountDto){
        boolean isDeleted = iAccountService.deleteAccount(accountDto.getEmail(),accountDto.getPassword());
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.MESSAGE_200,AccountConstants.STATUS_200));
        }else{
            return  ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.MESSAGE_417_DELETE,AccountConstants.STATUS_417));
        }
    }

}
