package ml.heartfulcpvp.hfcpvp.math;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Vector;

public class Vec3d {
    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
    }

    public Location toLocation(World world) {
        return new Location(world, x, y, z);
    }

    public void add(Vec3d vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;
    }

    public static Vec3d addVector(Vec3d a, Vec3d b) {
        a.add(b);
        return a;
    }

    public static double innerProduct(Vec3d a, Vec3d b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vec3d crossProduct(Vec3d a, Vec3d b) {
        return new Vec3d(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }
}
