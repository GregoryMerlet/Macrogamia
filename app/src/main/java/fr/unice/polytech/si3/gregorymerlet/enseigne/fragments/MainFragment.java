package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import fr.unice.polytech.si3.gregorymerlet.enseigne.PromoAdapter;
import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class MainFragment extends Fragment {

    private static final String FIRM = "firm";

    private View rootView;
    private Firm firm;
    private Timer timer;
    private boolean normalCarouselTurn = true;

    public static MainFragment newInstance(Firm firm) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        ViewPager promos = (ViewPager) rootView.findViewById(R.id.homeViewPager);
        PromoAdapter promoAdapter = new PromoAdapter(this.getContext());
        promos.setAdapter(promoAdapter);

        timer = new Timer();
        timer.schedule(new UpdateTimeTask(), 10000, 10000);

        final NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

        CardView productCard = (CardView) rootView.findViewById(R.id.homeProductCard);
        productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                navigationView.getMenu().findItem(R.id.nav_products).setChecked(true);
                fragmentManager.beginTransaction().replace(R.id.flContent, ProductsFragment.newInstance(firm)).commit();
            }
        });

        CardView mapCard = (CardView) rootView.findViewById(R.id.homeMapCard);
        mapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                navigationView.getMenu().findItem(R.id.nav_map).setChecked(true);
                fragmentManager.beginTransaction().replace(R.id.flContent, MapFragment.newInstance(firm)).commit();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private class UpdateTimeTask extends TimerTask {
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ViewPager promos = (ViewPager) rootView.findViewById(R.id.homeViewPager);
                    int nextItem = promos.getCurrentItem();
                    if(normalCarouselTurn)
                        nextItem++;
                    else
                        nextItem--;
                    if(nextItem < 0){
                        nextItem += 2;
                        normalCarouselTurn = true;
                    }
                    if (nextItem >= promos.getAdapter().getCount()) {
                        nextItem -= 2;
                        normalCarouselTurn = false;
                    }
                    promos.setCurrentItem(nextItem, true);
                }
            });
        }
    }
}
