package com.ajproject.realestatecrm.security;

import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private BrokerRepository brokerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Broker broker = brokerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Broker not found with email: " + email));

        return UserDetailsImpl.build(broker);
    }
}
