package com.github.annasajkh;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ant extends GameObject
{
	float radius = 3;
	Vector2 velocity;
	float friction = 0.99f;
	float speed;
	float maxVelocity = 1000;
	
	public Ant(Vector2 position)
	{
		super(position);
		speed = MathUtils.random(100,500);
		velocity = new Vector2();
	}

	@Override
	public void update()
	{
		if(position.x < -radius)
		{
			position.x = Gdx.graphics.getWidth() + radius;
		}
		else if(position.x > Gdx.graphics.getWidth() + radius)
		{
			position.x = -radius;
		}
		else if(position.y < -radius)
		{
			position.y = Gdx.graphics.getHeight() + radius;
		}
		else if(position.y > Gdx.graphics.getHeight() + radius)
		{
			position.y = -radius;
		}
		
		Vector2 dirToMouse = new Vector2(Game.mousePos3D.x,Game.mousePos3D.y).sub(position).nor();
		
		velocity.add(dirToMouse.scl(speed));
		
		
		velocity.x *= friction;
		velocity.y *= friction;
		
		velocity.clamp(-maxVelocity,maxVelocity);
		
		float delta = Gdx.graphics.getDeltaTime();
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.circle(position.x,position.y,radius);
	}

}
