package com.pojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *JSON String parser for inner json string
 * @author psych0
 */
public class Items {

private String averageItemLevel;




public String getAverageItemLevel() {
return averageItemLevel;
}

public void setAverageItemLevel(String averageItemLevel) {
this.averageItemLevel = averageItemLevel;
}
@Override
public String toString() {
return "Armory [averageItemLevel=" + averageItemLevel + "]";
}
}
