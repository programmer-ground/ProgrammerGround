package com.pg.programmerground.controller;


import com.pg.programmerground.dto.PlayGroundInfoDto;
import com.pg.programmerground.service.PlayGroundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/playground")
public class PlayGroundController {

  private final PlayGroundService playGroundService;

  public PlayGroundController(PlayGroundService playGroundService) {
    this.playGroundService = playGroundService;
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getPlaygrounds(@PathVariable Long id) {
    PlayGroundInfoDto playGroundInfo = playGroundService.findById(id);
    return ResponseEntity.ok(playGroundInfo);
  }

}
