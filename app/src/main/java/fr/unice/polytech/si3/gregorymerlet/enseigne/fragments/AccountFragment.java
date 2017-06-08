package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class AccountFragment extends Fragment{

    private static final String FIRM = "firm";
    private static final String OPEN_ADVANTAGES = "openAdvantages";

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private View rootView;
    private Firm firm;
    private boolean openAdvantages = false;

    public static AccountFragment newInstance(Firm firm) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    public static AccountFragment newInstance(Firm firm, boolean openAdvantages) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        args.putBoolean(OPEN_ADVANTAGES, openAdvantages);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);
        this.openAdvantages = getArguments().getBoolean(OPEN_ADVANTAGES);

        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.accountContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.accountTab);
        tabLayout.setupWithViewPager(mViewPager);

        if(openAdvantages)
            mViewPager.setCurrentItem(1);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AccountInfoFragment.newInstance(firm);
                case 1:
                    return AccountAdvantagesFragment.newInstance(firm);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.account_infos);
                case 1:
                    return getResources().getString(R.string.account_advantages);
            }
            return null;
        }
    }
}
