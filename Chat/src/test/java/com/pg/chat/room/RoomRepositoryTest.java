package com.pg.chat.room;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pg.chat.room.dao.RoomRepository;
import com.pg.chat.room.domain.Room;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RoomRepositoryTest {
	@Autowired
	RoomRepository roomRepository;
	
	@Test
	@DisplayName("Room 생성 테스트")
	public void roomCreateTest() {
		// given
		Room room = Room.createNewRoom()
			.title("데이터 엔지니어링 플젝구함@@")
			.playgroundId(1L)
			.roomMasterId(1L)
			.build();

		roomRepository.save(room);
		System.out.println("room = " + room);
		// when
		Optional<Room> findRoomById = roomRepository.findById(room.getId());

		// then
		assertThat(findRoomById).isNotEmpty();
		System.out.println("findRoomById = " + findRoomById);
		assertThat(findRoomById.get().getId()).isEqualTo(room.getId());
	}
}
