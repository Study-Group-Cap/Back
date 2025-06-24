package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.FoundItemRequest;
import com.Study_Group.App_Backend.dto.FoundItemSummaryResponse;
import com.Study_Group.App_Backend.dto.FoundItemDetailResponse;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.service.FoundItemService;
import com.Study_Group.App_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/found-items")
@RequiredArgsConstructor
public class FoundItemController {

    private final FoundItemService foundItemService;
    private final UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<FoundItemDetailResponse> createFoundItem(@PathVariable Long userId,
                                                                   @RequestBody FoundItemRequest request) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(new FoundItemDetailResponse(foundItemService.createFoundItem(request, user)));
    }

    @GetMapping
    public ResponseEntity<List<FoundItemSummaryResponse>> getAllFoundItems() {
        return ResponseEntity.ok(foundItemService.getAllFoundItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoundItemDetailResponse> getFoundItem(@PathVariable Long id) {
        return ResponseEntity.ok(foundItemService.getFoundItemById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<FoundItemSummaryResponse>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(foundItemService.getFoundItemsByCategory(category));
    }
}
