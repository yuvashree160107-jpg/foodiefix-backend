package com.example.FoodieFix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

   @NotNull
   private Double basePrice;

   @Enumerated(EnumType.STRING)
   MenuCategory category ;

   public enum MenuCategory{
    APPETIZER, MAIN_COURSE, DESSERT, BEVERAGE, SIDES;
   }

   @NotNull
   private Boolean isAvailable ;

   public MenuItem() {
}

   public MenuItem(Long id, @NotBlank String name, @NotNull Double basePrice, MenuCategory category,
        @NotNull Boolean isAvailable) {
    this.id = id;
    this.name = name;
    this.basePrice = basePrice;
    this.category = category;
    this.isAvailable = isAvailable;
   }

   public Long getId() {
    return id;
   }

   public void setId(Long id) {
    this.id = id;
   }

   public String getName() {
    return name;
   }

   public void setName(String name) {
    this.name = name;
   }

   public Double getBasePrice() {
    return basePrice;
   }

   public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
   }

   public MenuCategory getCategory() {
    return category;
   }

   public void setCategory(MenuCategory category) {
    this.category = category;
   }

   public Boolean getIsAvailable() {
    return isAvailable;
   }

   public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
   }



}