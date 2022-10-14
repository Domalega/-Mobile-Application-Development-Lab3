package com.example.lab3;

import android.opengl.GLSurfaceView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


class Sphere implements GLSurfaceView.Renderer {
    private FloatBuffer mVertexBuffer;
    public FloatBuffer textureBuffer;
    public int n = 0;

    public Sphere(float R) {
        int angleX =  20, angleY = 5;
        float PIf = (float)(Math.PI / 180.0f);

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(5000 * 3 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        byteBuf = ByteBuffer.allocateDirect(5000 * 2 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        for (int theta = -90; theta <= 90 - angleX; theta += angleX) {
            for (int phi = 0; phi <= 360; phi += angleY){
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.cos(phi * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.sin(phi * PIf)) * R);
                mVertexBuffer.put((float) (Math.sin(theta * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.cos(phi * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.sin(phi * PIf)) * R);
                mVertexBuffer.put((float) (Math.sin((theta + angleX) * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.cos((phi+angleY) * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.sin((phi+angleY) * PIf)) * R);
                mVertexBuffer.put((float) (Math.sin((theta + angleX) * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.cos((phi + angleY) * PIf)) * R);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.sin((phi + angleY) * PIf)) * R);
                mVertexBuffer.put((float) (Math.sin(theta * PIf)) * R);
                n += 4;

                textureBuffer.put((float) (phi / 360.0f));
                textureBuffer.put((float) ((90 + theta) / 180.0f));
                textureBuffer.put((float) (phi / 360.0f));
                textureBuffer.put((float) ((90 + theta + angleX) / 180.0f));
                textureBuffer.put((float) ((phi + angleY) / 360.0f));
                textureBuffer.put((float) ((90 + theta + angleX) / 180.0f));
                textureBuffer.put((float) ((phi+ angleY) / 360.0f));
                textureBuffer.put((float) ((90 + theta) / 180.0f));
            }
        }
        mVertexBuffer.position(0);
        textureBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {}

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {}

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        for (int i = 0; i < n; i += 4)
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, i,4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_BLEND);
    }
}
