package com.osanda.test.productStore.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PriceCalculateDto implements Serializable {

	private static final long serialVersionUID = 4323050449459231958L;

	private Long productId;

	private Integer singleUnitsAmount;

	private Integer cartonAmount;

	private Double costOfSingleUnits;

	private Double costOfCartons;

	private Double totalCost;

}
