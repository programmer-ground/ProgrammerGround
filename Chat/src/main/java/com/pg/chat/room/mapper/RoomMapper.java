package com.pg.chat.room.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pg.chat.room.domain.Room;
import com.pg.chat.room.dto.NewChatRoomCreateResponse;
import com.pg.chat.room.dto.RoomInfoResponse;

@Mapper(componentModel = "spring")
public interface RoomMapper {
	RoomMapper ROOM_MAPPER = Mappers.getMapper(RoomMapper.class);

	@Mapping(source = "id", target = "roomId")
	NewChatRoomCreateResponse toNewChatRoomResponse(Room room);

	@Mapping(source = "id", target = "roomId")
	RoomInfoResponse toRoomInfoResponse(Room room);
}
