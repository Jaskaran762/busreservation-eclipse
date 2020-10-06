package com.lti.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class WalletRepositoryImpl extends GenericRepositoryImpl implements WalletRepository{

	
	@Override
	public double getBalance(int customerId) {
		
		double walletBalance=(double)entityManager.createQuery("select c.wallet from Customer c where c.id=:customerId")
				.setParameter("customerId", customerId)
				.getSingleResult();
		return walletBalance;
	}
	
	@Override
	@Transactional
	public void setBalance(int customerId,double updatedWallet) {
		entityManager.createQuery("update Customer c set c.wallet=:updatedWallet where c.id=:customerId")
				.setParameter("updatedWallet", updatedWallet)
				.setParameter("customerId", customerId)
				.executeUpdate();
	} 
}
