package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.files.ImageUploadResponse;
import dev.rick.mandjesenpuutjesback20.services.RecipeImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/api/recipes")
public class RecipeImageController {

    private final RecipeImageService recipeImageService;

    public RecipeImageController(RecipeImageService recipeImageService) {
        this.recipeImageService = recipeImageService;
    }

    @PostMapping("/auth/{recipeId}/upload-image")
    public ResponseEntity<ImageUploadResponse> recipeImageUpload(@RequestParam("file")MultipartFile file, @PathVariable("recipeId") long recipeId) {

        String fileName = recipeImageService.storeFile(file, recipeId);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        String contentType = file.getContentType();

        return new ResponseEntity<>(new ImageUploadResponse(fileName, contentType, url), HttpStatus.CREATED);
    }

    @GetMapping("/open/download-image/{fileName}")
    public ResponseEntity<Resource> downloadRecipeImage(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = recipeImageService.downloadImage(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

}
