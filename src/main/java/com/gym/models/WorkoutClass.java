package com.gym.models;
/**
 * Creates the foundation for workout classes
 */
public class WorkoutClass {
    private int classId;
    private String classType;
    private String classDescription;
    private int trainerId;

    public WorkoutClass() {}

    public WorkoutClass(String classType, String classDescription, int trainerId) {
        this.classType = classType;
        this.classDescription = classDescription;
        this.trainerId = trainerId;
    }

    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public String getClassDescription() { return classDescription; }
    public void setClassDescription(String classDescription) { this.classDescription = classDescription; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
}
