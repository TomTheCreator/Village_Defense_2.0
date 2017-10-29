package me.TomTheDeveloper.Creatures.v1_12_R1;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Random;

import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import net.minecraft.server.v1_12_R1.EntityAgeable;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.Navigation;
import net.minecraft.server.v1_12_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_12_R1.PathfinderGoalInteract;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtTradingPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoalMakeLove;
import net.minecraft.server.v1_12_R1.PathfinderGoalMoveIndoors;
import net.minecraft.server.v1_12_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_12_R1.PathfinderGoalOpenDoor;
import net.minecraft.server.v1_12_R1.PathfinderGoalPlay;
import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_12_R1.PathfinderGoalRestrictOpenDoor;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_12_R1.PathfinderGoalTradeWithPlayer;

/**
 * Created by Tom on 15/08/2014.
 */
public class RidableVillager extends EntityVillager {

    private String[] villagernames = {"Jagger", "Kelsey", "Kelton", "Haylie", "Harlow", "Howard", "Wulffric", "Winfred", "Ashley", "Bailey", "Beckett", "Alfredo", "Alfred", "Adair", "Edgar", "ED", "Eadwig", "Edgaras", "Buckley", "Stanley", "Nuffley", "Mary", "Jeffry", "Rosaly", "Elliot", "Harry", "Sam", "Rosaline", "Tom", "Ivan", "Kevin", "Adam"};


    @SuppressWarnings("rawtypes")
    public RidableVillager(org.bukkit.World world) {
        super(((CraftWorld) world).getHandle());

        LinkedHashSet goalB = (LinkedHashSet) getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();
        LinkedHashSet goalC = (LinkedHashSet) getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();
        LinkedHashSet targetB = (LinkedHashSet) getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        LinkedHashSet targetC = (LinkedHashSet) getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();

        this.setSize(0.6F, 1.8F);
        ((Navigation)getNavigation()).b(true);
        ((Navigation)getNavigation()).a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
       // this.goalSelector.a(1, new PathfinderGoalAvoidTarget(this, new EntityZom(this), 8.0F, 0.6D, 0.6D));
        this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
        this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
        this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
        this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
        this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
        this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
        this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32D));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
        this.setCustomName(villagernames[new Random().nextInt(villagernames.length)]);
        this.setCustomNameVisible(true);
    }

    @SuppressWarnings("rawtypes")
	public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }


  /*  public void e(float f, float f1)
    {
        if (f > 1.0F) {
            makeSound("mob.horse.land", 0.4F, 1.0F);
        }
        int i = MathHelper.f((f * 0.5F - 3.0F) * f1);
        if (i > 0)
        {
            damageEntity(DamageSource.FALL, i);
            if (this.passenger != null) {
                this.passenger.damageEntity(DamageSource.FALL, i);
            }
            Block block = this.world.getType(new BlockPosition(this.locX, this.locY - 0.2D - this.lastYaw, this.locZ)).getBlock();
            if ((block.getMaterial() != Material.AIR) && (!R()))
            {
                StepSound stepsound = block.stepSound;

                this.world.makeSound(this, stepsound.getStepSound(), stepsound.getVolume1() * 0.5F, stepsound.getVolume2() * 0.75F);
            }
        }
    } */





   /* @Override
    public void g(float f, float f1)
    {
        if ((this.passenger != null) && ((this.passenger instanceof EntityLiving)) && (cE()))
        {
            this.lastYaw = (this.yaw = this.passenger.yaw);
            this.pitch = (this.passenger.pitch * 0.5F);
            setYawPitch(this.yaw, this.pitch);
            this.aI = (this.aG = this.yaw);
            f = ((EntityLiving)this.passenger).aX * 0.5F;
            f1 = ((EntityLiving)this.passenger).aY;
            if (f1 <= 0.0F)
            {
                f1 *= 0.25F;
                this.bL = 0;
            }
            if ((this.onGround) && (this.bp == 0.0F) && (cx()) && (!this.bE))
            {
                f = 0.0F;
                f1 = 0.0F;
            }
            if ((this.bp > 0.0F) && (!ct()) && (this.onGround))
            {
                this.motY = (getJumpStrength() * this.bp);
                if (hasEffect(MobEffectList.JUMP)) {
                    this.motY += (getEffect(MobEffectList.JUMP).getAmplifier() + 1) * 0.1F;
                }
                m(true);
                this.ai = true;
                if (f1 > 0.0F)
                {
                    float f2 = MathHelper.sin(this.yaw * 3.141593F / 180.0F);
                    float f3 = MathHelper.cos(this.yaw * 3.141593F / 180.0F);

                    this.motX += -0.4F * f2 * this.bp;
                    this.motZ += 0.4F * f3 * this.bp;
                    makeSound("mob.horse.jump", 0.4F, 1.0F);
                }
                this.bp = 0.0F;
            }
            this.S = 1.0F;
            this.aK = (bH() * 0.1F);
            if (!this.world.isStatic)
            {
                j((float)getAttributeInstance(GenericAttributes.d).getValue());
                super.g(f, f1);
            }
            if (this.onGround)
            {
                this.bp = 0.0F;
                m(false);
            }
            this.ay = this.az;
            double d0 = this.locX - this.lastX;
            double d1 = this.locZ - this.lastZ;
            float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
            if (f4 > 1.0F) {
                f4 = 1.0F;
            }
            this.az += (f4 - this.az) * 0.4F;
            this.aA += this.az;
        }
        else
        {
            this.S = 0.5F;
            this.aK = 0.02F;
            super.g(f, f1);
        }
    }
*/


    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return this.b(entityAgeable);
    }


}
