package com.example.pr14_03_2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;


public class SendCommon extends AsyncTask<Void, Void, Void> {

    public String Url = "http://192.168.0.109:5000/api/CommonController/Send", Code;
    public EditText TbEmail;
    CallbackResponse CallbackResponse, CallbackError;

    public SendCommon(EditText tbEmail, CallbackResponse callbackResponse, CallbackResponse callbackError) {
        this.TbEmail = tbEmail;
        this.CallbackResponse = callbackResponse;
        this.CallbackError = callbackError;
    }

    @Override
    protected Void doInBackground(Void... Voids){
        try{
            Document Response = Jsoup.connect(Url + "?Email="+TbEmail.getText())
                    .ignoreContentType(true)
                    .get();
            Code = Response.text();
        } catch (IOException e) {
            Log.e("Errors", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        if(Code == null) CallbackError.returner("Error");
        else CallbackResponse.returner(Code);
    }

}
