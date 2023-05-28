package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.lms.Chapter;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LecturesDto {
    private Chapter chapter;

}
