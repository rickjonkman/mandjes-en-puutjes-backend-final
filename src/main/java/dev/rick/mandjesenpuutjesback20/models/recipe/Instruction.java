package dev.rick.mandjesenpuutjesback20.models.recipe;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Instruction {

    private int step;
    private String instruction;
}
