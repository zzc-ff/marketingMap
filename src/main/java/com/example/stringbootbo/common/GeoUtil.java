package com.example.stringbootbo.common;

public class GeoUtil {
    /**
     * 根据经纬度和距离返回一个矩形范围
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @param distance
     *            距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */
    public static double[] getRectangle(double lng, double lat, long distance) {
        float delta = 111000;
        if (lng != 0 && lat != 0) {
            double lng1 = lng - distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lng2 = lng + distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        } else {
            // TODO ZHCH 等于0时的计算公式
            double lng1 = lng - distance / delta;
            double lng2 = lng + distance / delta;
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[] { lng1, lat1, lng2, lat2 };
        }
    }

    /**
     * 得到两点间的距离 米
     *
     * @param lat1 第一点纬度
     * @param lng1 第一点经度
     * @param lat2 第二点纬度
     * @param lng2 第二点经度
     * @return
     */
    public static double getDistanceOfMeter(double lat1, double lng1,
                                            double lat2, double lng2) {
        double radLat1 = rad(lat1,lat2);
        double radLat2 = rad(lng1,lng2);
        double a = radLat1 + radLat2;
        int s = (int)Math.sqrt(a);
        return s;
    }

    private static double rad(double d,double c) {
        double pow = Math.pow(d - c,2);
        return pow;
    }

    /**
     * 地球半径：6378.137KM
     */
    private static double EARTH_RADIUS = 6378.137;
}
