package com.socialnetwork.user_service.repository;

import com.socialnetwork.user_service.entity.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Integer> {
}
