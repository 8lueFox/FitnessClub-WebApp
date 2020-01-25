package com.kacper.fitnessclub.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carnet_purchase_history")
public class CarnetPurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carnet_id", nullable = false)
    Carnet carnet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    boolean paid;

    public CarnetPurchaseHistory(Carnet carnet, User user){
        this.carnet = carnet;
        this.user = user;
        paid = false;
    }
}
