package programmingSchool;

import programmingSchoolDao.SolutionDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int exercise_id;
    private Integer user_id;
    private List<Solution> allSolutionByExercise = new ArrayList<>();
    public Solution(){}
    Solution( int id, Timestamp created, Timestamp updated, String description, int exercise_id,
              Integer user_id){
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public List<Exercise> findAllByUserId (int user_id){
        SolutionDao exerciseDao = new SolutionDao();
        return exerciseDao.findAllByUserId(user_id);
    }
    public void findAllByExerciseId (int exercise_id){
        SolutionDao exerciseDao = new SolutionDao();
        allSolutionByExercise = exerciseDao.findAllByExerciseId(exercise_id);
    }
}
