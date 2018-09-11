package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.EngineType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licenceplate", columnNames = {"license_plate"}))
public class CarDO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "license_plate", nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @Column(name ="seat_count",nullable = false)
    @NotNull(message = "Seat Count can not be null!")
    private int seatCount;

    @Column(nullable = false)
    private float rating;

    @Enumerated(EnumType.STRING)
    @Column(name="engine_type",nullable = false)
    private EngineType engineType;

    @Column(nullable = false)
    @NotNull(message = "Manufacturer can not be null!")
    private String manufacturer;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column
    private boolean isConvertible;

    @Column(nullable = false)
    private String color;
    
    @Column
    private String model;


    public CarDO(
        @NotNull(message = "License plate can not be null!") String licensePlate,
        @NotNull(message = "Seat Count can not be null!") int seatCount, float rating, EngineType engineType,
        @NotNull(message = "Manufacturer can not be null!") String manufacturer, String color, boolean isConvertible, String model)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
        this.color = color;
        this.deleted = false;
        this.isConvertible = isConvertible;
        this.model = model;
    }
    

}
