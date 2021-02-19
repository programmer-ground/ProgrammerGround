package com.pg.programmerground.service;

import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.PlayGroundInfoDto;
import com.pg.programmerground.exception.PlaygroundNotFoundException;
import com.pg.programmerground.model.PlaygroundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaygroundService {

  private final PlaygroundRepository playGroundRepository;
  private final OAuthUserService oAuthUserService;
  private final ModelMapper modelMapper;

  public PlaygroundService(PlaygroundRepository playGroundRepository,
      OAuthUserService oAuthUserService, ModelMapper modelMapper) {
    this.playGroundRepository = playGroundRepository;
    this.oAuthUserService = oAuthUserService;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public PlayGroundInfoDto findById(Long id) {
    Playground playGround = playGroundRepository.findById(id)
        .orElseThrow(PlaygroundNotFoundException::new);
    return modelMapper.map(playGround, PlayGroundInfoDto.class);
  }
}
