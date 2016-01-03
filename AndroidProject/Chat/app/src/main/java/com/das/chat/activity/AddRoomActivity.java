package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.das.chat.R;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.AddRoomRequest;

public class AddRoomActivity extends Activity {

    private EditText nombreSala;
    private EditText descSala;
    private EditText tipoSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        nombreSala = (EditText) findViewById(R.id.nombre_sala_et);
        descSala = (EditText) findViewById(R.id.desc_sala_et);
        tipoSala = (EditText) findViewById(R.id.tipo_sala_et);
    }

    public void onAddRoomPressed(View v)
    {
        if(nombreSala.getText().toString().isEmpty() || descSala.getText().toString().isEmpty() || tipoSala.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AddRoomRequest req = new AddRoomRequest();
        req.setNombreSala(nombreSala.getText().toString());
        req.setDescSala(descSala.getText().toString());
        req.setTipoSala(tipoSala.getText().toString());

        Backend.getInstance().addRoom(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    AddRoomActivity.this.finish();
                } else {
                    Toast.makeText(AddRoomActivity.this, "Error al agregar sala", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
