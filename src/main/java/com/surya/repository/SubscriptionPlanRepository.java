package com.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.modal.SubscriptionPlan;;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Boolean existsByPlanCode(String planCode);

}
