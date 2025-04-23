package com.Study_Group.App_Backend.repository;

import com.Study_Group.App_Backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
