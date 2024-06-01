package com.socialnetwork.user_service.repository;

import com.socialnetwork.user_service.entity.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Integer> {
    @Query("SELECT f FROM FriendShip f WHERE (f.userId = :userId OR f.friendId = :userId) AND f.status != 'REQUEST'")
    List<FriendShip> findAllRelations(@Param("userId") String userId);
    @Query("SELECT f FROM FriendShip f WHERE (f.userId = :userId AND f.friendId = :friendId) OR" +
            " (f.userId = :friendId AND f.friendId = :userId)")
    FriendShip findRelationBetweenUsers(@Param("userId") String userId, @Param("friendId") String friendId);

    @Query("SELECT f FROM FriendShip f WHERE f.friendId = :userId AND f.status = 'REQUEST'")
    List<FriendShip> findAllFriendRequestsByUserId(@Param("userId") String userId);
}
