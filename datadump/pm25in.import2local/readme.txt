1. pm2.5 数据
接口列举：
1.12、获取所有城市的空气质量详细数据

地址 	http://www.pm25.in/api/querys/all_cities.json
方法 	GET
参数 	* 无
返回 	一个数组，里面包含目前支持的190个城市所有监测点的详细信息，一共946项，其中每项的信息有 * aqi * area * co * co_24h * no2 * no2_24h * o3 * o3_24h * o3_8h * o3_8h_24h * pm10 * pm10_24h * pm2_5 * pm2_5_24h * position_name * primary_pollutant * quality * so2 * so2_24h * station_code * time_point

1.13、获取全部城市的空气质量指数(AQI)排行榜

地址 	http://www.pm25.in/api/querys/aqi_ranking.json
方法 	GET
参数 	* 无
返回 	一个数组，里面包含目前支持的114个城市AQI排行榜信息，其中每项的信息有 * aqi * area * co * co_24h * no2 * no2_24h * o3 * o3_24h * o3_8h * o3_8h_24h * pm10 * pm10_24h * pm2_5 * pm2_5_24h * quality * level * so2 * so2_24h * primary_pollutant * time_point 说明primary_pollutant的值可能为[二氧化硫","二氧化氮","颗粒物(PM10)","颗粒物(PM2.5)","一氧化碳","臭氧1小时","臭氧8小时"]中的一项或多项组合，出现多项时，各项之间用逗号","分隔;AQI低于50时，没有值，为"".

成功返回示例：
[
    {
        "aqi": 24,
        "area": "昆明",
        "co": 1.173,
        "co_24h": 1.362,
        "no2": 27,
        "no2_24h": 32,
        "o3": 16,
        "o3_24h": 22,
        "o3_8h": 7,
        "o3_8h_24h": 18,
        "pm10": 9,
        "pm10_24h": 24,
        "pm2_5": 11,
        "pm2_5_24h": 15,
        "quality": "优",
        "level": "一级",
        "so2": 6,
        "so2_24h": 8,
        "primary_pollutant": "",
        "time_point": "2013-10-21T14:00:00Z"
    },
    ......
    {
        "aqi": 51,
        "area": "福州",
        "co": 0.562,
        "co_24h": 0.544,
        "no2": 20,
        "no2_24h": 26,
        "o3": 92,
        "o3_24h": 93,
        "o3_8h": 64,
        "o3_8h_24h": 67,
        "pm10": 59,
        "pm10_24h": 50,
        "pm2_5": 37,
        "pm2_5_24h": 30,
        "quality": "良",
        "level": "二级",
        "so2": 12,
        "so2_24h": 10,
        "primary_pollutant": "颗粒物(PM10)",
        "time_point": "2013-10-21T14:00:00Z"
    },
    ......
    {
        "aqi": 58,
        "area": "泉州",
        "co": 0.57,
        "co_24h": 0.47,
        "no2": 16,
        "no2_24h": 17,
        "o3": 149,
        "o3_24h": 149,
        "o3_8h": 108,
        "o3_8h_24h": 108,
        "pm10": 92,
        "pm10_24h": 64,
        "pm2_5": 39,
        "pm2_5_24h": 29,
        "quality": "良",
        "level": "二级",
        "so2": 12,
        "so2_24h": 8,
        "primary_pollutant": "臭氧8小时,颗粒物(PM10)",
        "time_point": "2013-10-21T14:00:00Z"
    },
    ......
    {
        "aqi": 500,
        "area": "哈尔滨",
        "co": 4.053,
        "co_24h": 3.942,
        "no2": 166,
        "no2_24h": 165,
        "o3": 13,
        "o3_24h": 44,
        "o3_8h": 9,
        "o3_8h_24h": 35,
        "pm10": 910,
        "pm10_24h": 860,
        "pm2_5": 866,
        "pm2_5_24h": 719,
        "quality": "严重污染",
        "level": "六级",
        "so2": 61,
        "so2_24h": 47,
        "primary_pollutant": "颗粒物(PM2.5),颗粒物(PM10)",
        "time_point": "2013-10-21T14:00:00Z"
    }
]


数据格式说明：
PM25.in网站提供的空气质量指数实时数据来源于国家环境保护部，API返回的数据包括了以下内容：

字段 	字段说明
aqi 	空气质量指数(AQI)，即air quality index，是定量描述空气质量状况的无纲量指数
area 	城市名称
position_name 	监测点名称
station_code 	监测点编码
so2 	二氧化硫1小时平均
so2_24h 	二氧化硫24小时滑动平均
no2 	二氧化氮1小时平均
no2_24h 	二氧化氮24小时滑动平均
pm10 	颗粒物（粒径小于等于10μm）1小时平均
pm10_24h 	颗粒物（粒径小于等于10μm）24小时滑动平均
co 	一氧化碳1小时平均
co_24h 	一氧化碳24小时滑动平均
o3 	臭氧1小时平均
o3_24h 	臭氧日最大1小时平均
o3_8h 	臭氧8小时滑动平均
o3_8h_24h 	臭氧日最大8小时滑动平均
pm2_5 	颗粒物（粒径小于等于2.5μm）1小时平均
pm2_5_24h 	颗粒物（粒径小于等于2.5μm）24小时滑动平均
primary_pollutant 	首要污染物
quality 	空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
time_point 	数据发布的时间