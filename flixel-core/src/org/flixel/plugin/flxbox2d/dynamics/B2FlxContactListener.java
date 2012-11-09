package org.flixel.plugin.flxbox2d.dynamics;

import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.ObjectMap;

import flash.events.EventDispatcher;

/**
 * Collision event for FlxBox2D collision handling.
 * 
 * @author Ka Wing Chin
 */
public class B2FlxContactListener extends EventDispatcher implements ContactListener
{	
	private B2FlxContactEvent _event;

	/**
	 * Constructor
	 */
	public B2FlxContactListener()
	{
		_event = new B2FlxContactEvent(null);
	}

	/** 
	 * Called when two fixtures begin to touch.
	 */
	@Override
	public void beginContact(Contact contact)
	{
		dispatch(contact, B2FlxContactEvent.BEGIN);
	}

	/** 
	 * Called when two fixtures cease to touch. 
	 */
	@Override
	public void endContact(Contact contact)
	{
		dispatch(contact, B2FlxContactEvent.END);
	}

	/**
	 * This is called after a contact is updated. This allows you to inspect a contact before it goes to the solver. If you are
	 * careful, you can modify the contact manifold (e.g. disable contact). A copy of the old manifold is provided so that you can
	 * detect changes. Note: this is called only for awake bodies. Note: this is called even when the number of contact points is
	 * zero. Note: this is not called for sensors. Note: if you set the number of contact points to zero, you will not get an
	 * EndContact callback. However, you may get a BeginContact callback the next step.
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		dispatch(contact, B2FlxContactEvent.PRESOLVE);
	}

	/**
	 * Internal: This lets you inspect a contact after the solver is finished. This is useful for inspecting impulses. Note: the contact
	 * manifold does not include time of impact impulses, which can be arbitrarily large if the sub-step is small. Hence the
	 * impulse is provided explicitly in a separate data structure. Note: this is only called for contacts that are touching,
	 * solid, and awake.
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		dispatch(contact, B2FlxContactEvent.POSTSOLVE);
	}
	
	/**
	 * Dispatch the event.
	 * @param contact	The current contact between two shapes.
	 * @param type		The type of event that needs to be dispatched.
	 */
	@SuppressWarnings({"unchecked"})
	public void dispatch(Contact contact, String type)
	{
		_event.type = type;
		_event.contact = contact;
		_event.sprite1 = (B2FlxShape) ((ObjectMap<String, Object>) contact.getFixtureA().getBody().getUserData()).get("shape");
		_event.sprite2 = (B2FlxShape) ((ObjectMap<String, Object>) contact.getFixtureB().getBody().getUserData()).get("shape");
		dispatchEvent(_event);
	}

	/**
	 * Clean up the memory.
	 */
	public void destroy()
	{
		_event = null;
	}
}

