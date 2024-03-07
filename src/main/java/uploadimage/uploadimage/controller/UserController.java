package uploadimage.uploadimage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uploadimage.uploadimage.dto.TeamGetByIdDto;
import uploadimage.uploadimage.dto.TeamResponse;
import uploadimage.uploadimage.entity.User;
import uploadimage.uploadimage.service.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/useries")
public class UserController {

    private final UserServiceImpl userService;



    @GetMapping
    public ResponseEntity<Iterable<User>> getAllTeams() {
        Iterable<User> teams = userService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamGetByIdDto> getById(@PathVariable String id) {
        TeamGetByIdDto team = userService.getById(id);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createTeam(@RequestBody TeamResponse teamDto) {
        User createdTeam = userService.createTeam(teamDto);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CachePut(cacheNames = "myCache", key = "#id")
    public ResponseEntity<User> updateTeam(@PathVariable String id, @RequestBody TeamResponse teamDto) {
        User updatedTeam = userService.updateTeam(id, teamDto);

        if (updatedTeam == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "userDataCache", key = "#userId")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        userService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
