package me.manishmahalwal.android.fms2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class WorkComplaintStatusStudentAdapter extends RecyclerView.Adapter<WorkComplaintStatusStudentAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<WorkComplaintStatusStudent> productList;

    //getting the context and product list with constructor
    public WorkComplaintStatusStudentAdapter(Context mCtx, List<WorkComplaintStatusStudent> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.objcomplaintstatusadmin, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        WorkComplaintStatusStudent product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.id);
        holder.textViewShortDesc.setText("Name: " + product.name + "     Phone: "+product.phoneNumber);
        if(product.assignedStatus.equals("true"))
            holder.textViewRating.setText("Not Available");
        else
            holder.textViewRating.setText("Available");
        holder.textViewPrice.setText("");
        holder.worker.setText(product.type);


    }
    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice, worker;
//        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            worker = itemView.findViewById(R.id.worker);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
