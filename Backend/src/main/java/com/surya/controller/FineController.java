package com.surya.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surya.domain.FineStatus;
import com.surya.domain.FineType;
import com.surya.payload.dto.FineDTO;
import com.surya.payload.request.CreateFineRequest;
import com.surya.payload.request.WaiveFineRequest;
import com.surya.payload.response.PageResponse;
import com.surya.payload.response.PaymentInitiateResponse;
import com.surya.services.FineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/fines")
public class FineController {

    private final FineService fineService;

    @PostMapping
    public ResponseEntity<?> createFine(
            @Valid @RequestBody CreateFineRequest fineRequest) throws Exception {
        FineDTO fineDTO = fineService.createFine(fineRequest);
        return ResponseEntity.ok(fineDTO);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payFine(
            @PathVariable Long id,
            @RequestParam(required = false) String transactionId) throws Exception {
        PaymentInitiateResponse res = fineService.payFine(id, transactionId);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<?> waiveFine(
            @Valid @RequestBody WaiveFineRequest waiveFineRequest) throws Exception {
        FineDTO fineDTO = fineService.waiveFine(waiveFineRequest);
        return ResponseEntity.ok(fineDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyFines(
            @RequestParam(required = false) FineStatus status,
            @RequestParam(required = false) FineType type) throws Exception {
        List<FineDTO> fines = fineService.getMyFines(status, type);

        return ResponseEntity.ok(fines);
    }

    @GetMapping
    public ResponseEntity<?> getAllFines(
            @RequestParam(required = false) FineStatus status,
            @RequestParam(required = false) FineType type,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResponse<FineDTO> fines = fineService.getAllFines(status, type, userId, page, size);

        return ResponseEntity.ok(fines);
    }

}
