import programmingSchool.Exercise;
import programmingSchool.Solution;
import programmingSchool.User;
import programmingSchoolDao.ExerciseDao;
import programmingSchoolDao.SolutionDao;
import programmingSchoolDao.UserDao;

import java.util.List;
import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();
        Integer id;
        String order;
        if(args.length>0) {
            id = Integer.parseInt(args[0]);
            user = userDao.read(id);
        }
        System.out.println("Wybierz opcję:");
        System.out.println("add\nview\nquit\n");
        order = getString();
        switch (order){
            case "view":
                viewSolution(user);
                break;
            case "add":

                addSolution(user);
                break;
        }
    }

    private static void viewSolution(User user) {
        SolutionDao solutionDao = new SolutionDao();
        for (Solution solution: solutionDao.findAllByExerciseId(user.getId())) {
            System.out.println(solution);
        }
    }

    private static void addSolution(User user) {
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exercises = exerciseDao.findAllNotSolvedByUserId(user.getId());
        for (Exercise exercise: exercises) {
            System.out.println(exercise.getId() + " " + exercise.getTitle());
        }
        System.out.println("Podaj ");

    }

    private static String getString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
