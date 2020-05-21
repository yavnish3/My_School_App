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

public class Adapter_St_Msg extends RecyclerView.Adapter<Adapter_St_Msg.ViewHolder> {

    Context context;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mTime=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mTeacherKey=new ArrayList<String>();
    String clas;

    public Adapter_St_Msg(Context context, ArrayList<String> mSubject, ArrayList<String> mText, ArrayList<String> mTime, ArrayList<String> mKey, ArrayList<String> mTeacherKey,String clas) {
        this.context = context;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mTime = mTime;
        this.mKey = mKey;
        this.mTeacherKey = mTeacherKey;
        this.clas=clas;
    }

    @NonNull
    @Override
    public Adapter_St_Msg.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classplan,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_St_Msg.ViewHolder viewHolder, final int i) {

        viewHolder.tv_title.setText(mSubject.get(i));
        viewHolder.tv_dis.setText(mText.get(i));
        viewHolder.time.setText(mTime.get(i));
        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Update_St_Message.class);
                intent.putExtra("key",mKey.get(i));
                intent.putExtra("class",clas);
                intent.putExtra("studentkey",mTeacherKey.get(i));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title,tv_dis,time;
        public CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);
            tv_dis=(TextView)itemView.findViewById(R.id.tv_dis);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }
}
