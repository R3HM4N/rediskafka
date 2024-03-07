package uploadimage.uploadimage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class TeamGetByIdDto {
    private String id;
    private String name;}
