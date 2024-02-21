package dev.rick.mandjesenpuutjesback20.models.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "products", nullable = false)
    private String productName;


}
