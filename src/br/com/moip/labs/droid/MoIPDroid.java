package br.com.moip.labs.droid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MoIPDroid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView (this);
        
        HttpClient client = new HttpClient();
        
        
        
        PostMethod post = new PostMethod("https://desenvolvedor.moip.com.br/sandbox/ws/alpha/EnviarInstrucao/Unica");
        
        String token = "GTGP7V7RGO5K4ZJBO2BCTMYAUYEAINA1";
        String key = "5IAQEXFKCELWQYUY0HKDNHMU0VCK7HGJHRUHQ58Y";
        
        
        String authHeader = token + ":" + key;
        String encoded = String.valueOf(org.apache.axis.encoding.Base64.encode(authHeader.getBytes()));
        post.setRequestHeader("Authorization", " Basic " + encoded);
        post.setDoAuthentication( true );
        
        String xml = "<EnviarInstrucao><InstrucaoUnica><Razao>Testes</Razao><IdProprio></IdProprio><Valores><Valor moeda='BRL'>150.00</Valor></Valores></InstrucaoUnica></EnviarInstrucao>";

        try {
			RequestEntity entity = new StringRequestEntity(xml,"application/x-www-formurlencoded","UTF-8");
			post.setRequestEntity(entity);
			int status = client.executeMethod(post);
			tv.setText(status+ " - "+post.getResponseBodyAsString());
			post.releaseConnection();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			tv.setText(e.getMessage());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			tv.setText(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tv.setText(e.getMessage());
		}
        tv.setText(tv.getText()+"Hello from MoIPLabs");
        setContentView(tv);
    }
}