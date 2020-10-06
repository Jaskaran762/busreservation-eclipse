package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.repository.WalletRepository;

@Service
public class WalletService{

	@Autowired
	private WalletRepository walletRepository;
	
	public double getBalance(int customerId) {
		
		return walletRepository.getBalance(customerId);
	}
	
	public void setBalance(int customerId,double updatedWallet) {
		
		walletRepository.setBalance(customerId,updatedWallet);
	}
}
