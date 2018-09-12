package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@Table(name = "driver_car",
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {"driver_id", "car_id"})
    })

public class DriverCarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "car_id", nullable = false)
    private Long carId;
}
