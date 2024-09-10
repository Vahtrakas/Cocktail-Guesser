package com.ridango.game.model;


public class Cocktail {
    
    private String name;

    private String instructions;

    private String category;

    private String glass;

    public Cocktail(String name, String instructions, String category, String glass) {
        this.name = name;
        this.instructions = instructions;
        this.category = category;
        this.glass = glass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }
    
}
