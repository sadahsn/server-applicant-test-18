package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverCarDTO
{
    @JsonIgnore
    private Long id;
    
    private Long driverId;
    
    private Long carId;

}
