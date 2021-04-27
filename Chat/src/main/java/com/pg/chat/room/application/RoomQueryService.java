package com.pg.chat.room.application;

import static com.pg.chat.room.domain.QRoom.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.chat.global.common.PageInfo;
import com.pg.chat.room.dao.RoomRepository;
import com.pg.chat.room.domain.Room;
import com.pg.chat.room.dto.MyRoomInfoListResponse;
import com.pg.chat.room.dto.RoomInfoResponse;
import com.pg.chat.room.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomQueryService {
	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;

	public MyRoomInfoListResponse getMyRoomInfoList(long memberId, Pageable pageable) {
		Page<Room> roomPagingResult = roomRepository.findAll(room.members.any().memberId.eq(memberId), pageable);
		PageInfo pageInfo = PageInfo.builder()
			.totalElements(roomPagingResult.getTotalElements())
			.totalPage(roomPagingResult.getTotalPages())
			.currentPage(pageable.getPageNumber() + 1)
			.currentSize(pageable.getPageSize())
			.build();
		List<RoomInfoResponse> roomInfoResponses = roomPagingResult.get()
			.map(roomMapper::toRoomInfoResponse)
			.collect(Collectors.toList());
		return new MyRoomInfoListResponse(roomInfoResponses, pageInfo);
	}
}
