package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;

public class AccountFragment extends Fragment{

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
