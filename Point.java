package com.patent.cloud.module.patent.core.util.meanShift;

public class Point {
    public final double[] coords;

    public Point(double... coords) {
        this.coords = coords.clone();
    }

    public double distanceTo(Point other) {
        double sum = 0;
        for (int i = 0; i < coords.length; i++) {
            double d = coords[i] - other.coords[i];
            sum += d * d;
        }
        return Math.sqrt(sum);
    }

    public Point add(Point other) {
        double[] result = new double[coords.length];
        for (int i = 0; i < coords.length; i++) {
            result[i] = coords[i] + other.coords[i];
        }
        return new Point(result);
    }

    public Point divide(double val) {
        double[] result = new double[coords.length];
        for (int i = 0; i < coords.length; i++) {
            result[i] = coords[i] / val;
        }
        return new Point(result);
    }

    public boolean closeTo(Point other, double epsilon) {
        return this.distanceTo(other) < epsilon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < coords.length; i++) {
            sb.append(coords[i]);
            if (i < coords.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
