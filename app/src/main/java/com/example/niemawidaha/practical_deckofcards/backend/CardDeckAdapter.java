package com.example.niemawidaha.practical_deckofcards.backend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.niemawidaha.practical_deckofcards.R;
import com.example.niemawidaha.practical_deckofcards.model.Card;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardDeckAdapter extends RecyclerView.Adapter<CardDeckAdapter.CardViewHolder>{

    // private members
    private List<Card> cardList = null;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    // constructors:

    public CardDeckAdapter(Context context, List<Card> cardList){

        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.cardList = cardList;
    }
    /**
     * onCreateViewHolder: inflates the layout from the xml:
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view_iv_card, parent, false);
        return new CardViewHolder(view);
    }

    /**
     * onBindViewHolder(): binds the data to the image view and loads images using picasso
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        // load with picasso:
        Picasso.with(context).load(cardList.get(position).getImage()).centerCrop().into(holder.Card_imageView);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    // inner class : CardViewHolder
    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // private members:
        ImageView Card_imageView;
        // constructor:
        public CardViewHolder(View itemView) {
            super(itemView);

            Card_imageView = itemView.findViewById(R.id.iv_user_cards);

            itemView.setOnClickListener(this);

        }

        // onClick:
        @Override
        public void onClick(View view ){
            if( mClickListener != null){
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    } // ends Card View Holder

    /**
     * getItem(): convenient method to get data at click posiiton:
     */
    String getCardCode(int id){
        return cardList.get(id).getCode();
    }
    /**
     * setClickListener()
     * @param itemClickListener
     */
    // allows clicks events to be caught:
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    /**
     * setItemClickListener
     */
    // I: ItemClicklistner parent activity will implement this method to respond to click events:
    public interface ItemClickListener {

        void onItemClick(View view, int position);

    }

} // ends CardDeckAdapter
