package com.jja.data;

/**
 * Created by Julius on 25.07.2016.
 */
public class HealthModule extends Module{

    public HealthModule(int id, int hp)
    {
        super(id);
        this.setHealthPoints(hp);
    }
}
