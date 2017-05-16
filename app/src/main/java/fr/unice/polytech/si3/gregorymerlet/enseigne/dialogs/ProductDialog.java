package fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Product;

public class ProductDialog extends Dialog {

    public ProductDialog(@NonNull Context context, Product product, List<String> shopList) {
        super(context);
        this.setContentView(R.layout.product_dialog);
        this.setTitle(product.getName());

        TextView name = (TextView) findViewById(R.id.productDialogName);
        name.setText(product.getName());

        TextView description = (TextView) findViewById(R.id.productDialogDescription);
        description.setText(product.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());

        TextView price = (TextView) findViewById(R.id.productDialogPrice);
        price.setText(product.getPrice() + " €");

        ImageView image = (ImageView) findViewById(R.id.productDialogImage);
        image.setImageResource(product.getImageId());

        ListView shopListView = (ListView) findViewById(R.id.productDialogShopList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, shopList );
        shopListView.setAdapter(arrayAdapter);

        ImageButton dialogButton = (ImageButton) findViewById(R.id.productDialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDialog.this.dismiss();
            }
        });
    }
}
