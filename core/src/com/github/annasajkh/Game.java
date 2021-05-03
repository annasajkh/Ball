package com.github.annasajkh;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Game extends ApplicationAdapter
{
	ShapeRenderer shapeRenderer;
	Ant[] ants;
	OrthographicCamera camera;
	public static Vector3 mousePos3D;
	public static Vector2 mousePos;
	
	@Override
	public void create()
	{
		shapeRenderer = new ShapeRenderer();
		ants = new Ant[10_000];
		
		for(int i = 0; i < ants.length; i++)
		{
			ants[i] = new Ant(new Vector2(MathUtils.random(Gdx.graphics.getWidth()),MathUtils.random(Gdx.graphics.getHeight())));
		}
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		camera.update();
		mousePos3D = new Vector3();
		shapeRenderer.setColor(Color.GREEN);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		shapeRenderer.setProjectionMatrix(camera.combined);
	}
	
	public void update()
	{
		mousePos3D.x = Gdx.input.getX();
		mousePos3D.y = Gdx.input.getY();
		mousePos3D = camera.unproject(mousePos3D);
		
		for(Ant ant : ants)
		{
			ant.update();
		}
	}

	@Override
	public void render()
	{
		update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);
		for(Ant ant : ants)
		{
			ant.draw(shapeRenderer);
		}
		shapeRenderer.end();
		
	}

	@Override
	public void dispose()
	{
		shapeRenderer.dispose();
	}
}
