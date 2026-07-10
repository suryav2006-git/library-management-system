package com.surya.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.surya.exception.SubscriptionException;
import com.surya.payload.dto.SubscriptionDTO;
import com.surya.payload.response.PaymentInitiateResponse;

public interface SubscriptionService {

    PaymentInitiateResponse subscribe(SubscriptionDTO subscriptionDTO) throws Exception;

    SubscriptionDTO getUsersActiveSubscription(Long userId) throws Exception;

    SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) throws SubscriptionException;

    SubscriptionDTO activateSubscription(Long subscriptionId, Long paymentId) throws SubscriptionException;

    List<SubscriptionDTO> getAllSubscriptions(Pageable pageable);

    void deactivateExpiredSubscriptions() throws Exception;

}