package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.exception.BusServiceException;
import com.lti.service.WalletService;

@RestController
@CrossOrigin
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@GetMapping(path="/addWallet")
	public double addWallet(@RequestParam int customerId,double amount) {
		double updatedWalletAmount=0.0;
		try {
			double walletBalance=walletService.getBalance(customerId);
			updatedWalletAmount=walletBalance+amount;
			
			//to recharge wallet
			walletService.setBalance(customerId, updatedWalletAmount);
		}
		catch(BusServiceException e) {
			System.out.println("Wallet not updated");
		}
		return updatedWalletAmount;
	}
	
	@GetMapping(path="/showWallet")
	public double showWallet(@RequestParam int customerId) {
		double walletBalance=walletService.getBalance(customerId);		
		return walletBalance;
	}
}
