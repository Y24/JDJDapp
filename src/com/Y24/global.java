package com.Y24;

public class global {
    private line[] lines;
    private node[] nodes;
    private int result;

    public global(int nodeNum, int lineNum, int[] allLines) {
        initNode(nodeNum);
        initLine(lineNum, allLines);
        result = 0;
    }

    private void initLine(int lineNum, int[] allLines) {
        lines = new line[lineNum];
        for (int i = 0; i < lineNum; i++)
            lines[i] = new line(allLines[3 * i], allLines[3 * i + 1], allLines[3 * i + 2]);
        initNearby();
    }

    private void initNearby() {
        for (com.Y24.line line : lines) {
            nodes[line.getFirst()].addNearby(line.getSecond());
            nodes[line.getSecond()].addNearby(line.getFirst());
        }
    }

    private void initNode(int nodeNum) {
        nodes = new node[nodeNum];
        for (int i = 0; i < nodeNum; i++)
            nodes[i] = new node(i);
    }

    int getsPath() {
        return result;
    }

    int getNodeLength() {
        return nodes.length;
    }

    int getLineLength() {
        return lines.length;
    }

    void calculatesPath(int fromId, int toId) {
        initOtherNode(toId);
        updatesPath(toId);
        result = nodes[fromId].getCurrentsPath();
        initNode(nodes.length);
        initNearby();
    }

    private void updatesPath(int idToUpdate) {
        boolean re;
        do {
            re = false;
            for (int i = 0; i < nodes.length; i++)
                if (i != idToUpdate) {
                    int tmp = nodes[i].getCurrentsPath();
                    nodes[i].setCurrentsPath(judge(i, idToUpdate));
                    if (tmp != nodes[i].getCurrentsPath() || nodes[i].getCurrentsPath() == 0)
                        re = true;
                }
        } while (re);
    }

    private int judge(int idToJudge, int objectId) {
        int[] nearbyNodes = nodes[idToJudge].getNearby();
        int comparedResult = nodes[idToJudge].getCurrentsPath();
        int startFlag = 0;
        if (comparedResult == 0)
            for (int i = 0; i < nearbyNodes.length; i++)
                if (nearbyNodes[i] != objectId && nodes[nearbyNodes[i]].PreDecided()) {
                    comparedResult = nodes[nearbyNodes[i]].getCurrentsPath() + getDirectPath(idToJudge, nearbyNodes[i]);
                    startFlag = i + 1;
                    break;
                }
        for (int j = startFlag; j < nearbyNodes.length; j++) {
            if (nearbyNodes[j] != objectId) {
                int tmp = nodes[nearbyNodes[j]].getCurrentsPath() + getDirectPath(idToJudge, nearbyNodes[j]);
                if (nodes[nearbyNodes[j]].PreDecided() && tmp < comparedResult)
                    comparedResult = tmp;
            }
        }
        return comparedResult;
    }

    private void initOtherNode(int objectId) {
        for (int i = 0; i < nodes.length; i++)
            if (i != objectId ) {
                if (!nodes[objectId].isNearby(i))
                    nodes[i].setCurrentsPath(0);
                else nodes[i].setCurrentsPath(getDirectPath(i,objectId));
            }
    }

    private int getDirectPath(int fromId, int toId) {
        for (com.Y24.line line : lines)
            if (line.equals(fromId, toId))
                return line.getCharge();
        return 0;
    }

    void changeLineCharge(int first, int second, int newCharge) {
        for (com.Y24.line line : lines)
            if (line.equals(first, second)) {
                line.setCharge(newCharge);
                break;
            }
        initNode(nodes.length);
        initNearby();
    }

    void deleteLine(int first, int second) {
        for (int i = 0; i < lines.length; i++)
            if (lines[i].equals(first, second)) {
                if (lines.length - 1 - i >= 0) System.arraycopy(lines, i + 1, lines, i, lines.length - 1 - i);
                break;
            }
        line[] newArray = new line[lines.length - 1];
        if (newArray.length >= 0) System.arraycopy(lines, 0, newArray, 0, newArray.length);
        initNode(nodes.length);
        initNearby();

    }
}
