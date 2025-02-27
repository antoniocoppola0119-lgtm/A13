package com.g2.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableRobot {
    @JsonProperty("testClassId")
    private String testClassId;
    @JsonProperty("robotType")
    private String robotType;
    @JsonProperty("difficulty")
    private int difficulty;

    public AvailableRobot() {
    }

    public AvailableRobot(String testClassId, String robotType, int difficulty) {
        this.testClassId = testClassId;
        this.robotType = robotType;
        this.difficulty = difficulty;
    }

    public String getTestClassId() {
        return testClassId;
    }

    public void setTestClassId(String testClassId) {
        this.testClassId = testClassId;
    }

    public String getRobotType() {
        return robotType;
    }

    public void setRobotType(String robotType) {
        this.robotType = robotType;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "AvailbleRobots{" +
                "testClassId='" + testClassId + '\'' +
                ", robotType='" + robotType + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
