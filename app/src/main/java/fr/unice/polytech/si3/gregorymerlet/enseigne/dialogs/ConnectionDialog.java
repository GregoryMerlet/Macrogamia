package fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.User;

public class ConnectionDialog extends Dialog {
    public ConnectionDialog(@NonNull final Context context, final Firm firm) {
        super(context);
        this.setContentView(R.layout.connection_dialog);
        this.setTitle(context.getResources().getString(R.string.connection));

        final EditText mail = (EditText) findViewById(R.id.connectionDialogMail);
        final EditText password = (EditText) findViewById(R.id.connectionDialogPassword);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.connectionDialogRemember);
        final TextView error = (TextView) findViewById(R.id.connectionDialogError);

        final Button connection = (Button) findViewById(R.id.connectionDialogButton);
        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = firm.getUser(mail.getText().toString());
                if(user == null) {
                    error.setText(context.getResources().getString(R.string.connectionErrorMail));
                    error.setVisibility(View.VISIBLE);
                } else {
                    boolean result = firm.connect(user, password.getText().toString());
                    if(!result){
                        error.setText(context.getResources().getString(R.string.connectionErrorPassword));
                        error.setVisibility(View.VISIBLE);
                    } else {
                        SharedPreferences loginPreferences = context.getSharedPreferences("loginPrefs", context.MODE_PRIVATE);
                        SharedPreferences.Editor loginPrefsEditor = loginPreferences.edit();
                        if(checkBox.isChecked()){
                            loginPrefsEditor.putBoolean("savedLogin", true);
                            loginPrefsEditor.putString("mail", mail.getText().toString());
                            loginPrefsEditor.putString("password", password.getText().toString());
                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }
                        ConnectionDialog.this.dismiss();
                    }
                }
            }
        });
    }
}
