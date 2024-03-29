package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class AccountInfoFragment extends Fragment{

    private static final String FIRM = "firm";

    private View rootView;
    private Firm firm;

    public static AccountInfoFragment newInstance(Firm firm) {
        AccountInfoFragment fragment = new AccountInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);

        rootView = inflater.inflate(R.layout.fragment_account_infos, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        TextView firstName = (TextView) rootView.findViewById(R.id.accountFirstName);
        firstName.setText(firm.getActualUser().getFirstName());

        TextView lastName = (TextView) rootView.findViewById(R.id.accountLastName);
        lastName.setText(firm.getActualUser().getLastName());

        TextView mail = (TextView) rootView.findViewById(R.id.accountMail);
        mail.setText(firm.getActualUser().getMail());

        final SharedPreferences notifPreferences = getContext().getSharedPreferences("notifPrefs", Context.MODE_PRIVATE);;
        final SharedPreferences.Editor notifPrefsEditor = notifPreferences.edit();

        ToggleButton toggleButton = (ToggleButton) rootView.findViewById(R.id.accountNotificationsToggleButton);

        toggleButton.setChecked(notifPreferences.getBoolean("notifications", true));

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notifPrefsEditor.putBoolean("notifications", isChecked);
                notifPrefsEditor.commit();
            }
        });
    }
}
