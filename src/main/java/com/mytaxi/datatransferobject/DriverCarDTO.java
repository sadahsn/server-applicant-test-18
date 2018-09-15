package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DriverCarDTO
{
    @JsonIgnore
    private Long id;
    
    private Long driverId;
    
    private Long carId;

}
