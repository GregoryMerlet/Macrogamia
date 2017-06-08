package fr.unice.polytech.si3.gregorymerlet.enseigne.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Advantage;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class AdvantagesAdapter extends ArrayAdapter<Advantage>{

    private Firm firm;

    public AdvantagesAdapter(Context context, List<Advantage> advantages, Firm firm){
        super(context, 0, advantages);
        this.firm = firm;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.advantage_adapter, null);

        if(firm.getActualUser().getFideltyPoints() < getItem(position).getCost())
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.disabledCardView));
        else
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.enabledCardView));

        TextView text = (TextView)convertView.findViewById(R.id.advantageAdapterText);
        text.setText(getItem(position).getText());

        TextView hours = (TextView)convertView.findViewById(R.id.advantageAdapterCost);
        hours.setText(String.valueOf(getItem(position).getCost()));

        return convertView;
    }
}
