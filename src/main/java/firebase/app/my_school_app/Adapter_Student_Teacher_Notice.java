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

public class Adapter_Student_Teacher_Notice extends RecyclerView.Adapter<Adapter_Student_Teacher_Notice.ViewHolder>  {

    Context context;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();

    public Adapter_Student_Teacher_Notice(Context context, ArrayList<String> mSubject, ArrayList<String> mText, ArrayList<String> mKey) {
        this.context = context;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mKey = mKey;
    }

    @NonNull
    @Override
    public Adapter_Student_Teacher_Notice.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studentnotice,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.tv_homesubject.setText(mSubject.get(i));
        viewHolder.tv_homedis.setText(mText.get(i));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,Show_Full_NoticeActivity.class);
                intent.putExtra("key",mKey.get(i));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
