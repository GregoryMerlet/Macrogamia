package fr.unice.polytech.si3.gregorymerlet.enseigne.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.ImagesAsyncTask;
import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs.ProductDialog;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Product;

public class ProductsAdapter extends ArrayAdapter<Product> {

    private Firm firm;

    public ProductsAdapter(Context context, List<Product> productList, Firm firm){
        super(context, 0, productList);
        this.firm = firm;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.product_adapter, null);

        TextView productName = (TextView)convertView.findViewById(R.id.productName);
        String nameString;
        if(getItem(position).getName().length() > 20)
            nameString = getItem(position).getName().subSequence(0,20) + "...";
        else
            nameString = getItem(position).getName();
        productName.setText(nameString);

        TextView productPrice = (TextView)convertView.findViewById(R.id.productPrice);
        productPrice.setText(getItem(position).getPrice() + " €");

        ImageView image = (ImageView)convertView.findViewById(R.id.productImage);
        ImagesAsyncTask imagesAsyncTask = new ImagesAsyncTask(image);
        imagesAsyncTask.execute(getItem(position).getImageURL());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDialog productDialog = new ProductDialog(getContext(), getItem(position), firm.getShopsForProduct(getItem(position)));
                productDialog.show();
            }
        });

        return convertView;
    }
}
