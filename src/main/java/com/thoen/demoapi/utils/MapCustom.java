package com.thoen.demoapi.utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class MapCustom<K,V> extends HashMap<K,V>{
    public MapCustom<String,Object> mapCustom = new MapCustom<>();
    public MapCustom<String,Object> addMap(Object value){
        mapCustom.put("status", value);
        mapCustom.put("message", value);
        return mapCustom;
    }



}
