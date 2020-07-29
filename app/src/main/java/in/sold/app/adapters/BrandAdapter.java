package in.sold.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.sold.app.R;
import in.sold.app.models.Brand;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Brand> brands;

    public BrandAdapter(ArrayList<Brand> brands) {
        this.brands = brands;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_brand, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Brand brand = brands.get(position);
        holder.textName.setText(brand.getName());
        Picasso.get().load(brand.getImg()).into(holder.imgBrand);

    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBrand;
        private TextView textName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBrand = itemView.findViewById(R.id.imgBrand);
            textName = itemView.findViewById(R.id.textName);
        }
    }

}
