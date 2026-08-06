package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surya.mapper.WishlistMapper;
import com.surya.modal.Book;
import com.surya.modal.User;
import com.surya.modal.Wishlist;
import com.surya.payload.dto.WishlistDTO;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookRepository;
import com.surya.repository.WishlistRepository;
import com.surya.services.UserService;
import com.surya.services.WishlistService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserService userService;
    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    public WishlistDTO addToWishlist(Long bookId, String notes) throws Exception {

        User user = userService.getCurrentUser();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new Exception("Book Not Found"));

        if (wishlistRepository.existsByUserIdAndBookId(user.getId(), bookId)) {
            throw new Exception("Book Is Already In Your Wishlist");
        }

        Wishlist wishlist = new Wishlist();

        wishlist.setUser(user);
        wishlist.setBook(book);
        wishlist.setNotes(notes);

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDTO(savedWishlist);
    }

    @Override
    public void removeFromWishlist(Long bookId) throws Exception {
        User user = userService.getCurrentUser();

        Wishlist wishlist = wishlistRepository.findByUserIdAndBookId(user.getId(), bookId);

        if (wishlist == null) {
            throw new Exception("Book is Not in Your Wishlist");
        }

        wishlistRepository.delete(wishlist);
    }

    @Override
    public PageResponse<WishlistDTO> getMyWishList(int page, int size) throws Exception {

        Long userId = userService.getCurrentUser().getId();

        Pageable pageable = PageRequest.of(page, size, Sort.by("addedAt").descending());

        Page<Wishlist> wishlistPage = wishlistRepository.findByUserId(userId, pageable);

        return convertToPageResponse(wishlistPage);
    }

    private PageResponse<WishlistDTO> convertToPageResponse(Page<Wishlist> wishlistPage) {

        List<WishlistDTO> wishlistDTOs = wishlistPage.getContent()
                .stream()
                .map(wishlistMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                wishlistDTOs,
                wishlistPage.getNumber(),
                wishlistPage.getSize(),
                wishlistPage.getTotalElements(),
                wishlistPage.getTotalPages(),
                wishlistPage.isLast(),
                wishlistPage.isFirst(),
                wishlistPage.isEmpty());
    }

}
