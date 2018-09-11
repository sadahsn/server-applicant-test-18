package com.mytaxi.datatransferobject;



import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    private Long id;

    private Float rating;

    private String engineType;

    private Integer seatCount;

    private Boolean convertible;

    private String licensePlate;

    private String manufacturer;
    
    private String model;
    
    private String color;
    
}
