package dev.rick.mandjesenpuutjesback20.services;


import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
import dev.rick.mandjesenpuutjesback20.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeImageService {

    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final RecipeRepository recipeRepository;

    public RecipeImageService(@Value("${my.upload_location}") String fileStorageLocation, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory.");
        }
    }

    public String storeFile(MultipartFile file, long recipeId) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + File.separator + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isEmpty()) {
            throw new RecordNotFound(recipeId);
        } else {
            Recipe foundRecipe = optionalRecipe.get();
            foundRecipe.setImagePath(fileName);
            recipeRepository.save(foundRecipe);
        }

        return fileName;
    }

    public Resource downloadImage(String fileName) {
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("The file does not exist or is not readable.");
        }
    }


}
