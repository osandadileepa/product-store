package com.osanda.test.productStore;

import com.osanda.test.productStore.dto.PriceCalculateDto;
import com.osanda.test.productStore.service.PriceCalculateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class PriceCalculateServiceTests {

    @Autowired
    private PriceCalculateService priceCalculateService;

    int units = 5;
    Long productId = 2L;

    @Test
    public void getPriceSingleProductTest() throws Exception {

        PriceCalculateDto priceCalculate = this.priceCalculateService.calculateProductPriceFromUnits(this.productId, this.units);

        assertThat(priceCalculate.getCartonAmount()).isEqualTo(1);
        assertThat(priceCalculate.getCostOfCartons()).isEqualTo(825.0);
        assertThat(priceCalculate.getTotalCost()).isEqualTo(825.0);

    }// getPriceSingleProductTest()

    @Test
    public void getPriceListTest() throws Exception {

        int numberOfPrices = 50;

        List<PriceCalculateDto> priceList = this.priceCalculateService.getPriceListForRange(this.productId, numberOfPrices);

        assertThat(priceList.size()).isEqualTo(numberOfPrices);

        assertThat(priceList.get(0).getSingleUnitsAmount() ).isEqualTo(1);
        assertThat(priceList.get(0).getCostOfSingleUnits() ).isEqualTo(214.5);
        assertThat(priceList.get(0).getTotalCost()).isEqualTo(214.5);


        assertThat(priceList.get(priceList.size() - 1).getCartonAmount()).isEqualTo(10);
        assertThat(priceList.get(priceList.size() - 1).getCostOfCartons()).isEqualTo(7425.0);
        assertThat(priceList.get(priceList.size() - 1)).isEqualTo(7425.0);

    }// getPriceListTest()

}// PriceCalculateServiceTests {}
