package game.entities;

import org.joml.Vector2fc;
import org.joml.Vector3fc;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by kilian on 09.02.17.
 */
public class Vector3f extends org.joml.Vector3f {

    public Vector3f() {
    }

    public Vector3f(float d) {
        super(d);
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(Vector3fc v) {
        super(v);
    }

    public Vector3f(Vector2fc v, float z) {
        super(v, z);
    }

    public Vector3f(ByteBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, ByteBuffer buffer) {
        super(index, buffer);
    }

    public Vector3f(FloatBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, FloatBuffer buffer) {
        super(index, buffer);
    }

    public boolean closeTo(org.joml.Vector3f vector) {
        return new Vector3f(this.x, vector.y, this.z).distance(vector) < .01f;
    }

    public boolean near (org.joml.Vector3f vector) {
        return new Vector3f(this.x, this.y, this.z).distance(vector) < 2;
    }
}
