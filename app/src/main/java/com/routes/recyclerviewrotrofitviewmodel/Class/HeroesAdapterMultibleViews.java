package com.routes.recyclerviewrotrofitviewmodel.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.routes.recyclerviewrotrofitviewmodel.Model.Hero;
import com.routes.recyclerviewrotrofitviewmodel.R;

import java.util.List;
import java.util.Random;

public class HeroesAdapterMultibleViews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_1 = 1;
    private static final int ITEM_TYPE_2 = 2;


    Context mCtx;
    List<Hero> heroList;


    private OnItemClickListener listener;


    public HeroesAdapterMultibleViews(Context mCtx, List<Hero> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        if (viewType == ITEM_TYPE_1) {
            View normalView = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout_1,parent, false);
            return new HeroViewHolder1(normalView); // view holder for normal items
        } else if (viewType == ITEM_TYPE_2) {
            View headerRow = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout_2, parent, false);
            return new HeroViewHolder2(headerRow); // view holder for header items
        }else {
            View normalView = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout_1, parent, false);
            return new HeroViewHolder1(normalView); // view holder for normal items
        }

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hero hero = heroList.get(position);

        //Hero hero = heroList.get(new Random().nextInt(heroList.size()));

        final int itemType = getItemViewType(position);
    //    final int itemType = getItemViewType(hero.getFirstappearance());

        if (itemType == ITEM_TYPE_1) {

            HeroViewHolder1 heroViewHolder1 = (HeroViewHolder1) holder;
           // imageViewHolder.mImage.setImageResource(...);

              //Glide.with(mCtx).load(hero.getImageurl()).into(heroViewHolder1.imageView);

             RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.progress_animation).error(R.drawable.loading_image).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).dontAnimate().dontTransform();

            Glide.with(mCtx).load(hero.getImageurl()).apply(options).into(heroViewHolder1.imageView);


            heroViewHolder1.textView.setText(hero.getName());

           // ((HeroViewHolder1)holder).bindData((Hero)heroList[position]);
        }

        else if (itemType == ITEM_TYPE_2) {

            HeroViewHolder2 heroViewHolder2 = (HeroViewHolder2) holder;
            // imageViewHolder.mImage.setImageResource(...);

           // Glide.with(mCtx).load(hero.getImageurl()).into(heroViewHolder2.imageView2);


            RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.progress_animation).error(R.drawable.loading_image).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).dontAnimate().dontTransform();

            Glide.with(mCtx).load(hero.getImageurl()).apply(options).into(heroViewHolder2.imageView2);


            heroViewHolder2.textView2_name.setText(hero.getName());
            heroViewHolder2.textView2_team.setText(hero.getTeam());
            heroViewHolder2.textView2_firstappearance.setText(hero.getFirstappearance());


            //((HeroViewHolder2)holder).setHeaderText((String)myData[position]);
        }

      //  Glide.with(mCtx).load(hero.getImageurl()).into(holder.imageView);

       // holder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }


@Override
    public int getItemViewType(int position) {

    Hero hero = heroList.get(position);


    //Hero hero = heroList.get(new Random().nextInt(heroList.size()));

           // if (position == 3) {

        if (hero.getName().equals("Spiderman")){
            return ITEM_TYPE_2;
        } else {
            return ITEM_TYPE_1;
        }
    }


















    class HeroViewHolder1 extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public HeroViewHolder1(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);




            //handel OnClickListener on Item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    // we put this condition because when we delete any item this take position[-1] ...... {RecyclerView.NO_POSITION = -1}
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onItemClick(position);

                    }
                }
            });

        }
    }






    class HeroViewHolder2 extends RecyclerView.ViewHolder {

        ImageView imageView2;
        TextView textView2_name,textView2_team,textView2_firstappearance;

        public HeroViewHolder2(View itemView) {
            super(itemView);

            imageView2 = itemView.findViewById(R.id.imageView2);
            textView2_name = itemView.findViewById(R.id.textView2_name);
            textView2_team = itemView.findViewById(R.id.textView2_team);
            textView2_firstappearance = itemView.findViewById(R.id.textView2_firstappearance);



            //handel OnClickListener on Item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    // we put this condition because when we delete any item this take position[-1] ...... {RecyclerView.NO_POSITION = -1}
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onItemClick(position);

                    }
                }
            });

        }
    }







    //onClick Interface to use [onClick method] in GasStationsAdmin Fragment
    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


}
