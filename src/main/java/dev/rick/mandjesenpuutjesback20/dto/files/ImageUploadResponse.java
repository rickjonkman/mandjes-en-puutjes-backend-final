package dev.rick.mandjesenpuutjesback20.dto.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageUploadResponse {

    String fileName;
    String contentType;
    String url;



}
