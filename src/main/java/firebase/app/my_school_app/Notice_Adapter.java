package firebase.app.my_school_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Notice_Adapter extends RecyclerView.Adapter<Notice_Adapter.ViewHolder> {


    Context context;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();

    public Notice_Adapter(Context context, ArrayList<String> mSubject, ArrayList<String> mText, ArrayList<String> mKey) {
        this.context = context;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mKey = mKey;
    }



    @NonNull
    @Override
    public Notice_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studentnotice,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notice_Adapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_homesubject.setText(mSubject.get(i));
        viewHolder.tv_homedis.setText(mText.get(i));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,UpdateNotice.class);
                intent.putExtra("key",mKey.get(i));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tv_homesubject,tv_homedis;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.SHWCardLay);
            tv_homedis=(TextView)itemView.findViewById(R.id.tv_homedis);
            tv_homesubject=(TextView)itemView.findViewById(R.id.tv_homesubject);
        }
    }

}
