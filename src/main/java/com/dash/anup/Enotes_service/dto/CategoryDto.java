package com.dash.anup.Enotes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
}
