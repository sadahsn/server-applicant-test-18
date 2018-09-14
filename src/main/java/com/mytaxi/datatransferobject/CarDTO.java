package com.mytaxi.datatransferobject;



import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class CarDTO
{
    @JsonIgnore
    private Long id;

    private Float rating;

    private String engineType;

    private Integer seatCount;

    private Boolean convertible;

    @NotNull(message = "licensePlate can not be null!")
    private String licensePlate;

    private String manufacturer;
    
    private String model;
    
    private String color;
    
}
