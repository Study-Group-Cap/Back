package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.LostItemRequest;
import com.Study_Group.App_Backend.dto.LostItemSummaryResponse;
import com.Study_Group.App_Backend.dto.LostItemDetailResponse;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.service.LostItemService;
import com.Study_Group.App_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lost-items")
@RequiredArgsConstructor
public class LostItemController {

    private final LostItemService lostItemService;
    private final UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<LostItemDetailResponse> createLostItem(@PathVariable Long userId,
                                                                 @RequestBody LostItemRequest request) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(new LostItemDetailResponse(lostItemService.createLostItem(request, user)));
    }

    @GetMapping
    public ResponseEntity<List<LostItemSummaryResponse>> getAllLostItems() {
        return ResponseEntity.ok(lostItemService.getAllLostItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LostItemDetailResponse> getLostItem(@PathVariable Long id) {
        return ResponseEntity.ok(lostItemService.getLostItemById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LostItemSummaryResponse>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(lostItemService.getLostItemByCategory(category));
    }
}
