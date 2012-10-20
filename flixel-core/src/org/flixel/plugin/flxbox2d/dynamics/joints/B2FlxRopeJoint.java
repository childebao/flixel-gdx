package org.flixel.plugin.flxbox2d.dynamics.joints;

import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

/**
 * A rope joint enforces a maximum distance between two points on two bodies. 
 * It has no other effect. Warning: if you attempt to change the maximum length 
 * during the simulation you will get some non-physical behavior. A model that 
 * would allow you to dynamically modify the length would have some sponginess, 
 * so I chose not to implement it that way. See b2DistanceJoint if you want to 
 * dynamically control length. 
 * 
 * @author Ka Wing Chin
 */
public class B2FlxRopeJoint extends B2FlxJoint
{
	
	/**
	 * Create a rope joint.
	 * @param spriteA	The first body.
	 * @param spriteB	The second body.
	 * @param jointDef	The joint definition.
	 */
	public B2FlxRopeJoint(B2FlxShape spriteA, B2FlxShape spriteB, RopeJointDef jointDef)
	{
		super(spriteA, spriteB, jointDef);
	}
	
	/**
	 * Create a rope joint.
	 * @param spriteA	The first body.
	 * @param spriteB	The second body.
	 * @param jointDef	The joint definition.
	 */
	public B2FlxRopeJoint(B2FlxShape spriteA, B2FlxShape spriteB)
	{
		this(spriteA, spriteB, null);
	}
	
	/**
	 * Create the jointDef.
	 */
	@Override
	protected void setupJointDef()
	{
		if(jointDef == null)
			jointDef = new RopeJointDef();
		jointDef.bodyA = bodyA;
		jointDef.bodyB = bodyB;
	}
	
	/**
	 * Set the defaults. The line will be drawn.
	 */
	@Override
	protected void setDefaults()
	{
		((RopeJointDef)jointDef).localAnchorA.set(new Vector2());
		((RopeJointDef)jointDef).localAnchorB.set(new Vector2());
		showLine = true;
	}
	
	/**
	 * Create the joint.
	 * @return	This joint. Handy for chaining stuff together.
	 */
	@Override
	public B2FlxJoint create()
	{
		joint = B2FlxB.world.createJoint(jointDef);
		return this;
	}
	
	/**
	 * The max length between the anchor points.
	 */
	public B2FlxRopeJoint setMaxLength(float maxLength)
	{
		((RopeJointDef)jointDef).maxLength = maxLength;
		return this;
	}
	
	@Override
	public B2FlxJoint setAnchorA(Vector2 anchorA)
	{		
		((RopeJointDef)jointDef).localAnchorA.set(anchorA);
		return this;
	}
	
	@Override
	public B2FlxJoint setAnchorB(Vector2 anchorB)
	{
		((RopeJointDef)jointDef).localAnchorB.set(anchorB);
		return this;
	}

}
