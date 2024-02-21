package dev.rick.mandjesenpuutjesback20.converters;

import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeBasicDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.TagDTO;
import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
import dev.rick.mandjesenpuutjesback20.models.recipe.Tag;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeConverter {

    public Recipe convertBasicInputToRecipe(RecipeBasicDTO inputDTO, User creator) {

        Recipe newRecipe = new Recipe();
        newRecipe.setName(inputDTO.getName());
        newRecipe.setServings(inputDTO.getServings());
        newRecipe.setPrepTime(inputDTO.getPrepTime());
        newRecipe.setTags(convertToTagList(inputDTO.getTags()));
        newRecipe.setCreatedByUser(creator);
        return newRecipe;
    }

    public RecipeBasicDTO convertToBasicDTO(Recipe recipe) {
        RecipeBasicDTO dto = new RecipeBasicDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setServings(recipe.getServings());
        dto.setPrepTime(recipe.getPrepTime());
        dto.setCreator(recipe.getCreatedByUser().getUsername());
        dto.setTags(convertToTagDTOList(recipe.getTags()));
        return dto;
    }

    public List<Tag> convertToTagList(List<TagDTO> tagDTOList) {
        List<Tag> tags = new ArrayList<>();

        for (TagDTO tagDTO : tagDTOList) {
            Tag convertedTag = convertDTOToTag(tagDTO);
            tags.add(convertedTag);
        }

        return tags;
    }

    public List<TagDTO> convertToTagDTOList(List<Tag> tags) {
        List<TagDTO> tagDTOList = new ArrayList<>();
        for (Tag tag : tags) {
            TagDTO tagDTO = convertToTagDTO(tag);
            tagDTOList.add(tagDTO);
        }
        return tagDTOList;
    }

    public Tag convertDTOToTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagDTO.getTagName());
        return tag;
    }

    public TagDTO convertToTagDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTagName(tag.getTagName());
        return tagDTO;
    }
}
