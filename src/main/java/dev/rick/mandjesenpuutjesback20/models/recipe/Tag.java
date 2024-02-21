package dev.rick.mandjesenpuutjesback20.models.recipe;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Tag {

    private String tagName;

}
