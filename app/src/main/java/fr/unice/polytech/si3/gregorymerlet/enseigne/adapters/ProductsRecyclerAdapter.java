package fr.unice.polytech.si3.gregorymerlet.enseigne.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.ImagesAsyncTask;
import fr.unice.polytech.si3.gregorymerlet.enseigne.ProductViewHolder;
import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs.ProductDialog;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Product;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    private Context context;
    private Firm firm;
    private List<Product> products;

    public ProductsRecyclerAdapter(Context context, List<Product> products, Firm firm){
        this.context = context;
        this.products = products;
        this.firm = firm;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_adapter, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        String nameString;
        if(products.get(position).getName().length() > 20)
            nameString = products.get(position).getName().subSequence(0,20) + "...";
        else
            nameString = products.get(position).getName();
        holder.name.setText(nameString);

        holder.price.setText(products.get(position).getPrice() + " €");

        ImagesAsyncTask imagesAsyncTask = new ImagesAsyncTask(holder.image);
        imagesAsyncTask.execute(products.get(position).getImageURL());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDialog productDialog = new ProductDialog(context, products.get(position), firm.getShopsForProduct(products.get(position)));
                productDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
