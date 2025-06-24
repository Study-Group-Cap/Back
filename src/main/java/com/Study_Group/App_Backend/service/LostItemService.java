package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.LostItemDetailResponse;
import com.Study_Group.App_Backend.dto.LostItemRequest;
import com.Study_Group.App_Backend.dto.LostItemSummaryResponse;
import com.Study_Group.App_Backend.entity.FoundItem;
import com.Study_Group.App_Backend.entity.LostItem;
import com.Study_Group.App_Backend.entity.Notification;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.FoundItemRepository;
import com.Study_Group.App_Backend.repository.LostItemRepository;
import com.Study_Group.App_Backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LostItemService {

    private final LostItemRepository lostItemRepository;
    private final FoundItemRepository foundItemRepository;
    private final NotificationRepository notificationRepository;

    public LostItem createLostItem(LostItemRequest request, User user) {
        LostItem lostItem = new LostItem();
        lostItem.setUser(user);
        lostItem.setTitle(request.getTitle());
        lostItem.setContent(request.getContent());
        lostItem.setCategory(request.getCategory());
        lostItem.setImageUrl(request.getImageUrl());
        lostItem.setCreatedAt(now());

        LostItem saved = lostItemRepository.save(lostItem);

        List<FoundItem> matched = foundItemRepository.findByCategory(saved.getCategory());
        if(!matched.isEmpty()) {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage("같은 카테고리의 주운 물건이 등록되었습니다.");
            notification.setLostItemId(saved.getId());
            notification.setCreatedAt(now());
            notificationRepository.save(notification);
        }
        return saved;
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH::mm:ss"));
    }

    public List<LostItemSummaryResponse> getAllLostItems() {
        return lostItemRepository.findAll().stream()
                .map(LostItemSummaryResponse::new)
                .toList();
    }

    public LostItemDetailResponse getLostItemById(Long id) {
        LostItem item = lostItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 분실물을 찾을 순 없습니다."));
        return new LostItemDetailResponse(item);
    }

    public List<LostItemSummaryResponse> getLostItemByCategory(String category) {
        return lostItemRepository.findByCategory(category).stream()
                .map(LostItemSummaryResponse::new)
                .toList();
    }

    public List<LostItemSummaryResponse> getLostItems(String category) {
        List<LostItem> items = (category == null) ?
                lostItemRepository.findAll() :
                lostItemRepository.findByCategory(category);

        return items.stream()
                .map(LostItemSummaryResponse::new)
                .toList();
    }
}
