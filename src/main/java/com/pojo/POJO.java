package com.pojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author psych0
 */

/**
 *
 * @author psych0
 */
public class POJO {
public class Armory{

private String name;

private String realm;

private String race;

private String gender;

private Items items;
    


public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}


public String getRealm() {
return realm;
}

public void setRealm(String realm) {
this.realm = realm;
}

public String getRace() {
return race;
}

public void setRace(String race) {
this.race = race;
}


public String getGen() {
return gender;
}

public void setGen(String gender) {
this.gender = gender;
}

public String getAverageItemLevel() {
return items.getAverageItemLevel();
}

@Override
public String toString() {
//return "Armory [name=" + name + ", realm=" + realm
//+ ", race=" + race + ", gender=" + gender + ", averageItemLevel="
//+ getAverageItemLevel() + "]";

return getAverageItemLevel();
}
}}
