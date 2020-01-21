package com.beerquiz.beerq;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.beerquiz.beerq.QuizConst.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +

                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Ile powinna wynosić optymalna temperatura spożywanego piwa? ",
                "5-9 st.C", "8-12 st.C", "15-18 st.C", 2);
        addQuestion(q1);
        Question q2 = new Question("Piwo powinno być podawane z pianką, gdyż pozwala ona utrzymać walory" +
                " smakowe i zapachowe. Jak nazywa się formowanie odpowiedniej piany podczas nalewania piwa?",
                "Czopkowanie", "Czapowanie", "Spienianie", 2);
        addQuestion(q2);
        Question q3 = new Question("Kto produkuje najmocniejsze piwa? ",
                "Niemcy", "Czesi", "Brytyjczycy", 3);
        addQuestion(q3);
        Question q4 = new Question("Ile trwa produkcja piwa w nowoczesnym browarze?",
                "3 dni", "3 tygodnie", "3 miesiące", 2);
        addQuestion(q4);
        Question q5 = new Question("Litr soku owocowego ma 460 kcal, litr pełnotłustego mleka - 650 kcal," +
                " a ile kalorii ma litr piwa? ",
                "470", "680", "950", 1);
        addQuestion(q5);

        Question q6 = new Question("Jak zostało nazwane pierwsze znane piwo?",
                "Sikar", "Bock", "Zythum", 1);
        addQuestion(q6);

        Question q7 = new Question("Gdzie znajduje się najstarszy działający browar? ",
                "Heineken w Zoeterwoude", "Browar Śląski w Lwówku Śląskim",
                "Weihenstephan w Monachium", 3);
        addQuestion(q7);

        Question q8 = new Question("Co jest podstawowym charakterystycznym składnikiem piwa Tosa Kuroshio? ",
                "Wiórki bonito, czyli ryby podobne do tuńczyka", "Mleko, które nadaje piwu słodki smak",
                "Woda z lodowca i wodorosty", 1);
        addQuestion(q8);

        Question q9 = new Question("Piwo jest doskonałe dla wegetarian, gdyż uzupełnia zapotrzebowanie na znajdującą się " +
                "głównie w produktach mięsnych witaminę: ",
                "A", "C", "B12", 3);
        addQuestion(q9);

        Question q10 = new Question("Ile czasu zajęło rekordziście Stevenowi Petrosinowi wypicie litra piwa?",
                "1,3s", "2,3s", "5,1s", 1);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}