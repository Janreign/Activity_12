package ph.kodego.aragon.janreign.activity_12.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASENAME, null,DATABASEVERSION) {

    companion object {
        private val DATABASEVERSION = 8
        private val DATABASENAME = "studentdatabase"

        val tableStudents = "student_table"
        val studentId = "id"
        val studentFirstName = "firstname"
        val studentlastName = "lastname"
        var yearstarted = "year_started"
        var course = "course"

        //added January 31,2023
        val tableContacts = "student_contacts"
        val contactID = "id"
        val studentcontactID = "student_id"
        val contactType = "contact_type"
        val contactDetails = "contact_details"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATESTUDENTSTABLE =
            "CREATE TABLE $tableStudents " +
                    "($studentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$studentFirstName TEXT, " +
                    "$studentlastName TEXT, " +
                    "$yearstarted INTEGER, " +
                    "$course TEXT)"
        db?.execSQL(CREATESTUDENTSTABLE)

        val CREATECONTACTSTABLE =
            "CREATE TABLE $tableContacts " +
                    "($contactID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$studentcontactID TEXT, " +
                    "$contactType TEXT, " +
                    "$contactDetails TEXT)"
//                    " FOREIGN KEY ($studentcontactID) REFERENCES $tableStudents ($studentId))"
        db?.execSQL(CREATECONTACTSTABLE)

        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Valmores', 'Marco', 2020, 'Mobile Development')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Dave', 'Navor', 2022, 'Web Development')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Victor', 'Yu', 2019, 'Web Development')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('JP', 'Soriano', 2022, 'Graphic Design')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('CJ', 'Tronco', 2023, 'Graphic Design')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Rene', 'Marco', 2023, 'Graphic Design')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Joni', 'James', 2019, 'Web Development')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Janreign', 'Aragon', 2023, 'Graphic Design')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Patricia', 'Leones', 2022, 'Mobile Development')")
        db?.execSQL("Insert into $tableStudents ($studentlastName, $studentFirstName, $yearstarted, $course) values ('Matthew', 'Mottos', 2021, 'Mobile Development')")

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        onCreate(db)
    }
}