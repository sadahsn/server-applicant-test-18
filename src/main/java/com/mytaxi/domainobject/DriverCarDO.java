package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@Table(name = "driver_car")
public class DriverCarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long carId;
}
