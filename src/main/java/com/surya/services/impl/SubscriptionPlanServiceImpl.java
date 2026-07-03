package com.surya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.payload.dto.SubscriptionPlanDTO;
import com.surya.services.SubscriptionPlanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) {
        return null;
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) {
        return null;
    }

    @Override
    public void deleteSubscriptionPlan(Long planId) {

    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {
        return null;
    }

}
