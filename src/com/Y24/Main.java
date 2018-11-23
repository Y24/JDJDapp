package com.Y24;

public class Main {
    public static void main(String[] args) {
        // write your code here
        global client = new global(5, 5, new int[]{0, 1, 100, 1, 2, 1, 2, 3, 1, 3, 4, 1, 0, 4, 1});
        //5,5,[0, 1, 1, 1, 2, 2, 2, 4, 3, 0, 3, 4, 1, 4, 2]
        printsPath(client, 0, 1);
        client.changeLineCharge(0,1,1);
        printsPath(client,0,1);
        client.deleteLine(0,1);
        printsPath(client,0,1);

    }

    private static void printsPath(global client, int from, int to) {
        client.calculatesPath(from, to);
        System.out.println(client.getsPath());
    }
}
