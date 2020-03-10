package com.example.magicball.model;

import java.util.ArrayList;

public class MagicObj
{
    private String name;
    protected ArrayList<String> good= new ArrayList<>(); // holds 10
    protected ArrayList<String> neutral= new ArrayList<>(); //holds 5
    protected ArrayList<String>  bad= new ArrayList<>(); //holds 5

    public void MagicObj(String name)
    {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String createRandom(int roll)
    {
        switch (roll)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:{
                return good.get(roll-1);
            }
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:{
               return neutral.get(roll-11);
            }
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:{
                return bad.get(roll-16);
            }
        }
        return null;
    }

    public String toString()
    {
        return "You are using: "+name;
    }






}

