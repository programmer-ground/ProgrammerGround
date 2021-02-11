package com.pg.programmerground.service;

import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.PlayGroundInfoDto;
import com.pg.programmerground.exception.PlaygroundNotFoundException;
import com.pg.programmerground.model.PlayGroundRepostiroy;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayGroundService {

  private final PlayGroundRepostiroy playGroundRepostiroy;
  private final OAuthUserService oAuthUserService;
  private final ModelMapper modelMapper;

  public PlayGroundService(PlayGroundRepostiroy playGroundRepostiroy,
      OAuthUserService oAuthUserService, ModelMapper modelMapper) {
    this.playGroundRepostiroy = playGroundRepostiroy;
    this.oAuthUserService = oAuthUserService;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public PlayGroundInfoDto findById(Long id) {
    Playground playGround = playGroundRepostiroy.findById(id)
        .orElseThrow(PlaygroundNotFoundException::new);
    return modelMapper.map(playGround, PlayGroundInfoDto.class);
  }
}
