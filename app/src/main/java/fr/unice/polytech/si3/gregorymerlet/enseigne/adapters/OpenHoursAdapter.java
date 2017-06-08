package fr.unice.polytech.si3.gregorymerlet.enseigne.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;

public class OpenHoursAdapter extends ArrayAdapter{

    public OpenHoursAdapter(Context context, List<String> openHours){
        super(context, 0, openHours);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.open_hour_adapter, null);

        List<String> week = new ArrayList<>();
        week.add("Lundi");
        week.add("Mardi");
        week.add("Mercredi");
        week.add("Jeudi");
        week.add("Vendredi");
        week.add("Samedi");
        week.add("Dimanche");

        TextView day = (TextView)convertView.findViewById(R.id.openHourAdapterDay);
        day.setText(week.get(position));

        TextView hours = (TextView)convertView.findViewById(R.id.openHourAdapterHours);
        hours.setText(getItem(position).toString());

        return convertView;
    }
}
