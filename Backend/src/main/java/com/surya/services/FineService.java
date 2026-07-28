package com.surya.services;

import java.util.List;

import com.surya.domain.FineStatus;
import com.surya.domain.FineType;
import com.surya.payload.dto.FineDTO;
import com.surya.payload.request.CreateFineRequest;
import com.surya.payload.request.WaiveFineRequest;
import com.surya.payload.response.PageResponse;
import com.surya.payload.response.PaymentInitiateResponse;

public interface FineService {

    FineDTO createFine(CreateFineRequest createFineRequest);

    PaymentInitiateResponse payFine(Long fineId, String transactionId) throws Exception;

    void markFineAsPaid(Long fineId, Long amount, String transactionId) throws Exception;

    FineDTO waiveFine(WaiveFineRequest waiveFineRequest) throws Exception;

    List<FineDTO> getMyFines(FineStatus status, FineType type) throws Exception;

    PageResponse<FineDTO> getAllFines(
            FineStatus status,
            FineType type,
            Long userId,
            int page,
            int size);

}