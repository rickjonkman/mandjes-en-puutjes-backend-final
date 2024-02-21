package dev.rick.mandjesenpuutjesback20.converters;

import dev.rick.mandjesenpuutjesback20.dto.recipes.IngredientDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.IngredientMeasuredDTO;
import dev.rick.mandjesenpuutjesback20.models.recipe.Ingredient;
import dev.rick.mandjesenpuutjesback20.models.recipe.IngredientMeasured;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientConverter {



    public List<IngredientMeasured> convertToMeasuredList(List<IngredientMeasuredDTO> dtoList) {
        List<IngredientMeasured> ingredientMeasuredList = new ArrayList<>();
        for (IngredientMeasuredDTO dto : dtoList) {
            IngredientMeasured measured = convertToMeasured(dto);
            ingredientMeasuredList.add(measured);
        }
        return ingredientMeasuredList;
    }

    public IngredientMeasured convertToMeasured(IngredientMeasuredDTO dto) {
        IngredientMeasured measured = new IngredientMeasured();
        measured.setAmount(dto.getAmount());
        measured.setUnit(dto.getUnit());
        measured.setName(dto.getName());
        return measured;
    }

    public List<Ingredient> convertMeasuredListToIngredientList(List<IngredientMeasured> measuredList) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (IngredientMeasured measured : measuredList) {
            Ingredient ingredient = convertMeasuredToIngredient(measured);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    public Ingredient convertMeasuredToIngredient(IngredientMeasured measured) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(measured.getName());
        return ingredient;
    }

    public List<IngredientMeasuredDTO> convertMeasuredListToDTOList(List<IngredientMeasured> measuredList) {
        List<IngredientMeasuredDTO> measuredDTOList = new ArrayList<>();

        for (IngredientMeasured measured : measuredList) {
            IngredientMeasuredDTO dto = convertMeasuredToDTO(measured);
            measuredDTOList.add(dto);
        }

        return measuredDTOList;
    }

    public IngredientMeasuredDTO convertMeasuredToDTO(IngredientMeasured measured) {
        IngredientMeasuredDTO dto = new IngredientMeasuredDTO();
        dto.setAmount(measured.getAmount());
        dto.setUnit(measured.getUnit());
        dto.setName(measured.getName());
        return dto;
    }

    public List<IngredientDTO> convertIngredientListToDTO(List<Ingredient> ingredients) {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            IngredientDTO dto = convertToIngredientDTO(ingredient);
            ingredientDTOList.add(dto);
        }

        return ingredientDTOList;
    }

    public IngredientDTO convertToIngredientDTO(Ingredient ingredient) {
        IngredientDTO dto = new IngredientDTO();
        dto.setName(ingredient.getIngredientName());
        return dto;
    }

}
