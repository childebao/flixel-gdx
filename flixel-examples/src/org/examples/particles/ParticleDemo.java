package org.examples.particles;

import org.flixel.FlxGame;

public class ParticleDemo extends FlxGame
{
	public ParticleDemo()
	{
		super(480, 320, PlayState.class, 1, 30, 30);
	}
	
	@Override
	public void create()
	{
		Asset.create();
		super.create();
	}
}
