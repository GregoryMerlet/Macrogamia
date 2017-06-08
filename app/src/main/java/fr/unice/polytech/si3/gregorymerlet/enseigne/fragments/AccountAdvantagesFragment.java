package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.adapters.AdvantagesAdapter;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class AccountAdvantagesFragment extends Fragment{

    private static final String FIRM = "firm";

    private View rootView;
    private Firm firm;

    public static AccountAdvantagesFragment newInstance(Firm firm) {
        AccountAdvantagesFragment fragment = new AccountAdvantagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);

        rootView = inflater.inflate(R.layout.fragment_account_advantages, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        TextView fidelityPoints = (TextView) rootView.findViewById(R.id.accountAdvantagesFidelityPoints);
        fidelityPoints.setText(String.valueOf(firm.getActualUser().getFideltyPoints()));

        ListView listView = (ListView) rootView.findViewById(R.id.accountAdvantagesList);
        AdvantagesAdapter advantagesAdapter = new AdvantagesAdapter(getContext(), firm.getAdvantages(), firm);
        listView.setAdapter(advantagesAdapter);
    }
}
