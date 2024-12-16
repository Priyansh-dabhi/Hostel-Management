package com.priyansh.hostelmanagementnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HostelManagement";
    public static final int DATABASE_VERSION = 2;

    // Student Login and Registration:
    private static final String TABLE_STUDENT_LOGIN = "Users";
    public static final String COL_NAME = "name";
    public static final String COL_ENROLLMENT_NO = "enrollment";
    public static final String COL_EMAIL = "email";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    // Admin Login:
    private static final String TABLE_NAME_ADMIN_LOGIN = "Admin_login";
    private static final String COL_ADMIN_USERNAME = "admin_username";
    private static final String COL_ADMIN_PASSWORD = "admin_password";

    //Staff Login
    private static final String TABLE_NAME_STAFF_LOGIN = "Staff_login";
    private static final String COL_STAFF_USERNAME = "Staff_username";
    private static final String COL_STAFF_PASSWORD = "Staff_password";

    // In out timings of student
    private static final String TABLE_IN_OUT_TIMINGS = "InOutTimings";
    private static final String COL_ENROLLMENT_INOUT = "enrollment";
    private static final String COL_STATUS = "status";
    private static final String COL_TIMESTAMP = "timestamp";

    // PRESENT AND ABSENT COLUMNS
    private static final String TABLE_MESS_ATTENDANCE = "Attendance";
    private static final String COL_MESS_ENROLLMENT_INOUT = "enrollment";
    private static final String COL_PRESENT_OR_ABSENT = "PresentORAbsent";
    private static final String COL_MESS_TIMESTAMP = "timestamp";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            // STUDENT
            // Student login and registration table
            String createStudentTable = "CREATE TABLE " + TABLE_STUDENT_LOGIN + " (" +
                    COL_NAME + " TEXT, " +
                    COL_ENROLLMENT_NO + " INTEGER PRIMARY KEY, " +  // Corrected INT to INTEGER
                    COL_EMAIL + " TEXT, " +
                    COL_USERNAME + " TEXT, " +
                    COL_PASSWORD + " TEXT)";
            db.execSQL(createStudentTable);
            //Temporary for testing
//        ContentValues studentValues = new ContentValues();
//        studentValues.put(COL_NAME, "staff1");
//        studentValues.put(COL_EMAIL,"priyanshex@gmail.com");
//        studentValues.put(COL_USERNAME,"Priyansh");
//        studentValues.put(COL_PASSWORD, "12345");
//        db.insert(TABLE_STUDENT_LOGIN, null, studentValues);

            //ADMIN
            // Admin login table
            String createAdminTable = "CREATE TABLE " + TABLE_NAME_ADMIN_LOGIN + " (" +
                    COL_ADMIN_USERNAME + " TEXT PRIMARY KEY, " +
                    COL_ADMIN_PASSWORD + " TEXT)";
            db.execSQL(createAdminTable);

            // insertin default admin username and password

            ContentValues adminValues = new ContentValues();
            adminValues.put(COL_ADMIN_USERNAME, "admin");
            adminValues.put(COL_ADMIN_PASSWORD, "password123");
            db.insert(TABLE_NAME_ADMIN_LOGIN, null, adminValues);

            //STAFF
            //STAFF login table
            String createStaffTable = "CREATE TABLE " + TABLE_NAME_STAFF_LOGIN + " (" +
                    COL_STAFF_USERNAME + " TEXT PRIMARY KEY, " +
                    COL_STAFF_PASSWORD + " TEXT)";
            db.execSQL(createStaffTable);

//            // inserting default staff username and password
//            //1
//            ContentValues staffValues = new ContentValues();
//            staffValues.put(COL_STAFF_USERNAME, "staff1");
//            staffValues.put(COL_STAFF_PASSWORD, "password1");
//            db.insert(TABLE_NAME_STAFF_LOGIN, null, staffValues);
//            //2
//            ContentValues staffValues2 = new ContentValues();
//            staffValues2.put(COL_STAFF_USERNAME, "staff2");
//            staffValues2.put(COL_STAFF_PASSWORD, "password2");
//            db.insert(TABLE_NAME_STAFF_LOGIN, null, staffValues2);
//            //3
//            ContentValues staffValues3 = new ContentValues();
//            staffValues3.put(COL_STAFF_USERNAME, "staff3");
//            staffValues3.put(COL_STAFF_PASSWORD, "password3");
//            db.insert(TABLE_NAME_STAFF_LOGIN, null, staffValues3);

            // CREATING IN AND OUT TIMING TABLE
            String createInOutTimingsTable = "CREATE TABLE " + TABLE_IN_OUT_TIMINGS + " (" +
                    COL_ENROLLMENT_INOUT + " INTEGER , " +
                    COL_STATUS + " TEXT, " +
                    COL_TIMESTAMP + " TEXT, " +
                    "FOREIGN KEY(" + COL_ENROLLMENT_INOUT + ") REFERENCES " + TABLE_STUDENT_LOGIN + "(" + COL_ENROLLMENT_NO + "))";
            db.execSQL(createInOutTimingsTable);

            //creating MESS ATTENDANCE TABLE (PRESENT/ABSENT)

            String createMessTable = "CREATE TABLE " + TABLE_MESS_ATTENDANCE + " (" +
                    COL_MESS_ENROLLMENT_INOUT + " INTEGER , " +
                    COL_PRESENT_OR_ABSENT + " TEXT, " +
                    COL_MESS_TIMESTAMP + " TEXT, " +
                    "FOREIGN KEY(" + COL_ENROLLMENT_INOUT + ") REFERENCES " + TABLE_STUDENT_LOGIN + "(" + COL_ENROLLMENT_NO + "))";
            db.execSQL(createMessTable);

        }catch (Exception e){
            Log.e("DatabaseError", "Error fetching InOut data: " + e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables if they exist and recreate them
        if (oldVersion < 2) {
            // Add necessary columns or tables here
            db.execSQL("ALTER TABLE " + TABLE_IN_OUT_TIMINGS + " ADD COLUMN " + COL_ENROLLMENT_INOUT + " TEXT");

            // Ensure Attendance table is created if upgrading from a lower version
            String createAttendanceTable = "CREATE TABLE " + TABLE_MESS_ATTENDANCE + " ("+
                    COL_MESS_ENROLLMENT_INOUT + " INTEGER, " +
                    COL_PRESENT_OR_ABSENT + " TEXT, " +
                    COL_MESS_TIMESTAMP + " TEXT, " +
                    "FOREIGN KEY(" + COL_MESS_ENROLLMENT_INOUT + ") REFERENCES " + TABLE_STUDENT_LOGIN + "(" + COL_ENROLLMENT_NO + "))";
            db.execSQL(createAttendanceTable);
        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ADMIN_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STAFF_LOGIN);
        onCreate(db);
    }

    // Registering the new user
    public Boolean registration(String name, String enrollment,String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_ENROLLMENT_NO,enrollment);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_STUDENT_LOGIN, null, contentValues);
        return result != -1;  // Return true if insert was successful
    }

    // Validating user login
    public Boolean loginvalidation(String enrollment, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENT_LOGIN + " WHERE " + COL_ENROLLMENT_NO + "=? AND " + COL_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{enrollment, password});
        boolean isValid = (cursor != null && cursor.moveToFirst());
        if (cursor != null) cursor.close();
        return isValid;
    }

    // Registering the new user
    public Boolean StaffRegistration( String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STAFF_USERNAME, username);
        contentValues.put(COL_STAFF_PASSWORD, password);

        long result = db.insert(TABLE_NAME_STAFF_LOGIN, null, contentValues);
        return result != -1;  // Return true if insert was successful
    }
    //storing enrollment in inout table
//    public Boolean storeEnrollment(String enrollment){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_ENROLLMENT_NO,enrollment);
//        long result = db.insert(TABLE_IN_OUT_TIMINGS, null, contentValues);
//        return result != -1;  // Return true if insert was successful
//    }

    // Validating admin login
    public Boolean adminLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_ADMIN_LOGIN + " WHERE " + COL_ADMIN_USERNAME + "=? AND " + COL_ADMIN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isMatched = (cursor != null && cursor.moveToFirst());
        if (cursor != null) cursor.close();
        return isMatched;
    }

    // validating staff login
    public Boolean staffLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_STAFF_LOGIN + " WHERE " + COL_STAFF_USERNAME + "=? AND " + COL_STAFF_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isMatched = (cursor != null && cursor.moveToFirst());
        if (cursor != null) cursor.close();
        return isMatched;
    }

    // Getting name from the registration
    public String getName(String fullname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "";
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_STUDENT_LOGIN + " WHERE " + COL_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{fullname});

        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return name;
    }
    // Getting name from the staff table
    public String getStaffName(String fullname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "";
        String query = "SELECT " + COL_STAFF_USERNAME + " FROM " + TABLE_NAME_STAFF_LOGIN + " WHERE " + COL_STAFF_USERNAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{fullname});

        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return name;
    }


    // for staff in and out, fetching all student name
    public List<String> getAllStudentNames() {
        List<String> studentNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_NAME + " FROM " + TABLE_STUDENT_LOGIN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);  // column 0 is the name column
                studentNames.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentNames;
    }
    // Getting all staff name
    public List<String> getAllStaffNames() {
        List<String> staffNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COL_STAFF_USERNAME + " FROM " + TABLE_NAME_STAFF_LOGIN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);  // column 0 is the name column
                staffNames.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return staffNames;
    }
    // Method to store the date of in and out of students

//    public List<InOutTiming> getInOutTimingsForStudent(String username) {
//        List<InOutTiming> timingsList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT " + COL_STATUS + ", " + COL_TIMESTAMP + " FROM " + TABLE_IN_OUT_TIMINGS +
//                " WHERE " + COL_ENROLLMENT_INOUT + " = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{username});
//
//        if (cursor.moveToFirst()) {
//            do {
//                String status = cursor.getString(0);
//                String timestamp = cursor.getString(1);
//                timingsList.add(new InOutTiming(username,status, timestamp));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//        return timingsList;
//    }
    //
    // Method to add in/out timing in MyDatabaseHelper.java
    public void addInOutTiming(String enroll, String status, String timestamp) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_ENROLLMENT_INOUT, enroll);
            values.put(COL_STATUS, status);
            values.put(COL_TIMESTAMP, timestamp);

            db.insert(TABLE_IN_OUT_TIMINGS, null, values);
            db.close();
        } catch (Exception e) {
            Log.e("DatabaseError", "Error fetching InOut data: " + e.getMessage());
        }
    }
    // method to add attendance
    public void addAttendance(String enroll, String status, String timestamp) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_MESS_ENROLLMENT_INOUT, enroll);
            values.put(COL_PRESENT_OR_ABSENT, status);
            values.put(COL_MESS_TIMESTAMP, timestamp);

            db.insert(TABLE_MESS_ATTENDANCE, null, values);
            db.close();
        } catch (Exception e) {
            Log.e("DatabaseError", "Error fetching InOut data: " + e.getMessage());
        }
    }


    //gettting username from the inout timings

//    public String getUsername(String username) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String name = "";
//        String query = "SELECT " + COL_ENROLLMENT_INOUT + " FROM " + TABLE_IN_OUT_TIMINGS + " WHERE " + COL_ENROLLMENT_INOUT + "=?";
//        Cursor cursor = db.rawQuery(query, new String[]{username});
//
//        if (cursor.moveToFirst()) {
//            name = cursor.getString(0);
//        }
//        cursor.close();
//        db.close();
//        return name;
//    }

    //
// This method retrieves all rows from the TABLE_IN_OUT_TIMINGS
    public List<InOutTiming> getAllInOutData() {
        List<InOutTiming> timingsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Fetch enrollment, status, and timestamp columns
        String query = "SELECT * FROM " + TABLE_IN_OUT_TIMINGS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String enrollment = cursor.getString(cursor.getColumnIndexOrThrow("enrollment"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));

                timingsList.add(new InOutTiming(enrollment, status, timestamp));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return timingsList;
    }
//    public List<InOutTiming> getAllInOutData(String enrollment) {
//        List<InOutTiming> timingsList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // Fetch enrollment, status, and timestamp columns for the given enrollment
//        String query = "SELECT * FROM " + TABLE_IN_OUT_TIMINGS + " WHERE " + COL_ENROLLMENT_INOUT + " = ?";
//
//        // Use the provided enrollment in the query
//        Cursor cursor = db.rawQuery(query, new String[]{enrollment});
//
//        if (cursor.moveToFirst()) {
//            do {
//                // You don't need to fetch enrollment again since it's already passed as a parameter
//                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
//                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
//
//                // Create a new InOutTiming object with the fetched data
//                timingsList.add(new InOutTiming(enrollment, status, timestamp));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return timingsList;
//    }

    // This method retrieves all rows from the TABLE_MESS_ATTENDANCE
    public List<Attendance> getAllAttendanceData() {
        List<Attendance> timingsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Fetch enrollment, status, and timestamp columns
        String query = "SELECT * FROM " + TABLE_MESS_ATTENDANCE;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    String enrollment = cursor.getString(cursor.getColumnIndexOrThrow("enrollment"));
                    String PresentORAbsent = cursor.getString(cursor.getColumnIndexOrThrow("PresentORAbsent"));
                    String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));

                    timingsList.add(new Attendance(enrollment, PresentORAbsent, timestamp));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return timingsList;
    }


    // MyDatabaseHelper.java used in both inout and mess adapters
    public String getEnrollmentByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String enrollment = null;

        String query = "SELECT " + COL_ENROLLMENT_NO + " FROM " + TABLE_STUDENT_LOGIN + " WHERE " + COL_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        if (cursor.moveToFirst()) {
            enrollment = cursor.getString(cursor.getColumnIndexOrThrow("enrollment"));
        }
        cursor.close();
        return enrollment;
    }
    //getting password
    // deleting staff from database
    public void deleteStaff(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_STAFF_LOGIN, COL_STAFF_USERNAME + " = ?", new String[]{username});
        db.close();
    }


}

