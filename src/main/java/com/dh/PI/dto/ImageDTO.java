package com.dh.PI.dto;

import com.dh.PI.model.Image;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ImageDTO {

    private Long id;
    private String title;
    private String url;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.title = image.getTitle();
        this.url = image.getUrl();
    }
}
