package com.example.mega2.FingerPrint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

class Db_Helper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";


    // columns of the User_master table
    private static final String USER_MASTER= "USER_MASTER";
    private static final String USER_MASTER_id = "_id";
    private static final String USER_MASTER_USERID = "UserId";
    private static final String USER_MASTER_NAME = "Name";
    private static final String USER_MASTER_EXPIRY = "Expiry";
    private static final String USER_MASTER_DOB = "Date_of_birth";
    private static final String USER_MASTER_IMAGE = "Image";
    private static final String USER_MASTER_IS_SYNC = "Is_sync";

    // columns of the User_detail table
    private static final String USER_DETAIL = "USER_DETAIL";
    private static final String USER_DETAIL_ID = "_id";
    private static final String USER_DETAIL_USERID = "UserId";
    private static final String USER_DETAIL_HAND = "Hand";
    private static final String USER_DETAIL_FINGER = "Finger";
    private static final String USER_DETAIL_FILE = "File";
    private static final String USER_DETAIL_FINGER_QUALITY = "Finger_quality";
    private static final String USER_DETAIL_IS_SYNC = "Is_Sync";

    // columns of the Punch_data table
    private static final String PUNCH_DATA = "PUNCH_DATA";
    private static final String PUNCH_DATA_ID = "ID";
    private static final String PUNCH_DATA_USERID = "UserId";
    private static final String PUNCH_DATA_DEVICE_INFO = "Device_info";
    private static final String PUNCH_DATA_DATE = "Date";
    private static final String PUNCH_DATA_TIME= "Time";
    private static final String PUNCH_DATA_IS_SYNC = "Is_Sync";
    private static final String PUNCH_DATA_FILE = "FILE";


    private static final String DATABASE_NAME = "Megamind.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the User_detail table creation
    private static final String SQL_CREATE_TABLE_1 = "CREATE TABLE " + USER_MASTER + " (" + USER_MASTER_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_MASTER_USERID + " VARCHAR(20)," + USER_MASTER_NAME + " VARCHAR(50)," + USER_MASTER_EXPIRY + " DATE , " + USER_MASTER_DOB + " VARCHAR(30)  , " + USER_MASTER_IS_SYNC + " VARCHAR(1) , " + USER_MASTER_IMAGE + " VARCHAR(20) )";


    // SQL statement of the User_master table creation
    private static final String SQL_CREATE_TABLE_2 = "CREATE TABLE " + USER_DETAIL + " (" + USER_DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_DETAIL_USERID + " VARCHAR(20)," + USER_DETAIL_HAND + " VARCHAR(2)," + USER_DETAIL_FINGER + " VARCHAR(2)," + USER_DETAIL_FILE + " VARCHAR(50) , " + USER_DETAIL_FINGER_QUALITY + " VARCHAR(2) , "+ USER_DETAIL_IS_SYNC + " VARCHAR(1) )";


    // SQL statement of the Punch_data table creation
    private static final String SQL_CREATE_TABLE_3 = "CREATE TABLE " + PUNCH_DATA + " (" + PUNCH_DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PUNCH_DATA_USERID + " VARCHAR(20)," + PUNCH_DATA_DEVICE_INFO + " VARCHAR(50)," + PUNCH_DATA_DATE + " TEXT," + PUNCH_DATA_TIME + " TEXT," + PUNCH_DATA_FILE + " VARCHAR(50) , " + PUNCH_DATA_IS_SYNC + " VARCHAR(1) )";

    Db_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_1);
        database.execSQL(SQL_CREATE_TABLE_2);
        database.execSQL(SQL_CREATE_TABLE_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + USER_MASTER);
        db.execSQL("DROP TABLE IF EXISTS " + USER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + PUNCH_DATA);

        // recreate the tables
        onCreate(db);
    }

    // functions of User-master table start here
    boolean insert_USER_Data(String UserId, String Name, String date, String Image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_MASTER_USERID, UserId);
        contentValues.put(USER_MASTER_NAME, Name);
        contentValues.put(USER_MASTER_DOB, date);
        contentValues.put(USER_MASTER_IMAGE,Image);
        long result = db.insert(USER_MASTER, null, contentValues);
        return result != -1;
    }
   /* boolean insert_USER_Data_from_csv(String UserId, String Name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_MASTER_USERID, UserId);
        contentValues.put(USER_MASTER_NAME, Name);
        // contentValues.put(USER_MASTER_VERIFYMODE, Verifymode);
        // contentValues.put(USER_MASTER_USERTYPE, Usertype);
        contentValues.put(USER_MASTER_DOB, date);
        long result = db.insert(USER_MASTER, null, contentValues);
        return result != -1;
    }*/

    String getallData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {USER_MASTER_id, USER_MASTER_NAME, USER_MASTER_USERID,USER_MASTER_DOB};
        Cursor cursor = db.query(USER_MASTER, columns, USER_MASTER_USERID + "=?", new String[]{s}, null, null, null);
        StringBuilder buffer = new StringBuilder();
        ArrayList<MyPojo> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String userid = cursor.getString(2);
            String dob=cursor.getString(3);
            MyPojo pojo = new MyPojo();
            pojo.setCid(cid);
            pojo.setName(name);
            pojo.setUserid(userid);
            arrayList.add(pojo);
            buffer.append(userid + "/" + name + "/" +"/" + dob + "\n");
        }
        cursor.close();
        return buffer.toString();
    }

    long modifyDetails(String _id, String UserId, String Name, String Date_of_birth,String filename)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_MASTER_USERID,UserId);
        contentValues.put(USER_MASTER_NAME,Name);
        contentValues.put(USER_MASTER_DOB,Date_of_birth);
        contentValues.put(USER_MASTER_IMAGE,filename);
        return (long) db.update(USER_MASTER,contentValues, USER_MASTER_id + " =? ",new String[]{_id});
    }

    ArrayList<MyPojo> getAllData(String UserID) {
        String querry;
        SQLiteDatabase db = this.getWritableDatabase();
        if (UserID.equals("")) {
            querry = "select um._id,um.userid,name,date_of_birth,count(ud.userid) as FCnt,Image from user_master um left outer join user_detail ud on um.userid=ud.userid group by um._id,um.userid,name,date_of_birth order by um.UserId asc";
        }
        else
        {
            querry = "select um._id,um.userid,name,date_of_birth,count(ud.userid) as FCnt,Image from user_master um left outer join user_detail ud on um.userid=ud.userid Where um.UserId='" + UserID +"' group by um._id,um.userid,name,date_of_birth order by um.UserId asc";
        }
        Cursor cursor=db.rawQuery(querry,null);
        ArrayList<MyPojo> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String userid=cursor.getString(1);
            String name = cursor.getString(2);
            String date=cursor.getString(3);
            int fcnt=cursor.getInt(4);
            String image=cursor.getString(5);
            MyPojo pojo =new MyPojo();
            pojo.setCid(cid);
            pojo.setName(name);
            pojo.setUserid(userid);
            pojo.setDate(date);
            pojo.setFcnt(fcnt);
            pojo.setFile(image);
            arrayList.add(pojo);
        }
        cursor.close();
        return arrayList;
    }

    long deleteDetails_UM(String UserId)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return (long) db.delete(USER_MASTER,USER_MASTER_USERID+"=?",new String[]{UserId});
    }
    long deleteDetails_UD(String UserId)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return (long) db.delete(USER_DETAIL,USER_DETAIL_USERID+"=?",new String[]{UserId});
    }
    long insData(String UserId, String Hand, String Finger, String File, String quality) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_DETAIL_USERID, UserId);
        contentValues.put(USER_DETAIL_HAND, Hand);
        contentValues.put(USER_DETAIL_FINGER, Finger);
        contentValues.put(USER_DETAIL_FILE, File);
        contentValues.put(USER_DETAIL_FINGER_QUALITY, quality);
        return db.insert(USER_DETAIL, null, contentValues);
    }

    StringBuffer getData(String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {USER_DETAIL_ID, USER_DETAIL_USERID,USER_DETAIL_HAND, USER_DETAIL_FINGER, USER_DETAIL_FILE};
        Cursor cursor = db.query(USER_DETAIL, columns, USER_MASTER_USERID + "=?", new String[]{user_id}, null, null, null);
        StringBuffer buffer = new StringBuffer();
        ArrayList<MyPojo> arrayList=new ArrayList<>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String userid = cursor.getString(1);
            String hand = cursor.getString(2);
            String finger = cursor.getString(3);
            String file = cursor.getString(4);
            MyPojo pojo=new MyPojo();
            pojo.setCid(cid);
            pojo.setUserid(userid);
            pojo.setFinger(String.valueOf(finger));
            pojo.setHand(String.valueOf(hand.charAt(0)));
            pojo.setFile(file);
            arrayList.add(pojo);
            if(finger!=null) {
                buffer.append(cid +"/"+ userid +"/" +hand +"/"+ file +"/"+ finger + "\n");
            }else{
                buffer.append(cid + "/" + userid + "/" + hand + "/" + file + "\n");
            }
        }
        cursor.close();
        return buffer;
    }

    MyPojo getUserDatabyDetails(String fileName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String querry="select UD.UserId,UM.NAME, CASE WHEN Hand='L' THEN 'Left Hand' ELSE " +
                "CASE WHEN HAND ='R' THEN 'Right Hand' ELSE 'NO Hand' END END  AS HAND " +
                ",CASE WHEN Finger='T' THEN 'THUMB' WHEN FINGER='I' THEN 'INDEX' WHEN FINGER='M' THEN 'MIDDLE' WHEN FINGER='R' THEN 'RING' WHEN FINGER='L' THEN 'LITTLE'  ELSE 'Finger No -' || FINGER END AS FINGER, Date_Of_Birth as dob , UM.Image  " +
                " from USER_DETAIL UD Inner Join USER_MASTER UM ON UD.USERID=UM.USERID " +
                " where File='" + fileName + "'";
        Cursor cursor=db.rawQuery(querry,null);
        MyPojo pojo = new MyPojo();
        while (cursor.moveToNext()) {
            pojo.setUserid(cursor.getString(0));
            pojo.setName(cursor.getString(1));
            pojo.setHand(cursor.getString(2));
            pojo.setFinger(cursor.getString(3));
            pojo.setFile(cursor.getString(5));
            break;
        }
        cursor.close();
        return pojo;
    }

    Cursor getFinger_data(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String querry="select CASE WHEN Hand='L' THEN 'LEFT' ELSE \n" +
                "CASE WHEN HAND ='R' THEN 'RIGHT' ELSE 'NONE' END END  AS HAND\n" +
                ",CASE WHEN Finger='T' THEN 'THUMB' WHEN FINGER='I' THEN 'INDEX' WHEN FINGER='M' THEN 'MIDDLE' WHEN FINGER='R' THEN 'RING' WHEN FINGER='L' THEN 'LITTLE'  ELSE FINGER END AS FINGER   from USER_DETAIL where UserID='" + uid + "'\n" +
                "ORDER BY HAND,FINGER ";
        return db.rawQuery(querry,null);
    }

    Cursor getfile(String uid){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select file from USER_DETAIL where userid='"+uid+"'",null);
    }


    //functions of user-detail table end here

    //functions of punch-data table start here
    long insertData(String UserId, String Device_info, String Date, String Time, String Is_sync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PUNCH_DATA_USERID, UserId);
        contentValues.put(PUNCH_DATA_DEVICE_INFO, Device_info);
        contentValues.put(PUNCH_DATA_DATE, Date);
        contentValues.put(PUNCH_DATA_TIME, Time);
        contentValues.put(PUNCH_DATA_IS_SYNC, Is_sync);
        return db.insert(PUNCH_DATA, null, contentValues);
    }


    ArrayList<MyPojo> getAllData_report() {
        SQLiteDatabase db = this.getWritableDatabase();
        String querry="Select PD.UserID, UM.Name,PD.Date, PD.Time,PD.IS_Sync From Punch_Data PD Left Outer Join User_Master UM on PD.UserID=UM.UserID order by Date Desc, Time Desc";
        Cursor cursor=db.rawQuery(querry,null);
        ArrayList<MyPojo> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String userid = cursor.getString(0);
            String name=cursor.getString(1);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            String is_sync = cursor.getString(4);
            MyPojo pojo = new MyPojo();
            pojo.setUserid(userid);
            pojo.setName(name);
            pojo.setDate(date);
            pojo.setTime(time);
            pojo.setSync(is_sync);
            arrayList.add(pojo);
        }
        cursor.close();
        return arrayList;
    }

    long updateDb(String UserId, String Date, String Time)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PUNCH_DATA_IS_SYNC,"Y");
        return (long) db.update(PUNCH_DATA,contentValues,PUNCH_DATA_USERID+" =? AND "+PUNCH_DATA_DATE+" =? AND "+PUNCH_DATA_TIME+" =? ",new String[]{UserId,Date,Time});
    }



    //functions of punch-data table end here
}
