package com.project1.myfitness;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myfitness1.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createFoodTable(db);
        createPlanTable(db);
        //createReminderTable(db);
        createClassTable(db);
        createHasFoodTable(db);
        createExerciseTable(db);
        createWorkoutTable(db);
        createGoalTable(db);
        createUserTable(db);
        createProgressTable(db);
        //createUserReminderTable(db);
        createUserClassTable(db);
        createAchievedWorkouts(db);
        addGoals(db);
        addClasses(db);
        addNutPlan(db);
        addFood(db);
        addHasFood(db);
        addWorkouts(db);
        addExercises(db);

    }



    private void addExercises(SQLiteDatabase db) {
        String addExercises = "INSERT INTO exercise (exerciseName, exerciseType, exerciseDesc, exerciseNbOfSets, exerciseNbOfReps, exerciseWorkoutID) VALUES " +
                "('Easy push ups', 'Chest', ' ', 3,5,1), " +
                "('Wall push ups', 'Chest', ' ',3,5,1), " +
                "('Push ups', 'Chest', ' ',3,5,1)," +
                "('Push ups', 'Chest', ' ',3,8,5), " +
                "('Incline push ups', 'Chest', ' ',3,8,5), " +
                "('Decline push ups', 'Chest', ' ',3,8,5)," +
                "('Push ups', 'Chest', ' ',3,12,9), " +
                "('Incline push ups', 'Chest', ' ',3,12,9)," +
                "('Decline push ups', 'Chest', ' ',3,12,9)," +
                "('Bicep Curls with Household Items (min 2 kg)', 'Arms', ' ',3,5,2), " +
                "('Tricep dips', 'Arms', ' ',3,5,2), " +
                "('Easy push ups', 'Arms', ' ',3,5,2)," +
                "('Hammer curls with Household Items (4kg and above)', 'Arms', ' ',3,8,6)," +
                "('Tricep dips', 'Arms', ' ',3,8,6), " +
                "('Easy push ups', 'Arms', ' ',3,8,6)," +
                "('Hammer curls with Household Items (6kg and above)', 'Arms', ' ',3,12,10), " +
                "('Tricep dips', 'Arms', ' ',3,12,10), " +
                "('Easy push ups', 'Arms', ' ',3,12,10)," +
                "('Crunches', 'Abs', ' ',3,5,3), ('Leg Raises', 'Abs', ' ',3,5,3), " +
                "('Russian Twists', 'Abs', ' ',3,5,3)," +
                "('Crunches', 'Abs', ' ',3,8,7), ('Leg Raises', 'Abs', ' ',3,8,7), ('Russian Twists', 'Abs', ' ',3,8,7)," +
                "('Hanging leg raises', 'Abs', ' ',3,12,11), ('Crunches', 'Abs', ' ',3,12,11), ('Russian Twists with weights', 'Abs', ' ',3,12,11)," +
                "('Bodyweight squats', 'Legs',' ',3,5,4), ('Lunges', 'Legs',' ',3,5,4), ('Calf raises', 'Legs',' ',3,5,4)," +
                "('Squats with weights', 'Legs',' ',3,8,8),('Lunges with weight', 'Legs',' ',3,8,8),('Calf raises with weight', 'Legs',' ',3,8,8)," +
                "('Pistol squats', 'Legs',' ',3,8,12),('Bulgarian split squats', 'Legs',' ',3,8,12),('Calf raises with weight', 'Legs',' ',3,8,12)";
                db.execSQL(addExercises);
    }

    private void addWorkouts(SQLiteDatabase db) {
        String addWorkouts = "INSERT INTO workout (workoutDifficulty, workoutType, workoutDesc, " +
                "workoutNbOfExercises, workoutGoalID) " +
                "VALUES" +
                "('Easy','Chest','Do these exercises to train your chest!', 3,1) , " +
                "('Easy','Arms','Do these exercises to train your arms!' ,3,1), " +
                "('Easy','Abs', 'Do these exercises to train your abs!',3,1), " +
                "('Easy','Legs','Do these exercises to train your legs!' , 3,1)," +
                "('Medium','Chest','Do these exercises to train your chest!' , 3,2) , " +
                "('Medium','Arms','Do these exercises to train your arms!' ,3,2), " +
                "('Medium','Abs','Do these exercises to train your abs!' ,3,2), " +
                "('Medium','Legs','Do these exercises to train your legs!' , 3,2)," +
                "('Hard','Chest','Do these exercises to train your chest!' , 3,3) ," +
                " ('Hard','Arms','Do these exercises to train your arms!' ,3,3), " +
                "('Hard','Abs','Do these exercises to train your abs!' ,3,3), " +
                "('Hard','Legs', 'Do these exercises to train your legs!', 3,3)";
        db.execSQL(addWorkouts);
    }

    private void addHasFood(SQLiteDatabase db) {
        String addHasFood = "INSERT INTO hasfood (hasFoodPlanID, hasFoodFoodID) VALUES " +
                "(1, 1), (2, 1), (3, 1), (4, 2), (5, 2), (6, 2), (7, 3), (8, 3), (9, 3)";
        db.execSQL(addHasFood);
    }


    private void addFood(SQLiteDatabase db) {
        String addFood = "INSERT INTO food (foodName, foodQuantity, foodCalories) VALUES "+
                "('B.E.C. Breakfast Bites', 1, 300)," +
                "('Double Apple Baked Oatmeal',1,300)," +
                "('Waffle Nachos',1,300)," +
                "('Oatmeal with Apricots and Pistachios',1,400)," +
                "('Fluffy Pancakes',1,400)," +
                "('Almond-Buckwheat Granola with Yogurt and Berries',1,400)," +
                "('Focaccia French Toast',1,500)," +
                "('Sheet Pan Asparagus Frittata',1,500)," +
                "('Loaded Pancake Tacos',1,500)";
        db.execSQL(addFood); }


    ///////////////////////////////////////////////////////////////////////////////////
    private void addNutPlan(SQLiteDatabase db) {
        String addplan = "INSERT INTO nutritionplan (planName, planCalories, planDesc) VALUES " +
                "('Plan for losing weight', 900, 'Plan made for losing weight' ), " +
                "('Plan for gaining weight', 1200, 'Plan made for gaining weight'), " +
                "('Plan for adding muscle', 1500, 'Plan made for adding muscle')";
        db.execSQL(addplan);
    }

    private void addGoals(SQLiteDatabase db) {
        String addGoal = "INSERT INTO goal (goalType, goalNbOfWorkouts, goalPlanID) VALUES " +
                "('Lose Weight', 4, 1), " +
                "('Gain Weight', 4, 2), " +
                "('Add Muscle', 4, 3)";
        db.execSQL(addGoal);
    }

    private void addClasses(SQLiteDatabase db) {
        String addClasses = "INSERT INTO classs (className, classLength, classDifficulty, classDesc) VALUES " +
                "('Yoga Class', 60, 'Beginner', 'Relaxing yoga session'), " +
                "('Zumba Class', 45, 'Intermediate', 'Fun and energetic dance workout'), " +
                "('Steps Class', 60, 'Advanced', 'High-intensity step aerobics')";
        db.execSQL(addClasses);
    }


    private void createUserClassTable(SQLiteDatabase db) {
        String userClass = "CREATE TABLE userclass (" +
                "ucUserID INTEGER , ucClassID," +
                "FOREIGN KEY (ucUserID) REFERENCES user(userID)," +
                "FOREIGN KEY (ucClassID) REFERENCES classs(classID));";
        db.execSQL(userClass);
    }

    private void createUserReminderTable(SQLiteDatabase db) {
        String userReminder = "CREATE TABLE userreminder (" +
                "urUserID INTEGER , urReminderID," +
                "FOREIGN KEY (urUserID) REFERENCES user(userID)," +
                "FOREIGN KEY (urReminderID) REFERENCES reminder(reminderID));";
        db.execSQL(userReminder);
    }

    private void createProgressTable(SQLiteDatabase db) {
        String progress = "CREATE TABLE progress (" +
                "progressUserID INTEGER PRIMARY KEY," +
                "progressGoal REAL DEFAULT 0," +
                "progressWorkout REAL DEFAULT 0," +
                "FOREIGN KEY (progressUserID) references user(UserID) );";
        db.execSQL(progress);
    }

    private void createAchievedWorkouts(SQLiteDatabase db) {
        String addAchieved = "CREATE TABLE achieved (" +
                "achievedUserID INTEGER PRIMARY KEY," +
                "achievedGoalID INTEGER," +
                "achievedWorkoutID INTEGER," +
                "achievedExerciseID INTEGER);";
        db.execSQL(addAchieved);
    }

    private void createReminderTable(SQLiteDatabase db) {
        String reminder = "CREATE TABLE reminder (" +
                "reminderID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "reminderName TEXT," +
                "reminderTime TEXT," +
                "reminderDate DATE);";
        db.execSQL(reminder);
    }

    private void createClassTable(SQLiteDatabase db) {
        String classs = "CREATE TABLE classs (" +
                "classID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "className TEXT," +
                "classLength INT," +
                "classDifficulty TEXT, classDesc TEXT); ";
        db.execSQL(classs);
    }

    private void createHasFoodTable(SQLiteDatabase db) {
        String hasfood = "CREATE TABLE hasfood (" +
                "hasFoodPlanID INTEGER ," +
                "hasFoodFoodID INTEGER," +
                "FOREIGN KEY (hasFoodPlanID) REFERENCES nutritionplan(planID)," +
                "FOREIGN KEY (hasFoodFoodID) REFERENCES food(foodID));";
        db.execSQL(hasfood);
    }

    private void createFoodTable(SQLiteDatabase db) {
        String food = "CREATE TABLE food (" +
                "foodID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "foodName TEXT," +
                "foodQuantity INTEGER," +
                "foodCalories INTEGER);";
        db.execSQL(food);
    }

    private void createPlanTable(SQLiteDatabase db) {
        String plan = "CREATE TABLE nutritionplan (" +
                "planID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "planName TEXT," +
                "planCalories INTEGER," +
                "planDesc TEXT);";
        db.execSQL(plan);
    }

    private void createExerciseTable(SQLiteDatabase db) {
        String exercise = "CREATE TABLE exercise (" +
                "exerciseID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exerciseName TEXT," +
                "exerciseType TEXT," +
                "exerciseDesc TEXT," +
                "exerciseNbOfSets INTEGER," +
                 "exerciseNbOfReps INTEGER,"+
                "exerciseWorkoutID INTEGER," +
                "FOREIGN KEY (exerciseWorkoutID) REFERENCES workout(workoutID));";
        db.execSQL(exercise);
    }

    private void createWorkoutTable(SQLiteDatabase db) {
        String workout = "CREATE TABLE workout (" +
                "workoutID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "workoutDifficulty TEXT," +
                "workoutType TEXT," +
                "workoutDesc TEXT," +
                "workoutNbOfExercises INTEGER," +
                "workoutGoalID INTEGER," +
                "FOREIGN KEY (workoutGoalID) REFERENCES goal(goalID));";
        db.execSQL(workout);
    }

    private void createGoalTable(SQLiteDatabase db) {
        String goal = "CREATE TABLE goal (" +
                "goalID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "goalType TEXT," +
                "goalNbOfWorkouts INTEGER," +
                "goalPlanID INTEGER," +
                "FOREIGN KEY (goalPlanID) REFERENCES nutritionplan(planID));";
        db.execSQL(goal);
    }

    private void createUserTable(SQLiteDatabase db) {
        String user = "CREATE TABLE user (" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT," +
                "userEmail TEXT," +
                "userPassword TEXT," +
                "userGender TEXT," +
                "userHeight REAL," +
                "userWeight REAL," +
                "userBMI REAL,"+
                "userStepCount DEFAULT 0,"+
                "userStepGoal DEFAULT 5000,"+
                "goalID INTEGER," +
                "FOREIGN KEY (goalID) REFERENCES goal(goalID));";
        db.execSQL(user);
    }
    ////////////////////////////////////////////////////////////////////////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addUser(String username,String email, String password, String gender, Float height, Float weight,Float BMI, int goalID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName", username);
        cv.put("userEmail", email);
        cv.put("userPassword", password);
        cv.put("userGender", gender);
        cv.put("userHeight", height);
        cv.put("userWeight",weight);
        cv.put("userBMI",BMI);
        cv.put("goalID", goalID);
        long user = db.insert("user", null, cv);
        return user != -1; // Return true if the insertion was successful, false otherwise
    }

    public boolean getUser(String usermail, String password){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM user WHERE userEmail = ? AND userPassword = ?";
        String[] args = {usermail,password};

        Cursor cursor = db.rawQuery(query,args);
        if(cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        else
            return false;
    }

    public String getUserByEmail(String usermail) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT userName FROM user WHERE userEmail = ?";
        String[] args = {usermail};

        Cursor cursor = db.rawQuery(query,args);
        if(cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(0);
            return username;
        }
        cursor.close();
        return null;
    }

    public int getUserID(String emailValue) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT userID FROM user WHERE userEmail = ?";
        String[] args = {emailValue};

        Cursor cursor = db.rawQuery(query,args);
        if(cursor != null && cursor.moveToFirst()){
            int userID = cursor.getInt(0);
            cursor.close();  // Close the cursor to avoid potential resource leaks
            return userID;
        }
        return 0;
    }

    public int getGoalID(String emailValue) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT userGoalID FROM user WHERE userEmail = ?";
        String[] args = {emailValue};

        Cursor cursor = db.rawQuery(query,args);
        if(cursor != null && cursor.moveToFirst()){
            int goalID = cursor.getInt(0);
            cursor.close();  // Close the cursor to avoid potential resource leaks
            return goalID;
        }
        return 0;
    }


    public boolean setDefaultProgress(int ID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("progressUserID",ID);
        long progress = db.insert("progress", null, cv);
        if(progress == -1) {
            return false;
        }
        return true;

    }

    public boolean addUserToClass(int ID, int classID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ucUserID",ID);
        cv.put("ucClassID",classID);
        long userclass = db.insert("userclass", null, cv);
        return userclass != -1;
    }

    public String getClassDescription(int classID){
        String desc;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT classDesc FROM classs WHERE classID = "+ classID;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()){
            desc = cursor.getString(0);
            return desc;
        }
        return null;
    }
    //food(foodID, foodName)
    //plan(planID, planName)
    //hasfood(planID, foodID)
    //select the foods with item id 1
    public List<String> getFood(int planID) {
        SQLiteDatabase db = getReadableDatabase();
        List<String> food = new ArrayList<>();
        String query = "SELECT * FROM hasfood NATURAL JOIN food NATURAL JOIN nutritionplan WHERE planID = " + planID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                int foodQuantity = cursor.getInt(cursor.getColumnIndex("foodQuantity"));
                int foodCalories = cursor.getInt(cursor.getColumnIndex("foodCalories"));
                String text = foodName + " (Quantity: " + foodQuantity + ", Calories: " + foodCalories + ")";
                food.add(text);
            } while (cursor.moveToNext());
        }
        cursor.close();  // Close the cursor to avoid potential resource leaks
        return food;
    }

    public List<String> getExercisesByGoalID(int goalID) {
        SQLiteDatabase db = getReadableDatabase();
        List<String> exercises = new ArrayList<>();

        String query = "SELECT exerciseName, exerciseType, exerciseDesc, exerciseNbOfSets, exerciseNbOfReps " +
                "FROM exercise " +
                "JOIN workout ON exercise.exerciseWorkoutID = workout.workoutID " +
                "JOIN goal ON workout.workoutGoalID = goal.goalID " +
                "WHERE goal.goalID = " + goalID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String exerciseName = cursor.getString(cursor.getColumnIndex("exerciseName"));
                @SuppressLint("Range") String exerciseType = cursor.getString(cursor.getColumnIndex("exerciseType"));
                @SuppressLint("Range") String exerciseDesc = cursor.getString(cursor.getColumnIndex("exerciseDesc"));
                @SuppressLint("Range") int exerciseNbOfSets = cursor.getInt(cursor.getColumnIndex("exerciseNbOfSets"));
                @SuppressLint("Range") int exerciseNbOfReps = cursor.getInt(cursor.getColumnIndex("exerciseNbOfReps"));

                String exerciseDetails = exerciseName +
                        "\nNumber of Sets: " + exerciseNbOfSets +
                        "\nNumber of Reps: " + exerciseNbOfReps;

                exercises.add(exerciseDetails);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return exercises;
    }
    
    public boolean setProgress(int userID) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE progress SET progressGoal = progressGoal + (25.0 / 3.0), progressWorkout = progressWorkout + (25.0 / 3.0) WHERE progressUserID = " + userID;

        try {
            db.execSQL(updateQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public double getProgress(int userID){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT progressGoal from progress WHERE progressUserID = " + userID;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            double progress = cursor.getDouble(0);
            return progress;
        }
        return 0;
    }

    public void updateSteps(int stepCount, int userID){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE user " +
                "SET userStepCount = " + stepCount + " WHERE userID = " + userID;
        db.execSQL(query);
    }

}
