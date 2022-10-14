package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView g = new GLSurfaceView(this);
        g.setRenderer(new RendererSolarSystem(this));
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(g);
    }
}

