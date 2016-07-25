package com.jja.data;

/**
 * Created by Julius on 25.07.2016.
 */
public class DamageModule extends Module {
    private int damage, damagetype;

    public DamageModule(int id, int dmg, int dmgtype)
    {
        super(id);
        this.damage = dmg;
        this.damagetype = dmgtype;
    }
}
