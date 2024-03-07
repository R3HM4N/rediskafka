package uploadimage.uploadimage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import uploadimage.uploadimage.dto.TeamGetByIdDto;
import uploadimage.uploadimage.dto.TeamResponse;
import uploadimage.uploadimage.entity.User;
import uploadimage.uploadimage.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RedisTemplate<String,User> redisTemplate;

    public Iterable<User> getAllTeams() {
        return userRepository.findAll();
    }

    public TeamGetByIdDto getById(String id) {
        User team = userRepository.findById(id).orElse(null);



        TeamGetByIdDto dto = TeamGetByIdDto.builder()
                .id(team.getId())
                .name(team.getTeamName())

                .build();
        return dto;
    }

    public User createTeam(TeamResponse teamDto) {


        User team = new User();
        team.setTeamName(teamDto.getTeamName());
        return userRepository.save(team);
    }

    public User updateTeam(String id, TeamResponse teamDto) {
        User existingTeam = userRepository.findById(id).orElse(null);

        if (existingTeam == null) {
            return null;
        }

        existingTeam.setTeamName(teamDto.getTeamName());
        redisTemplate.opsForValue().set(id,existingTeam);
        return userRepository.save(existingTeam);
    }

    public void deleteTeam(String id) {
        userRepository.deleteById(id);
        redisTemplate.delete(id);

    }
}






