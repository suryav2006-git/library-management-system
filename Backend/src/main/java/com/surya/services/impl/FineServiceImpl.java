package com.surya.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surya.domain.FineStatus;
import com.surya.domain.FineType;
import com.surya.domain.PaymentGateway;
import com.surya.domain.PaymentType;
import com.surya.mapper.FineMapper;
import com.surya.modal.BookLoan;
import com.surya.modal.Fine;
import com.surya.modal.User;
import com.surya.payload.dto.FineDTO;
import com.surya.payload.request.CreateFineRequest;
import com.surya.payload.request.PaymentInitiateRequest;
import com.surya.payload.request.WaiveFineRequest;
import com.surya.payload.response.PageResponse;
import com.surya.payload.response.PaymentInitiateResponse;
import com.surya.repository.BookLoanRepository;
import com.surya.repository.FineRepository;
import com.surya.services.FineService;
import com.surya.services.PaymentService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

    private final BookLoanRepository bookLoanRepository;
    private final FineRepository fineRepository;
    private final FineMapper fineMapper;
    private final UserService userService;
    private final PaymentService paymentService;

    @Override
    public FineDTO createFine(CreateFineRequest createFineRequest) {

        BookLoan bookLoan = bookLoanRepository.findById(createFineRequest.getBookLoanId())
                .orElseThrow(
                        () -> new RuntimeException("Book loan Doesn't Exist"));
        Fine fine = com.surya.modal.Fine.builder()
                .bookLoan(bookLoan)
                .user(bookLoan.getUser())
                .type(createFineRequest.getType())
                .amount(createFineRequest.getAmount())
                .status(FineStatus.PENDING)
                .reason(createFineRequest.getReason())
                .notes(createFineRequest.getNotes())
                .build();

        Fine savedFine = fineRepository.save(fine);

        return fineMapper.toDTO(savedFine);
    }

    @Override
    public PaymentInitiateResponse payFine(Long fineId, String transactionId) throws Exception {

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(
                        () -> new Exception("Fine Doesn't Exist"));

        if (fine.getStatus().equals(FineStatus.PAID)) {
            throw new Exception("Fine Already Paid");
        }
        if (fine.getStatus().equals(FineStatus.WAIVED)) {
            throw new Exception("Fine Waived");
        }

        User user = userService.getCurrentUser();

        PaymentInitiateRequest request = PaymentInitiateRequest.builder()
                .userId(user.getId())
                .fineId(fine.getId())
                .paymentType(PaymentType.FINE)
                .gateway(PaymentGateway.RAZORPAY)
                .amount(fine.getAmount())
                .description("Library Fine Payment")
                .build();

        return paymentService.initiatePayment(request);
    }

    @Override
    public void markFineAsPaid(Long fineId, Long amount, String transactionId) throws Exception {

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(
                        () -> new Exception("Fine Not Found With ID : " + fineId));

        fine.applyPayment(amount);
        fine.setTransactionId(transactionId);
        fine.setStatus(FineStatus.PAID);
        fine.setUpdatedAt(LocalDateTime.now());

        fineRepository.save(fine);
    }

    @Override
    public FineDTO waiveFine(WaiveFineRequest waiveFineRequest) throws Exception {

        Fine fine = fineRepository.findById(waiveFineRequest.getFineId())
                .orElseThrow(
                        () -> new Exception("Fine Not Found With  ID :" + waiveFineRequest.getFineId()));

        if (fine.getStatus() == FineStatus.WAIVED) {
            throw new Exception("Fine Has Already Been Waived");
        }

        if (fine.getStatus() == FineStatus.PAID) {
            throw new Exception("Fine Already Paid And Can't Be Waived");
        }

        User currentAdmin = userService.getCurrentUser();
        fine.waive(currentAdmin, waiveFineRequest.getReason());

        Fine savedFine = fineRepository.save(fine);

        return fineMapper.toDTO(savedFine);
    }

    @Override
    public List<FineDTO> getMyFines(FineStatus status, FineType type) throws Exception {

        User currentUser = userService.getCurrentUser();
        List<Fine> fines;

        if (status != null && type != null) {
            fines = fineRepository.findByUserId(currentUser.getId()).stream()
                    .filter(f -> f.getStatus() == status && f.getType() == type)
                    .collect(Collectors.toList());
        } else if (status != null) {
            fines = fineRepository.findByUserId(currentUser.getId()).stream()
                    .filter(f -> f.getStatus() == status)
                    .collect(Collectors.toList());
        } else if (type != null) {
            fines = fineRepository.findByUserIdAndType(currentUser.getId(), type);
        } else {
            fines = fineRepository.findByUserId(currentUser.getId());
        }

        return fines.stream().map(
                fineMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PageResponse<FineDTO> getAllFines(FineStatus status, FineType type, Long userId, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending());

        Page<Fine> findPage = fineRepository.findAllWithFilters(userId, status, type, pageable);

        return convertToPageResponse(findPage);
    }

    private PageResponse<FineDTO> convertToPageResponse(Page<Fine> findPage) {
        List<FineDTO> fineDTOs = findPage.getContent()
                .stream()
                .map(fineMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                fineDTOs,
                findPage.getNumber(),
                findPage.getSize(),
                findPage.getTotalElements(),
                findPage.getTotalPages(),
                findPage.isLast(),
                findPage.isFirst(),
                findPage.isEmpty());
    }

}
