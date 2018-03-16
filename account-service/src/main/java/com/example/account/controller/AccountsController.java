package com.example.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.dao.AccountRepository;
import com.example.account.models.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="Account API", description="Operations pertaining to Account API")
public class AccountsController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	protected static Logger logger = LoggerFactory.getLogger(AccountsController.class.getName());
		
	@ApiOperation(value = "View Account Details by Account Number",response = Account.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved Account"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	)
    @RequestMapping(value="/account/{accountNumber}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<Account> findByAccountNumber(@PathVariable Integer accountNumber) {
		
		logger.info(String.format("Account.findByNumber(%s)", accountNumber));
		
		Account account = accountRepository.findOne(accountNumber.longValue());;
		
		if(account == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(account);
	
	}
	
	@ApiOperation(value = "View All Accounts",response = Iterable.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully Retrieved Accounts List"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	)
    @RequestMapping(value="/account",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<Iterable<Account>> findAllAccounts() {
		
		logger.info(String.format("Account.findAllAccounts"));
		
		Iterable<Account> accounts = accountRepository.findAll();
		
		return ResponseEntity.ok().body(accounts);
	}	

}
