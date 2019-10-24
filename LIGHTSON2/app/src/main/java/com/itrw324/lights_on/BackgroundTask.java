package com.itrw324.lights_on;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;

public class BackgroundTask extends AsyncTask<String,String,String>
{
    Context context;
    BackgroundTask(Context ctx)
    {
        this.context=ctx;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        String type = strings[0];
        String productidtext=strings[1];
        String email =strings[2];
        String name=strings[3] ;
        String password =strings[4];
        String regUrl="http://127.0.0.1/Example/index.php";
        if(type.equals("reg"))
        {
            try
            {
                URL url = new URL(regUrl);
                try
                {
                    HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
                    httpurlconnection.setRequestMethod("POST");
                    httpurlconnection.setDoOutput(true);
                    httpurlconnection.setDoInput(true);
                    OutputStream outputstream = httpurlconnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputstream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String insert_data = URLEncoder.encode("product_id","UTF-8")+"="+URLEncoder.encode(productidtext,"UTF8")+
                            "&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF8")+
                            "&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF8")+
                            "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF8");
                    bufferedWriter.write(insert_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpurlconnection.getInputStream();
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String results ="";
                    String line="";
                    StringBuilder stringBuilder= new StringBuilder();
                    while((line=bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append("\n");
                    }
                    results=stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconnection.disconnect();
                    return  results;

                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected  void onPostExecute(String s)
    {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        //super.onPostExecute(s);
    }
}
