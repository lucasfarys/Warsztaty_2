package programmingSchoolDao;

import programmingSchool.Exercise;
import programmingSchool.Solution;
import programmingSchool.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES (?,?,?,?,?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_EXERCISE_BY_USER = "SELECT * FROM users join solution on users.id=solution.users_id join exercise on exercise.id=solution.exercise_id AND solution.users_id=?";
    private static final String FIND_ALL_SOLUTION_BY_EXERCISE = "SELECT * FROM solution WHERE solution.exercise_id = ?" +
            "ORDER BY updated DESC;";

    public Solution create(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, solution.getCreated());
            statement.setTimestamp(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUser_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Solution read(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setTimestamp(1, solution.getCreated());
            statement.setTimestamp(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getUser_id());
            statement.setInt(5, solution.getExercise_id());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Exercise> findAllByUserId (int user_id){
        List<Exercise> exercise = new ArrayList<>();
        Exercise exerciseGet = new Exercise();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_BY_USER);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                exerciseGet.setId(resultSet.getInt("id"));
                exerciseGet.setTitle(resultSet.getString("title"));
                exerciseGet.setDescription(resultSet.getString("description"));
                exercise.add(exerciseGet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
    }
    public List<Solution> findAllByExerciseId (int exercise_id){
        List<Solution> solution = new ArrayList<>();
        Solution solutionGet = new Solution();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setInt(1, exercise_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                solutionGet.setId(resultSet.getInt("id"));
                solutionGet.setCreated(resultSet.getTimestamp("created"));
                solutionGet.setUpdated(resultSet.getTimestamp("updated"));
                solutionGet.setDescription(resultSet.getString("description"));
                solutionGet.setExercise_id(resultSet.getInt("exercise_id"));
                solutionGet.setUser_id(resultSet.getInt("user_id"));
                solution.add(solutionGet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solution;
    }
}
