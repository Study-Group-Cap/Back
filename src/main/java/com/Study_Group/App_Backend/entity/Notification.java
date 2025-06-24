package com.Study_Group.App_Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "lost_item_id")
    private Long lostItemId;

    @Column(name = "found_item_id")
    private Long foundItemId;

    @Column(name = "created_at")
    private String createdAt;
}
