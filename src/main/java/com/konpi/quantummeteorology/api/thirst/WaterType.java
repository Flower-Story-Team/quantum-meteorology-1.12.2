package com.konpi.quantummeteorology.api.thirst;

public enum WaterType implements IDrink
{
    NORMAL("Water", 3, 0.1F, 0.75F), 
    PURIFIED("Purified Water", 6, 0.5F, 0.0F),
    RAIN("Rain", 1, 0.05F, 0.0F);
    
    private String description;
    private int thirst;
    private float hydration;
    private float poisonChance;
    
    private WaterType(String description, int thirst, float hydration, float poisonChance)
    {
        this.description = description;
        this.thirst = thirst;
        this.hydration = hydration;
        this.poisonChance = poisonChance;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    @Override
    public int getThirst()
    {
        return this.thirst;
    }
    
    @Override
    public float getHydration()
    {
        return this.hydration;
    }
    
    @Override
    public float getPoisonChance()
    {
        return this.poisonChance;
    }
}
