package com.Y24;

public class node {
    private int id;
    private int[] nearbyNode;
    private int sPath;

    public node(int id) {
        this.id = id;
        sPath=0;
    }

    public void addNearby(int idToAdd) {
        if(nearbyNode==null)
            nearbyNode=new int[]{idToAdd};
        else {
            int[] newArray = new int[nearbyNode.length + 1];

            System.arraycopy(nearbyNode, 0, newArray, 0, nearbyNode.length);
            newArray[nearbyNode.length] = idToAdd;
            nearbyNode = newArray;
        }
    }

    boolean isNearby(int testId) {
        for (int aNearbyNode : nearbyNode) if (aNearbyNode == testId) return true;
        return false;
    }

    int[] getNearby() {
        return nearbyNode;
    }

    int getCurrentsPath() {
        return sPath;
    }

    void setCurrentsPath(int newCharge) {
        sPath = newCharge;
    }

    boolean PreDecided() {
        return sPath != 0;
    }
}
