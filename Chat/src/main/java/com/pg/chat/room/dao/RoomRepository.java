package com.pg.chat.room.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.pg.chat.room.domain.Room;

public interface RoomRepository extends MongoRepository<Room, String>, QuerydslPredicateExecutor<Room> {
	boolean existsByMasterId(long masterId);
}
