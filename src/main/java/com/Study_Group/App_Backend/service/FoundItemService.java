package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.FoundItemDetailResponse;
import com.Study_Group.App_Backend.dto.FoundItemRequest;
import com.Study_Group.App_Backend.dto.FoundItemSummaryResponse;
import com.Study_Group.App_Backend.entity.FoundItem;
import com.Study_Group.App_Backend.entity.LostItem;
import com.Study_Group.App_Backend.entity.Notification;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.FoundItemRepository;
import com.Study_Group.App_Backend.repository.LostItemRepository;
import com.Study_Group.App_Backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoundItemService {

    private final FoundItemRepository foundItemRepository;
    private final LostItemRepository lostItemRepository;
    private final NotificationRepository notificationRepository;

    public FoundItem createFoundItem(FoundItemRequest request, User user) {
        FoundItem foundItem = new FoundItem();
        foundItem.setUser(user);
        foundItem.setTitle(request.getTitle());
        foundItem.setContent(request.getContent());
        foundItem.setCategory(request.getCategory());
        foundItem.setImageUrl(request.getImageUrl());
        foundItem.setCreatedAt(now());

        FoundItem saved = foundItemRepository.save(foundItem);

        List<LostItem> matched = lostItemRepository.findByCategory(saved.getCategory());
        if (!matched.isEmpty()) {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage("같은 카테고리의 잃어버린 물건이 등록되었습니다.");
            notification.setFoundItemId(saved.getId());
            notification.setCreatedAt(now());
            notificationRepository.save(notification);
        }
        return saved;
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public List<FoundItemSummaryResponse> getAllFoundItems() {
        return foundItemRepository.findAll().stream()
                .map(FoundItemSummaryResponse::new)
                .toList();
    }

    public FoundItemDetailResponse getFoundItemById(Long id) {
        FoundItem item = foundItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 슬롯을 찾을 수 없습니다."));
        return new FoundItemDetailResponse(item);
    }

    public List<FoundItemSummaryResponse> getFoundItemsByCategory(String category) {
        return foundItemRepository.findByCategory(category).stream()
                .map(FoundItemSummaryResponse::new)
                .toList();
    }

    public List<FoundItemSummaryResponse> getFoundItems(String category) {
        List<FoundItem> items;

        if (category == null || category.isBlank()) {
            items = foundItemRepository.findAll();
        } else {
            items = foundItemRepository.findByCategory(category);
        }

        return items.stream()
                .map(FoundItemSummaryResponse::new)
                .toList();
    }

}
