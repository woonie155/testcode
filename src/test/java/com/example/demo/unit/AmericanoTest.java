package com.example.demo.unit;

import com.example.demo.unit.beverage.Americano;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AmericanoTest {

    @Test
    void getName(){
        Americano americano = new Americano();
        Assertions.assertThat(americano.getName()).isEqualTo("아메리카노");
    }


    @Test
    void getPrice(){
        Americano americano = new Americano();
        Assertions.assertThat(americano.getPrice()).isEqualTo(4000);
    }
}
