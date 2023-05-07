package uz.market.uzum.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderDTO {


    @NotNull(message = "Product id must not be null")
    private Long productId;

    @Min(value = 1, message = "Count must be greater than 0")
    private Short count;

}
