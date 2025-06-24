package com.Study_Group.App_Backend.repository;

import com.Study_Group.App_Backend.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    List<FoundItem> findByCategory(String category);
}
