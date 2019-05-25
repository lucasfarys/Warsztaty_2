import programmingSchool.Exercise;
import programmingSchool.Group;
import programmingSchool.Solution;
import programmingSchool.User;
import programmingSchoolDao.ExerciseDao;
import programmingSchoolDao.GroupDao;
import programmingSchoolDao.SolutionDao;
import programmingSchoolDao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminProgram {
    public static void main(String[] args) {
        adminProgram();
    }

    private static void userProgram() {

    }

    private static void adminProgram() {
        int order = getAdminNumber();
        switch (order){
            case 1:
                chooseUserAction();
                break;
            case 2:
                chooseExerciseAction();
                break;
            case 3:
                chooseUserGroupAction();
                break;
            case 4:
                chooseExerciseAnswer();
                break;
        }
    }

    private static void chooseExerciseAnswer() {
        String order = "";
        while(!("quit".equalsIgnoreCase(order))) {
            Scanner scan = new Scanner(System.in);
            while (!("add".equalsIgnoreCase(order) || "view".equalsIgnoreCase(order) ||
                    "quit".equalsIgnoreCase(order))) {
                UserDao.printAllUsers();
                System.out.println("Wybierz jedną z opcji:");
                System.out.println("add \nview \nquit");
                order = scan.nextLine();
            }
            switch (order) {
                case "add":
                    addExerciseAnswer();
                    break;
                case "view":
                    viewExerciseAnswer();
                    break;
            }
            //order = ""
        }
    }

    private static void viewExerciseAnswer() {
        List<Exercise> exercises = new ArrayList<>();
        System.out.println("Podaj id użytkownika:");
        int user_id = getNumber();
        Solution solution = new Solution();
        exercises = solution.findAllByUserId(user_id);
        System.out.println("Rozwiązania użytkownika o id: " + user_id);
        //2for(String val: )

    }

    private static void addExerciseAnswer() {
        System.out.println("Podaj id użytkownika:");
        int user_id = getNumber();
        ExerciseDao.printAllExercise();
        System.out.println("Podaj id zadania:");
        int exercise_id = getNumber();
        Solution solution = new Solution();
        SolutionDao solutionDao = new SolutionDao();
        solution.setUser_id(user_id);
        solution.setExercise_id(exercise_id);
        solutionDao.create(solution);
    }

    private static void chooseUserGroupAction() {
        String order = "";
        while(!("quit".equalsIgnoreCase(order))) {
            GroupDao.printAllGroup();
            order = getOrder();
            switch (order) {
                case "add":
                    addGroup();
                    break;
                case "edit":
                    editGroup();
                    break;
                case "delete":
                    delete_Group();
                    break;
            }
        }
        
    }

    private static void addGroup() {
        Group group = new Group();
        GroupDao groupDao = new GroupDao();
        System.out.println("Podaj nazwe grupy:");
        group.setName(getString());
        groupDao.create(group);
        System.out.println("Done");
    }

    private static void editGroup() {
        Group group = new Group();
        GroupDao groupDao = new GroupDao();
        System.out.println("Podaj nazwe grupy:");
        group.setName(getString());
        System.out.println("Podaj id grupy do edycji:");
        group.setId(getNumber());
        groupDao.update(group);
        System.out.println("Done");
    }

    private static void delete_Group() {
        System.out.println("Podaj id doquit usuniecia");
        int group_id = getNumber();
        GroupDao groupDao = new GroupDao();
        groupDao.delete(group_id);
    }

    private static void chooseExerciseAction() {
        String order = "";
        while(!("quit".equalsIgnoreCase(order))) {
            printAllExercise();
            order = getOrder();
            switch (order) {
                case "add":
                    addExercise();
                    break;
                case "edit":
                    editExercise();
                    break;
                case "delete":
                    delete_Exercise();
                    break;
            }
        }

    }

    private static void delete_Exercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println("Podaj id zadania do usunięcia:");
        int exercise_id = getNumber();
        exerciseDao.delete(exercise_id);
    }

    private static void editExercise() {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println("Podaj tytuł zadania:");
        exercise.setTitle(getString());
        System.out.println("Podaj opis zadania:");
        exercise.setDescription(getString());
        System.out.println("Podaj id zadania:");
        exercise.setId(getNumber());
        exerciseDao.update(exercise);

    }

    private static void addExercise() {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println("Podaj tytuł zadania:");
        exercise.setTitle(getString());
        System.out.println("Podaj opis zadania:");
        exercise.setDescription(getString());
        exerciseDao.create(exercise);

    }

    private static void printAllExercise() {
        ExerciseDao.printAllExercise();
    }

    private static void chooseUserAction() {
        String order = "";
        while(!("quit".equalsIgnoreCase(order))) {
            printAllUsers();
            order = getOrder();
            switch (order) {
                case "add":
                    addUser();
                    break;
                case "edit":
                    editUser();
                    break;
                case "delete":
                    deleteUser();
                    break;
                case "quit":
                    break;
            }
        }
    }

    private static String getOrder() {
        String order = "";
        Scanner scan = new Scanner(System.in);
            while (!("add".equalsIgnoreCase(order) || "edit".equalsIgnoreCase(order) || "delete".equalsIgnoreCase(order) ||
                "quit".equalsIgnoreCase(order))) {
                System.out.println("Wybierz jedną z opcji:");
                System.out.println("add \nedit \ndelete \nquit");
                order = scan.nextLine();
        }
        return order;
    }

    private static void deleteUser() {
        UserDao userDao = new UserDao();
        System.out.println("Podaj id użytkownika do usunięcia:");
        int user_id = getNumber();
        userDao.delete(user_id);
        System.out.println("Done");
    }

    private static void editUser() {
        User user = new User();
        UserDao userDao = new UserDao();
        System.out.println("Podaj username:");
        user.setUserName(getString());
        System.out.println("Podaj email:");
        user.setEmail(getString());
        System.out.println("Podaj password:");
        user.setPassword(getString());
        System.out.println("Podaj id:");
        user.setId(getNumber());
        userDao.update(user);
        System.out.println("Done");
    }

    private static void addUser() {
        User user = new User();
        UserDao userDao = new UserDao();
        System.out.println("Podaj username:");
        user.setUserName(getString());
        System.out.println("Podaj email:");
        user.setEmail(getString());
        System.out.println("Podaj password:");
        user.setPassword(getString());
        userDao.create(user);
        System.out.println("Done");
    }

    private static String getString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private static void printAllUsers() {
        UserDao.printAllUsers();
    }

    private static int getAdminNumber() {
        int number = 0;
        while (!(number== 1 || number== 2 || number== 3 || number== 4)){
            System.out.println("Wybierz program:");
            System.out.println("1: Zarządzanie Użytkownikami");
            System.out.println("2: Zarządzanie zadaniami");
            System.out.println("3: Zarządzanie grupami");
            System.out.println("4: Przypisywanie zadań");
            number = getNumber();
        }
        return number;
    }

    private static int getMainNumber() {
        int number = 0;
        while (!(number== 1 || number== 2)){
            System.out.println("Wybierz program:");
            System.out.println("1: Admin program");
            System.out.println("2: User program");
            number = getNumber();
        }
        return number;
    }

    private static int getNumber() {
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()){
            System.out.println("Podaj liczbę:");
            scan.nextLine();
        }
        return scan.nextInt();
    }
}
