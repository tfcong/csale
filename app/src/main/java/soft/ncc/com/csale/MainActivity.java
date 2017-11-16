package soft.ncc.com.csale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import soft.ncc.com.csale.Model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignIn;
    private TextView tvSignUp;
    private EditText edtName, edtPassWord;
    private static String FILE_NAME = "user.txt";
    private CheckBox checkBoxRememberAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        addControls();
        ReadJSON("http://172.16.200.82/csaleserver/index.php");
    }

    private void addControls() {
        tvSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        restoringPreferences();
        getData();
    }

    private void initViews() {
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvSignUp = findViewById(R.id.tv_sign_up);
        edtName = findViewById(R.id.edt_name);
        edtPassWord = findViewById(R.id.edt_password);
        checkBoxRememberAccount = findViewById(R.id.checkbox_remember_account);
       getSupportActionBar().hide();// an thanh action
    }

    private void ReadJSON(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(), "Connect is success !", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connect is error !", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_up:
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_in:
                savingPreferences();
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void restoringPreferences() {
        SharedPreferences pref = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        boolean chk = pref.getBoolean("isRemember", false);
        if (chk) {
            String name = pref.getString("name", "");
            String password = pref.getString("password", "");
            edtName.setText(name);
            edtPassWord.setText(password);
        }
        checkBoxRememberAccount.setChecked(chk);
    }

    private void savingPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = edtName.getText().toString();
        String password = edtPassWord.getText().toString();
        boolean chk = checkBoxRememberAccount.isChecked();
        if (!chk) editor.clear();
        else {
            editor.putString("name", name);
            editor.putString("password", password);
            editor.putBoolean("isRemember", chk);
        }
        editor.commit();
    }

    private void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            User user = (User) bundle.getSerializable("user");
            edtName.setText(user.getName());
            edtPassWord.setText(user.getPass());
        } catch (Exception e) {
        }
    }
}
