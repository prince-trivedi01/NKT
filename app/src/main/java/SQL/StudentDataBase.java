package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modal.Student;

/**
 * Created by Prince on 05-04-2018.
 */

public class StudentDataBase extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StudentRecords.db";

    // Student table name
    private static final String TABLE_StudentDetails = "Student";

    // Student Table Columns names
    private static final String COLUMN_Student_ID = "student_id";
    private static final String COLUMN_Student_NAME = "student_name";
    private static final String COLUMN_Student_Branch = "student_branch";
    private static final String COLUMN_Student_PASSWORD = "student_password";
    private static final Boolean COLUMN_Student_ATTEND = Boolean.FALSE;
    private static final String COLUMN_Student_UserID="student_userId";

    // create table sql query
    private String CREATE_StudentDetails = "CREATE TABLE " + TABLE_StudentDetails + "("
            + COLUMN_Student_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_Student_NAME + " TEXT,"
            + COLUMN_Student_Branch + " TEXT," + COLUMN_Student_PASSWORD + " TEXT" + COLUMN_Student_ATTEND +" Boolean"+
            COLUMN_Student_UserID + "TEXT" +  ")";

    // drop table sql query
    private String DROP_Student_TABLE = "DROP TABLE IF EXISTS " + TABLE_StudentDetails;

    public StudentDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_StudentDetails);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop Student Table if exist
        db.execSQL(DROP_Student_TABLE);

        // Create tables again
        onCreate(db);


    }


    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Student_NAME, student.getStd_name());
        values.put(COLUMN_Student_Branch, student.getStd_branch());
        values.put(COLUMN_Student_PASSWORD, student.getStd_password());
        values.put(COLUMN_Student_UserID,student.getStd_userID());
        // Inserting Row
        db.insert(TABLE_StudentDetails, null, values);
        db.close();
    }


    public List<Student> getAllStudent() {
        // array of columns to fetch
        String[] columns = {
              //  COLUMN_Student_ID,
                COLUMN_Student_Branch,
                COLUMN_Student_NAME,
                COLUMN_Student_PASSWORD,
                COLUMN_Student_UserID,
                String.valueOf(COLUMN_Student_ATTEND)
        };
        // sorting orders
        String sortOrder =
                COLUMN_Student_NAME + " ASC";
        List<Student> StudentList = new ArrayList<Student>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the Student table
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_id,Student_name,Student_email,Student_password FROM Student ORDER BY Student_name;
         */
        Cursor cursor = db.query(TABLE_StudentDetails, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student Student = new Student();
                Student.setStd_userID(cursor.getString(cursor.getColumnIndex(COLUMN_Student_UserID)));
                Student.setStd_name(cursor.getString(cursor.getColumnIndex(COLUMN_Student_NAME)));
                Student.setStd_branch(cursor.getString(cursor.getColumnIndex(COLUMN_Student_Branch)));
                Student.setStd_password(cursor.getString(cursor.getColumnIndex(COLUMN_Student_PASSWORD)));
                // Adding Student record to list
                StudentList.add(Student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return Student list
        return StudentList;
    }



    public void updateStudent(Student Student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Student_NAME, Student.getStd_name());
        values.put(COLUMN_Student_Branch, Student.getStd_branch());
        values.put(COLUMN_Student_UserID,Student.getStd_userID());
        values.put(COLUMN_Student_PASSWORD, Student.getStd_password());

        // updating row
        db.update(TABLE_StudentDetails, values, COLUMN_Student_UserID + " = ?",
                new String[]{String.valueOf(Student.getStd_userID())});
        db.close();
    }

    /**
     * This method is to delete Student record
     *
     * @param student
     */
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete Student record by id
        db.delete(TABLE_StudentDetails, COLUMN_Student_UserID + " = ?",
                new String[]{String.valueOf(student.getStd_userID())});
        db.close();
    }



    /**
     * This method to check Student exist or not
     *
     * @param UserID
     * @return true/false
     */
    public boolean checkStudent(String UserID) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Student_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_Student_UserID + " = ?";

        // selection argument
        String[] selectionArgs = {UserID};

        // query Student table with condition
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_id FROM Student WHERE Student_UserID = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_StudentDetails, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check Student exist or not
     *
     * @param UserID
     * @param password
     * @return true/false
     */
    public boolean checkStudent(String UserID, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_Student_UserID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_Student_UserID + " = ?" + " AND " + COLUMN_Student_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {UserID, password};

        // query Student table with conditions
        /**
         * Here query function is used to fetch records from Student table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Student_id FROM Student WHERE Student_UserID = 'jack@androidtutorialshub.com' AND Student_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_StudentDetails, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            //query where Student=this set =attend=true;
            return true;
        }

        return false;
    }


}
