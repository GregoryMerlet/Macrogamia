package fr.unice.polytech.si3.gregorymerlet.enseigne;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductViewHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView price;
    public ImageView image;

    public ProductViewHolder(View view){
        super(view);
        this.name = (TextView)view.findViewById(R.id.productName);
        this.price = (TextView)view.findViewById(R.id.productPrice);
        this.image = (ImageView) view.findViewById(R.id.productImage);
    }
}
