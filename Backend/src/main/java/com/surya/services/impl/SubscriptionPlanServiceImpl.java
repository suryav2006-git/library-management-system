package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.surya.mapper.SubscriptionPlanMapper;
import com.surya.modal.SubscriptionPlan;
import com.surya.modal.User;
import com.surya.payload.dto.SubscriptionPlanDTO;
import com.surya.repository.SubscriptionPlanRepository;
import com.surya.services.SubscriptionPlanService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository planRepository;
    private final SubscriptionPlanMapper planMapper;
    private final UserService userService;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception {

        if (planRepository.existsByPlanCode(planDTO.getPlanCode())) {
            throw new Exception("Plan Code is Already Exists!");
        }

        SubscriptionPlan plan = planMapper.toEntity(planDTO);

        User currentUser = userService.getCurrentUser();
        plan.setCreatedBy(currentUser.getFullName());
        plan.setUpdatedBy(currentUser.getFullName());
        SubscriptionPlan savedPlan = planRepository.save(plan);

        return planMapper.toDTO(savedPlan);
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) throws Exception {
        SubscriptionPlan existingPlan = planRepository.findById(planId).orElseThrow(
                () -> new Exception("Subscription Plan Not Found! "));

        planMapper.updateEntity(existingPlan, planDTO);

        User currentUser = userService.getCurrentUser();
        existingPlan.setUpdatedBy(currentUser.getFullName());

        SubscriptionPlan updatedPlan = planRepository.save(existingPlan);

        return planMapper.toDTO(updatedPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long planId) throws Exception {
        SubscriptionPlan existingPlan = planRepository.findById(planId).orElseThrow(
                () -> new Exception("Subscription Plan Not Found! "));

        planRepository.delete(existingPlan);

    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {

        List<SubscriptionPlan> planList = planRepository.findAll();

        return planList.stream().map(
                planMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SubscriptionPlan getBySubscriptionPlanCode(String subscriptionPlanCode) throws Exception {
        SubscriptionPlan plan = subscriptionPlanRepository.findByPlanCode(subscriptionPlanCode);
        if (plan == null) {
            throw new Exception("Subscription Plan Not Found");
        }
        return plan;
    }

}
