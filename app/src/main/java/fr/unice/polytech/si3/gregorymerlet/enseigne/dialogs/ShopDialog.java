package fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import fr.unice.polytech.si3.gregorymerlet.enseigne.OpenHoursAdapter;
import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Shop;

public class ShopDialog extends Dialog {

    public ShopDialog(@NonNull Context context, Shop shop) {
        super(context);
        this.setContentView(R.layout.shop_dialog);
        this.setTitle(shop.getName());

        TextView name = (TextView) findViewById(R.id.shopDialogName);
        name.setText(shop.getName());

        TextView adress = (TextView) findViewById(R.id.shopDialogAdress);
        adress.setText(shop.getAdress());

        ListView shopListView = (ListView) findViewById(R.id.shopDialogHoursList);
        OpenHoursAdapter openHoursAdapter = new OpenHoursAdapter(context, shop.getOpenHours());
        shopListView.setAdapter(openHoursAdapter);

        ImageButton dialogButton = (ImageButton) findViewById(R.id.shopDialogButtonClose);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDialog.this.dismiss();
            }
        });
    }
}
