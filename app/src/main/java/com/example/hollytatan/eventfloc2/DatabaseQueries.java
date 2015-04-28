package com.example.hollytatan.eventfloc2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by hollytatan on 28/04/15.
 */
public class DatabaseQueries extends SQLiteOpenHelper {

    private static final String DB_NAME = "Event_Floc.db";
    private static final int DB_VERSION = 1;
    private static final SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");
    private static final SimpleDateFormat parseDateTime = new SimpleDateFormat("dd-MM-yyy hh:mm a");

    //--------------------------------CREATE TABLE STRINGS-------------------------------
    private static final String TABLE_USER = "User";
    private static final String TABLE_STUDENT = "Student";
    private static final String TABLE_SOCIETY = "Society";
    private static final String TABLE_ADMIN = "Admin";
    private static final String TABLE_EVENT = "Event";
    private static final String TABLE_EVENT_TYPE = "Event_Type";
    private static final String TABLE_FOLLOW_SOCIETY = "Follow_Society";
    private static final String TABLE_HAS_CATEGORY = "Has_Category";
    private static final String TABLE_ATTENDS = "Attends";
    private static final String TABLE_COMMENT = "Comment";


    //---------------------------USER TABLE-----------------------------------------------
    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";


    //---------------------------STUDENT TABLE--------------------------------------------
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_USER_ID = "user_id";
    private static final String STUDENT_FIRSTNAME = "first_name";
    private static final String STUDENT_LASTNAME = "last_name";


    //---------------------------SOCIETY TABLE--------------------------------------------
    private static final String SOCIETY_ID = "society_id";
    private static final String SOCIETY_USER_ID = "user_id";
    private static final String SOCIETY_NAME = "society_name";
    private static final String SOCIETY_APPROVAL_DATE = "approval_date";
    private static final String SOCIETY_DESC = "society_desc";
    private static final String SOCIETY_FACULTY = "society_faculty";


    //---------------------------ADMIN TABLE----------------------------------------------
    private static final String ADMIN_ID = "admin_id";
    private static final String ADMIN_USER_ID = "user_id";


    //---------------------------EVENT TABLE----------------------------------------------
    private static final String EVENT_ID = "event_id";
    private static final String EVENT_SOCIETY_ID = "society_id";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_LOCATION = "event_location";
    private static final String EVENT_DATE = "event_date";
    private static final String EVENT_DESC = "event_desc";
    private static final String EVENT_LINK = "event_link";
    private static final String EVENT_START_TIME = "event_start_time";
    private static final String EVENT_END_TIME = "event_end_time";


    //--------------------------ATTENDS TABLE----------------------------------------------
    private static final String ATTEND_STATUS = "attend_status";
    private static final String ATTEND_EVENT_ID = "event_id";
    private static final String ATTEND_STUDENT_ID = "student_id";


    //-------------------------COMMENT TABLE-----------------------------------------------
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_DATE = "comment_date";
    private static final String COMMENT_TEXT = "comment_text";
    private static final String COMMENT_EVENT_ID = "event_id";
    private static final String COMMENT_USER_ID = "user_id";


    //------------------------FOLLOWS TABLE-------------------------------------------------
    private static final String FOLLOW_STUDENT_ID = "student_id";
    private static final String FOLLOW_SOCIETY_ID = "society_id";


    //------------------------EVENT TYPE TABLE----------------------------------------------
    private static final String EVENT_TYPE_ID = "event_type_id";
    private static final String EVENT_TYPE = "event_type";


    //-----------------------HAS TABLE------------------------------------------------------
    private static final String HAS_EVENT_ID = "event_id";
    private static final String HAS_EVENT_TYPE_ID = "event_type_id";


    private static final String CREATE_TABLE_USER = "create table " + TABLE_USER + " ("
            + USER_ID + " integer(7) primary key autoincrement, "
            + USER_EMAIL + " varchar2 NOT NULL, "
            + USER_PASSWORD + " varchar2 NOT NULL"
            + " constraint password_ck check (password like '%[0-9]%' "
            + " and password like '%[A-Z]%' and len(password) >=8);";


    private static final String CREATE_TABLE_STUDENT = "create table " + TABLE_STUDENT + " ("
            + STUDENT_ID + " varchar primary key NOT NULL, "
            + STUDENT_USER_ID + " integer foreign key references " + TABLE_USER + "(user_id) NOT NULL, "
            + STUDENT_FIRSTNAME + " varchar2(20) NOT NULL, "
            + STUDENT_LASTNAME + " varchar2(20) NOT NULL);";


    private static final String CREATE_TABLE_SOCIETY = "create table " + TABLE_SOCIETY + " ("
            + SOCIETY_ID + " integer(7) primary key autoincrement, "
            + SOCIETY_USER_ID + " integer(7) foreign key references " + TABLE_USER + "(user_id), "
            + SOCIETY_NAME + " varchar2(50) NOT NULL, "
            + SOCIETY_APPROVAL_DATE + " date, "
            + SOCIETY_DESC + " blob, "
            + SOCIETY_FACULTY + " varchar2(25));";


    private static final String CREATE_TABLE_ADMIN = "create table " + TABLE_ADMIN + " ( "
            + ADMIN_ID + " integer(7) primary key autoincrement, "
            + ADMIN_USER_ID + " integer(7) foreign key references " + TABLE_USER + "(user_id)); ";

    private static final String CREATE_TABLE_EVENT = "create table " + TABLE_EVENT + " ( "
            + EVENT_ID + " integer(7) primary key autoincrement, "
            + EVENT_NAME + " varchar2,"
            + EVENT_SOCIETY_ID + " integer(7) foreign key references " + TABLE_SOCIETY + "(society_id),"
            + EVENT_LOCATION + " varchar2,"
            + EVENT_DATE + " date,"
            + EVENT_DESC + " varchar2,"
            + EVENT_LINK + " varchar2,"
            + EVENT_START_TIME + " time,"
            + EVENT_END_TIME + " time);";

    private static final String CREATE_TABLE_EVENT_TYPE = "create table " + TABLE_EVENT_TYPE + " ( "
            + EVENT_TYPE_ID + " integer(7) primary key autoincrement, "
            + EVENT_TYPE + " varchar2);";

    private static final String CREATE_TABLE_FOLLOW_SOCIETY = "create table " + TABLE_FOLLOW_SOCIETY + " ( "
            + FOLLOW_STUDENT_ID + " integer(7) foreign key references " + TABLE_STUDENT + "(student_id), "
            + FOLLOW_SOCIETY_ID + " integer(7) foreign key references " + TABLE_SOCIETY + "(society_id)); ";

    private static final String CREATE_TABLE_HAS_CATEGORY = "create table " + TABLE_HAS_CATEGORY + " ( "
            + HAS_EVENT_ID + " integer(7) foreign key references " + TABLE_EVENT + "(event_id), "
            + HAS_EVENT_TYPE_ID + " integer(7) foreign key references " + TABLE_EVENT_TYPE + "(event_type_id)); ";

    private static final String CREATE_TABLE_ATTENDS = "create table " + TABLE_ATTENDS + " ( "
            + ATTEND_EVENT_ID + " integer(7) foreign key references " + TABLE_EVENT + "(event_id), "
            + ATTEND_STUDENT_ID + " integer(7) foreign key references " + TABLE_STUDENT + "(student_id),"
            + ATTEND_STATUS + " varchar2);";

    private static final String CREATE_TABLE_COMMENT = "create table " + TABLE_COMMENT + " ( "
            + COMMENT_ID + " integer(7) primary key autoincrement, "
            + COMMENT_USER_ID + " integer(7) foreign key references " + TABLE_USER + "(user_id), "
            + COMMENT_EVENT_ID + " integer (7) foreign key references " + TABLE_EVENT + "(event_id)"
            + COMMENT_DATE + " date, "
            + COMMENT_TEXT + " blob);";

    public DatabaseQueries(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //******************DATABASE STUFF STARTS HERE****************************


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the tables in the database
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_SOCIETY);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_EVENT_TYPE);
        db.execSQL(CREATE_TABLE_FOLLOW_SOCIETY);
        db.execSQL(CREATE_TABLE_HAS_CATEGORY);
        db.execSQL(CREATE_TABLE_ATTENDS);
        db.execSQL(CREATE_TABLE_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Insert a new User
     *
     * @param user
     * @return
     */
    public void insertUser(User user) {
        ContentValues cv = new ContentValues();

        cv.put(USER_EMAIL, user.getUserEmail());
        cv.put(USER_PASSWORD, String.valueOf(user.getPassword()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER, null, cv);
        db.close();
    }


    /**
     * Insert a new Student
     *
     * @param student
     * @return
     */
    public void insertStudent(Student student) {
        ContentValues cv = new ContentValues();

        insertUser(student);

        cv.put(STUDENT_ID, student.getStudentID());
        cv.put(STUDENT_USER_ID, student.getUserID());
        cv.put(STUDENT_FIRSTNAME, student.getFirstName());
        ;
        cv.put(STUDENT_LASTNAME, student.getLastName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENT, null, cv);
        db.close();
    }


    /**
     * Insert a new Society
     *
     * @param society
     * @return
     */
    public void insertSociety(Society society) {
        ContentValues cv = new ContentValues();


        insertUser(society);
        cv.put(SOCIETY_USER_ID, society.getUserID());
        cv.put(SOCIETY_NAME, society.getSocietyName());
        cv.put(SOCIETY_DESC, society.getDescription());
        cv.put(SOCIETY_FACULTY, society.getSocietyFaculty());
        cv.put(SOCIETY_APPROVAL_DATE, parser.format(society.getApprovalDate()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SOCIETY, null, cv);
        db.close();
    }


    /**
     * Insert new Admin
     *
     * @param admin
     * @return
     */
    public void insertAdmin(Admin admin) {
        ContentValues cv = new ContentValues();

        insertUser(admin);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ADMIN, null, cv);
        db.close();
    }


    /**
     * Insert a new Event
     *
     * @param event
     * @return
     */
    public void insertEvent(Event event) {
        ContentValues cv = new ContentValues();

        cv.put(EVENT_NAME, event.getEventName());
        cv.put(EVENT_SOCIETY_ID, event.getSocietyID());
        cv.put(EVENT_LOCATION, event.getEventLocation());
        cv.put(EVENT_DATE, parser.format(event.getEventDate()));
        cv.put(EVENT_DESC, event.getEventDescription());
        cv.put(EVENT_LINK, event.getEventLink());
        cv.put(EVENT_START_TIME, parseTime.format(event.getEventStartTime()));
        cv.put(EVENT_END_TIME, parseTime.format(event.getEventEndTime()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EVENT, null, cv);
        db.close();


    }


    /**
     * Insert a new Comment
     *
     * @param comment
     * @return
     */
    public void insertComment(Comment comment) {
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, comment.getUserID());
        cv.put(COMMENT_EVENT_ID, comment.getEventID());
        cv.put(COMMENT_DATE, parseDateTime.format(comment.getCommentDate()));
        cv.put(COMMENT_TEXT, comment.getCommentText());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_COMMENT, null, cv);
        db.close();
    }

    public void updateEmail(int userID, String newEmail) {
        String update = "UPDATE " + TABLE_USER + " set " + USER_EMAIL + " = " + newEmail + " where "
                + USER_ID + " = " + userID;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(update);
        db.close();
    }

    public void updatePassword(int userID, String newPassword) {
        String update = "UPDATE " + TABLE_USER + " set " + USER_PASSWORD + " = " + newPassword + " where "
                + USER_ID + " = " + userID;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(update);
        db.close();
    }


    //------COMPOSITE ENTITY INSERTS-----


    /**
     * When student follows society
     *
     * @return
     */
    public void insertFollows(FollowSociety follows) {
        ContentValues cv = new ContentValues();

        cv.put(FOLLOW_STUDENT_ID, follows.getStudentID());
        cv.put(FOLLOW_SOCIETY_ID, follows.getSocietyID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_FOLLOW_SOCIETY, null, cv);
        db.close();
    }

    /**
     * When society has event types
     *
     * @return
     */
    public void insertHasCategory(HasCategory has) {
        ContentValues cv = new ContentValues();

        cv.put(HAS_EVENT_ID, has.getEventID());
        cv.put(HAS_EVENT_TYPE_ID, has.getEventTypeID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_HAS_CATEGORY, null, cv);
        db.close();

    }


    /**
     * New attend event
     *
     * @param attends
     * @return
     */
    public void insertAttends(Attends attends) {
        ContentValues cv = new ContentValues();

        cv.put(ATTEND_EVENT_ID, attends.getEventID());
        cv.put(ATTEND_STUDENT_ID, attends.getStudentID());
        cv.put(ATTEND_STATUS, attends.getAttendStatus());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ATTENDS, null, cv);
        db.close();
    }


    //------------------------------------DELETE ROWS-----------------------------------------------

    /**
     * Delete a user
     *
     * @param userID
     * @return
     */
    public boolean deleteUser(int userID) {
        boolean result = false;
        String query = "SELECT * from " + TABLE_USER + "where " + USER_ID + " = \""
                + userID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();

        if (cursor.moveToFirst()) {
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USER, USER_ID + " = ?", new String[]{String.valueOf(user.getUserID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean deleteStudent(int studentID) {
        boolean result = false;
        String query = "SELECT * from " + TABLE_STUDENT + " where " + STUDENT_ID + " = \""
                + studentID + "\";";

        String queryFollows = "DELETE FROM " + TABLE_FOLLOW_SOCIETY + " where " + FOLLOW_STUDENT_ID + " = \""
                + studentID + "\";";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();

        if (cursor.moveToFirst()) {
            student.setUserID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENT, STUDENT_ID + " = ?",
                    new String[]{String.valueOf(student.getStudentID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    /**
     * Delete society
     *
     * @param societyID
     * @return
     */
    public boolean deleteSociety(int societyID) {
        boolean result = false;
        String query = "SELECT * from " + TABLE_SOCIETY + " where " + SOCIETY_ID + " = \""
                + societyID + "\";";

        //delete the related Follows row from follow table
        String queryFollows = "DELETE FROM " + TABLE_FOLLOW_SOCIETY + " where " + FOLLOW_SOCIETY_ID + " = \""
                + societyID + "\";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Society society = new Society();

        if (cursor.moveToFirst()) {
            society.setSocietyID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_SOCIETY, SOCIETY_ID + " = ?",
                    new String[]{String.valueOf(society.getSocietyID())});
            cursor.close();
            result = true;

            //execute the follows delete
            db.execSQL(queryFollows);
        }
        db.close();
        return result;

    }


    /**
     * Delete Event
     *
     * @param eventID
     */
    public boolean deleteEvent(int eventID) {
        boolean result = false;
        String query = "SELECT * from " + TABLE_EVENT + " where " + EVENT_ID + " = \""
                + eventID + "\";";


        //SQL statement to delete comments on the event
        String queryComments = "DELETE FROM " + TABLE_COMMENT + " where " + COMMENT_EVENT_ID + " = " + eventID + ";";
        //SQL statement to delete related has category rows
        String queryHasCategory = "DELETE FROM " + TABLE_HAS_CATEGORY + " where " + HAS_EVENT_ID + " = " + eventID + ";";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Event event = new Event();

        if (cursor.moveToFirst()) {
            event.setEventID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_EVENT, EVENT_ID + " = ?",
                    new String[]{String.valueOf(event.getEventID())});
            cursor.close();

            //Also delete all comments and has category on the event
            db.execSQL(queryComments);
            db.execSQL(queryHasCategory);
        }
        db.close();
        return result;
    }


    /**
     * Delete a comment
     *
     * @param commentID
     * @return
     */
    public boolean deleteComment(int commentID) {
        boolean result = false;
        String query = "SELECT * from " + TABLE_COMMENT + " where " + COMMENT_ID + " = " + commentID + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Comment comment = new Comment();

        if (cursor.moveToFirst()) {
            comment.setCommentID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_COMMENT, COMMENT_ID + " = ?",
                    new String[]{String.valueOf(comment.getCommentID())});
            cursor.close();

        }
        db.close();
        return result;
    }


    //------------------------------------FIND ROW--------------------------------------------------


    /**
     * Find a user with userID
     *
     * @param userID
     * @return
     */
    public User getUser(int userID) {
        String query = "SELECT * from " + TABLE_USER + " where " + USER_ID + " = \"" + userID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();


        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setUserEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public User getUser(String userEmail) {
        String query = "SELECT * from " + TABLE_USER + " where " + USER_EMAIL + " = \"" + userEmail + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();


        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setUserEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }


    /**
     * Find a student with studentID
     *
     * @param studentID
     * @return
     */
    public Student getStudent(int studentID) {
        String query = "SELECT * from " + TABLE_STUDENT + " where " + STUDENT_ID + " = \"" + studentID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setStudentID(Integer.parseInt(cursor.getString(0)));
            student.setUserID(Integer.parseInt(cursor.getString(1)));
            student.setFirstName(cursor.getString(2));
            student.setLastName(cursor.getString(3));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }


    /**
     * Find a society
     *
     * @param societyID
     * @return
     * @throws java.text.ParseException
     */
    public Society getSociety(int societyID) throws ParseException {
        String query = "SELECT * from " + TABLE_SOCIETY + " where " + STUDENT_ID + " = \"" + societyID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Society society = new Society();


        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            society.setSocietyID((Integer.parseInt(cursor.getString(0))));
            society.setUserID(Integer.parseInt(cursor.getString(1)));
            society.setSocietyName(cursor.getString(2));

            String myDate = cursor.getString(3);
            society.setApprovalDate(parser.parse(myDate));
            society.setDescription(cursor.getString(4));
            society.setSocietyFaculty(cursor.getString(5));
        } else {
            society = null;
        }
        db.close();
        return society;
    }


    /**
     * Get Event
     *
     * @param eventID
     * @return
     * @throws ParseException
     */
    public Event getEvent(int eventID) throws ParseException {
        String query = "SELECT * from " + TABLE_EVENT + " where " + EVENT_ID + " = \"" + eventID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        Event event = new Event();

        if (c.moveToFirst()) {
            c.moveToFirst();
            event.setEventID(Integer.parseInt(c.getString(0)));
            event.setEventName(c.getString(1));
            event.setSocietyID(Integer.parseInt(c.getString(2)));
            event.setEventLocation(c.getString(3));

            String myDate = c.getString(4);
            event.setEventDate(parser.parse(myDate));
            event.setEventDescription(c.getString(5));
            event.setEventLink(c.getString(6));

            String myStartTime = c.getString(7);
            event.setEventStartTime(parseTime.parse(myStartTime));

            String myEndTime = c.getString(8);
            event.setEventEndTime(parseTime.parse(myEndTime));
        } else {
            event = null;

        }
        db.close();
        return event;
    }


    /**
     * Find a comment
     *
     * @param commentID
     * @return
     * @throws ParseException
     */
    public Comment getComment(int commentID) throws ParseException {
        String query = "SELECT * from " + TABLE_COMMENT + " where " + COMMENT_ID + " = \"" + commentID + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        Comment comment = new Comment();

        if (c.moveToFirst()) {
            c.moveToFirst();
            comment.setCommentID(Integer.parseInt(c.getString(0)));
            comment.setUserID(Integer.parseInt(c.getString(1)));
            comment.setEventID(Integer.parseInt(c.getString(2)));

            String myDate = c.getString(3);
            comment.setCommentDate(parser.parse(myDate));
            comment.setCommentText(c.getString(4));
        } else {
            comment = null;
        }
        db.close();
        return comment;
    }
}