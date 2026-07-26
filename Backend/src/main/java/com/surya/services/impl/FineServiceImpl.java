package com.surya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.domain.FineStatus;
import com.surya.domain.FineType;
import com.surya.payload.dto.FineDTO;
import com.surya.payload.request.CreateFineRequest;
import com.surya.payload.request.WaiveFineRequest;
import com.surya.payload.response.PageResponse;
import com.surya.payload.response.PaymentInitiateResponse;
import com.surya.services.FineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

    @Override
    public FineDTO createFine(CreateFineRequest createFineRequest) {
        return null;
    }

    @Override
    public PaymentInitiateResponse payFine(Long fineId, String transactionId) {
        return null;
    }

    @Override
    public void markFineAsPaid(Long fineId, Long amount, String transactionId) {

    }

    @Override
    public FineDTO waiveFine(WaiveFineRequest waiveFineRequest) {
        return null;
    }

    @Override
    public List<FineDTO> getMyFines(FineStatus status, FineType type) {
        return null;
    }

    @Override
    public PageResponse<FineDTO> getAllFines(FineStatus status, FineType type, Long userId, int page, int size) {
        return null;
    }

}
