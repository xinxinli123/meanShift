package com.patent.cloud.module.patent.core.util;


import com.patent.cloud.module.patent.core.util.meanShift.MeanShift;
import com.patent.cloud.module.patent.core.util.meanShift.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeanShiftClustering {
    public static void main(String[] args) {
        double[][] result = {
                {1.0, 1.0},
                {1.1, 1.1},
                {0.9, 1.0},
                {5.0, 5.0},
                {5.1, 5.1},
                {10.0, 10.0}
                // ... 你的实际数据
        };
        // 将向量数组转换为 Point 列表
        List<Point> data = new ArrayList<>(result.length);
        for (double[] vec : result) {
            data.add(new Point(vec));
        }
        // 设置带宽并执行 Mean Shift 聚类
        double bandwidth = 1.5;
        MeanShift ms = new MeanShift(bandwidth);
        List<Point> centers = ms.fit(data);
        // 输出聚类中心
        System.out.println("Cluster Centers:");
        centers.forEach(System.out::println);
    }
}
