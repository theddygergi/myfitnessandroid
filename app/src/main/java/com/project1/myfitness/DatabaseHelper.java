package com.project1.myfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myfitness1.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createPlanTable(db);
        createFoodTable(db);
        createHasFoodTable(db);
        createExerciseTable(db);
        createWorkoutTable(db);
        createGoalTable(db);
        createUserTable(db);
        createClassTable(db);
        createReminderTable(db);
        createProgressTable(db);
        createUserReminderTable(db);
        createUserClassTable(db);
        //addGoals(db);
        addClasses(db);
    }

    private void addClasses(SQLiteDatabase db) {
        String addClasses = "INSERT INTO classs (className, classLength, classDifficulty, classDesc) VALUES " +
                "('Yoga Class', 60, 'Beginner', 'Relaxing yoga session'), " +
                "('Zumba Class', 45, 'Intermediate', 'Fun and energetic dance workout'), " +
                "('Steps Class', 60, 'Advanced', 'High-intensity step aerobics')";
        db.execSQL(addClasses);
    }

    /*private void addGoals(SQLiteDatabase db) {
        String addGoals = "INSERT INTO goal values ('Lose Weight',4,1), ('Gain Weight',4,2), ('Add Muscle',4,3)";
        db.execSQL(addGoals);
    }*/

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
                "goalID INTEGER," +
                "FOREIGN KEY (goalID) REFERENCES goal(goalID));";
        db.execSQL(user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement upgrade logic if needed
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


    public boolean setDefaultProgress(int ID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("progressUserID",ID);
        long progress = db.insert("progress", null, cv);
        if(progress == -1)
            return false;
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

}
