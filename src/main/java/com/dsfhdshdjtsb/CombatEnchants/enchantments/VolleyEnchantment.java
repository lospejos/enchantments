package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class VolleyEnchantment extends Enchantment {
    public VolleyEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target.distanceTo(user) < 4)
            return;
        World world = user.world;
        ArrowEntity[] arrowArray = new ArrowEntity[9];
        int random = 2;
        int height = 40 + (5 * level);
        if(target instanceof LivingEntity)
        {
            for(int i = 0; i < arrowArray.length; i++) {
                arrowArray[i] = EntityType.ARROW.create(world);
            }

            assert arrowArray[0] != null;
            arrowArray[0].setPos(target.getX(), target.getY() + height + (5 * Math.random()), target.getZ());
            arrowArray[1].setPos(target.getX() + 1 + random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() + random * Math.random() - 0.6f);
            arrowArray[2].setPos(target.getX() - 1 - random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() + random * Math.random() - 0.6f);
            arrowArray[3].setPos(target.getX() + 1 + random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() + 1 + random * Math.random());
            arrowArray[4].setPos(target.getX() - 1 - random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() + 1 + random * Math.random());
            arrowArray[5].setPos(target.getX() + 1 + random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() - 1 - random * Math.random());
            arrowArray[6].setPos(target.getX() - 1 - random * Math.random(), target.getY() + height + (5 * Math.random()), target.getZ() - 1 - random * Math.random());
            arrowArray[7].setPos(target.getX() + random * Math.random() - 0.6f, target.getY() + height + (5 * Math.random()), target.getZ() + 1 + random * Math.random());
            arrowArray[8].setPos(target.getX() + random * Math.random() - 0.6f, target.getY() + height + (5 * Math.random()), target.getZ() - 1 - random * Math.random());


            for (ArrowEntity arrowEntity : arrowArray) {
                //arrowArray[i].setVelocity(arrowArray[i].getX(), arrowArray[i].getY(), arrowArray[i].getZ(), 10.0f, 1);
                arrowEntity.setVelocity(0, -2 + (-1 * level), 0);
                world.spawnEntity(arrowEntity);
            }

        }
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.ZAP))
            return false;
        return super.canAccept(other);
    }
}
