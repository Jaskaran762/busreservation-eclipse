package com.lti.repository;

public interface WalletRepository {

	double getBalance(int customerId);
	void setBalance(int customerId,double newWallet);

}