package com.patent.cloud.module.patent.core.util.meanShift;

import java.util.ArrayList;
import java.util.List;

public class MeanShift {
    private final double bandwidth;
    private final double epsilon = 1e-3;

    public MeanShift(double bandwidth) {
        this.bandwidth = bandwidth;
    }

    public List<Point> fit(List<Point> points) {
        List<Point> shifted = new ArrayList<>(points.size());
        for (Point p : points) {
            shifted.add(shiftPoint(p, points));
        }
        // 合并中心
        List<Point> centers = new ArrayList<>();
        for (Point p : shifted) {
            boolean found = false;
            for (Point c : centers) {
                if (p.closeTo(c, bandwidth / 2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                centers.add(p);
            }
        }
        return centers;
    }

    private Point shiftPoint(Point current, List<Point> points) {
        Point prev;
        do {
            prev = current;
            List<Point> inRange = new ArrayList<>();
            for (Point p : points) {
                if (current.distanceTo(p) <= bandwidth) {
                    inRange.add(p);
                }
            }
            // 计算均值
            Point mean = new Point(new double[current.coords.length]);
            for (Point p : inRange) {
                mean = mean.add(p);
            }
            current = mean.divide(inRange.size());
        } while (!current.closeTo(prev, epsilon));
        return current;
    }
}