package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class AccountFragment extends Fragment{

    private static final String FIRM = "firm";

    private View rootView;
    private Firm firm;

    public static AccountFragment newInstance(Firm firm) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);

        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

    }
}
