package com.example.dungdung;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUpActivity extends Activity{

    static final String PREFS_NAME="LoginPrefs";

    private static final String DATABASE_NAME="user.db";
    private static final int DATABASE_VERSION=1;
    private static final String USER_TABLE_NAME="user";

    private SQLiteDatabase db;
    private UserHelper userHelper;
    private EditText idname;
    private EditText pass;
    private EditText  passCheck;
    private EditText email;
    private EditText partOne;
    private EditText partTwo;
    private EditText partThree;
    private Button signUp;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);

        userHelper=new UserHelper(this);
        db=userHelper.getWritableDatabase();

        preProcess();

        idname=(EditText)findViewById(R.id.id);
        pass=(EditText)findViewById(R.id.pass);
        passCheck=(EditText)findViewById(R.id.passCh);
        email=(EditText)findViewById(R.id.email);
        partOne=(EditText)findViewById(R.id.partOne);
        partTwo=(EditText)findViewById(R.id.partTwo);
        partThree=(EditText)findViewById(R.id.partThree);

        result=(TextView)findViewById(R.id.resultLabel);

        signUp = (Button) findViewById(R.id.buttonSignup);
        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String inputId = idname.getText().toString();
                String inputPw = pass.getText().toString();
                String inputPwc = passCheck.getText().toString();
                String inputemail = email.getText().toString();
                String inputpartOne = partOne.getText().toString();
                String inputpartTwo = partTwo.getText().toString();
                String inputpartThree =partThree.getText().toString();

                if(inputId.length()>0 && inputPw.length()>0 && inputPwc.length()>0 && inputemail.length()>0 &&
                inputpartOne.length()>0 && inputpartTwo.length()>0 && inputpartThree.length()>0) {
                        if(inputPw.equals(inputPwc)) {
                                String dbData[] = selectProcess(inputId);
                                if (!inputId.equals(dbData[0])) { //중복된 아이디 걸러내고 회원가입 진행
                                    String q = "INSERT INTO "+USER_TABLE_NAME+"(ID,PASS,EMAIL,PARTONE,PARTTWO,PARTTHREE) VALUES ("+"'"+inputId+"',"+"'"+inputPw+"',"+
                                            "'"+inputemail+"',"+"'"+inputpartOne+"',"+"'"+inputpartTwo+"',"+"'"+inputpartThree+"');";
                                    db.execSQL(q);
                                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("id", inputId);
                                    editor.putString("pass", inputPw);
                                    editor.putString("email", inputemail);
                                    editor.putString("partOne", inputpartOne);
                                    editor.putString("partTwo", inputPw);
                                    editor.putString("partThree", inputPw);
                                    editor.commit();

                                    showToast(SignUpActivity.this, "회원가입에 성공했습니다.");
                                    result.setText("아이디:" + inputId + "\n" + "비밀번호:" + inputPw+ "\n"+ "이메일:" + inputemail+ "\n"+ "part1:" + inputpartOne+ "\n"+ "part2:" + inputpartTwo+ "\n"+ "part3:" + inputpartThree);
                                    clearFields();
                                   //myStartActivity(SignInActivity.class);
                                    //finish();
                                } else {
                                    result.setText("이미 존재하는 아이디 입니다.");
                                    clearFields();
                                }
                        }else{
                            result.setText("비밀번호 확인을 해주세요.");
                            showToast(SignUpActivity.this, "다시 시도해주세요.");
                        }
                }else {
                    result.setText("빈칸을 모두 채워주세요.");
                    showToast(SignUpActivity.this, "다시 시도해주세요.");
                }
            }
        });
    }

    public void preProcess(){
        ContentValues values = new ContentValues();
        values.put("id","abcd");
        values.put("pass","1234");
        values.put("email","abcd@gmail.com");
        values.put("partOne","지리");
        values.put("partTwo","운동");
        values.put("partThree","교양");
        db.insert(USER_TABLE_NAME,"",values);
    }


    public String[] selectProcess(String inputId){
        String rString[]=new String[2];
        String columns[]={"id","pass"};
        String selection="id = '"+inputId+"'";
        Cursor c = db.query(USER_TABLE_NAME,columns,selection,null,null,null,null);
        if(c.getCount()>0){
            c.moveToFirst();
            rString[0]=c.getString(0);
            rString[1]=c.getString(1);
        }
        return rString;
    }

    public void clearFields(){
        idname.setText("");
        pass.setText("");
        passCheck.setText("");

        idname.requestFocus();
    }

    private static class UserHelper extends SQLiteOpenHelper{
        public UserHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db){
            String query = "CREATE TABLE "+USER_TABLE_NAME+"(ID TEXT, PASS TEXT, EMAIL TEXT, PARTONE TEXT, PARTTWO TEXT, PARTTHREE TEXT);";
            db.execSQL(query);
        }
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE_NAME);
            onCreate(db);
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}
