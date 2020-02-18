package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

public class Statistics extends AppCompatActivity {

    private TextView txtNumberClients;
    private TextView txtNumberEmployees;
    private TextView txtNumberAdmins;


    private int countClients;
    private int countEmployees;
    private int countAdmins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        init();
    }

    private void init() {
        countClients = 0;
        countEmployees = 0;
        countAdmins = 0;
        txtNumberClients = findViewById(R.id.set_number_clients);
        txtNumberEmployees = findViewById(R.id.set_number_employees);
        txtNumberAdmins = findViewById(R.id.set_number_Admins);
        for (int i=0; i < Manager.getPersonList().size(); i++){
            if( Manager.getPersonList().get(i).getTypeUser().equals("Cliente")){
                countClients++;
            }else if( Manager.getPersonList().get(i).getTypeUser().equals("Empleado")){
                countEmployees++;
            }else if( Manager.getPersonList().get(i).getTypeUser().equals("Administrador")){
                countAdmins++;
            }
        }
        txtNumberClients.setText("" + countClients);
        txtNumberEmployees.setText("" + countEmployees);
        txtNumberAdmins.setText("" + countAdmins);
    }
}
