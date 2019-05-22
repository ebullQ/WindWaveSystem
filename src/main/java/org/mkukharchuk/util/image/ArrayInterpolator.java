package org.mkukharchuk.util.image;

import org.springframework.stereotype.Component;

@Component
public class ArrayInterpolator {

    public double[][] getMap(double[][] createdMap){
        double[][] map = createdMap;
        for (int i = 0; i < 3  ; i++) {
            map = getInterpolationMap(map);
        }
        return map;
    }

    private double[][] getInterpolationMap(double[][] array){
        double[][] doubles = new double[array.length*2-1][array[0].length*2-1];
        int k = 0;
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[0].length; j++) {
                doubles[k][m++] = array[i][j];
                if(m<doubles[i].length) {
                    doubles[k][m++] = (array[i][j] + array[i][j + 1]) / 2;
                }
            }
            k = k + 2;
            m=0;
        }
        for (int i = 1; i < doubles.length; i=i+2) {
            for (int j = 0; j < doubles[i].length; j++) {
                doubles[i][j] = (doubles[i-1][j]+doubles[i+1][j])/2;
            }
        }
        return doubles;
    }
}
