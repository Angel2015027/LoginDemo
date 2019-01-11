package proyecto.ejemplo.mundo.hola.rodriguez.angel.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText v_Agent;
    private EditText v_Pass;
    private TextView v_Error;
    private Button v_btnLogin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_Agent = (EditText) findViewById(R.id.txAgent);
        v_Pass = (EditText) findViewById(R.id.txPass);
        v_Error = (TextView) findViewById(R.id.txError);
        v_btnLogin = (Button)findViewById(R.id.btnLogin);

         sp = getSharedPreferences("PruebaLogin", Context.MODE_PRIVATE);

        v_Error.setVisibility(View.INVISIBLE);


        SharedPreferences sp1 = this.getSharedPreferences("PruebaLogin", Context.MODE_PRIVATE);

        String aCode = sp1.getString("AgentCode", null);
        String aPass = sp1.getString("AgentPwd", null);


        if(aCode != null && aPass != null){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }

        v_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v_Agent.getText().toString().isEmpty()){
                    v_Error.setVisibility(v.VISIBLE);
                    v_Error.setText("Por favor ingresar codigo de agente");

                } else if(v_Pass.getText().toString().isEmpty()){
                    v_Error.setVisibility(v.VISIBLE);
                    v_Error.setText("Por favor ingresar contrasena");

                } else {
                    validate(v_Agent.getText().toString(), v_Pass.getText().toString());
                }
                //validate(v_Agent.getText().toString(), v_Pass.getText().toString());
            }
        });

    }

    private void validate(String agentCode, String agentPass){

            if(agentCode.equals("Admin") && agentPass.equals("1234")){

                SharedPreferences.Editor edit = sp.edit();
                edit.putString("AgentCode", agentCode);
                edit.putString("AgentPwd", agentPass);
                edit.apply();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            } else {
                 v_Error.setVisibility(View.VISIBLE);
                 v_Error.setText("Usuario y/o Contrasena invalido");
            }
    }
}
