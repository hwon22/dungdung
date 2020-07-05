package com.example.dungdung;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest { //정보를 보내서 회원가입 시키는 요청 보냄

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://hwon0128s.cafe24.com/UserValidate.php";
    private Map<String,String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송

        parameters = new HashMap<>();
        parameters.put("userID",userID);
    }

    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
