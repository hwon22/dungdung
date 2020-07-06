package com.example.dungdung;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateAddRequest extends StringRequest { //정보를 보내서 회원가입 시키는 요청 보냄

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://hwon0128s.cafe24.com/BookAdd.php";
    private Map<String,String> parameters;

    public ValidateAddRequest(String bookName,String bookPart, String bookDo, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송

        parameters = new HashMap<>();
        parameters.put("bookName",bookName);
        parameters.put("bookPart",bookPart);
        parameters.put("bookDo",bookDo);
    }

    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
