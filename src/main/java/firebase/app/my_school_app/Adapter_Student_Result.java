package firebase.app.my_school_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_Student_Result extends RecyclerView.Adapter<Adapter_Student_Result.ViewHolder> {

    Context context;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mMrkOb=new ArrayList<String>();
    ArrayList<String> mTlMrk=new ArrayList<String>();
    ArrayList<String> mTime=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mTeacherKey=new ArrayList<String>();
    String clas;

    public Adapter_Student_Result(Context context, ArrayList<String> mSubject, ArrayList<String> mText, ArrayList<String> mMrkOb, ArrayList<String> mTlMrk, ArrayList<String> mTime, ArrayList<String> mKey, ArrayList<String> mTeacherKey, String clas) {
        this.context = context;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mMrkOb = mMrkOb;
        this.mTlMrk = mTlMrk;
        this.mTime = mTime;
        this.mKey = mKey;
        this.mTeacherKey = mTeacherKey;
        this.clas = clas;
    }

    @NonNull
    @Override
    public Adapter_Student_Result.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.result,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Student_Result.ViewHolder viewHolder, int i) {

        viewHolder.tv_tsty.setText(mSubject.get(i));
        viewHolder.tv_sub.setText(mText.get(i));
        viewHolder.tv_mrkob.setText(mMrkOb.get(i));
        viewHolder.tv_tlmrk.setText(mTlMrk.get(i));
        viewHolder.time.setText(mTime.get(i));
    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tsty,tv_sub,tv_mrkob,tv_tlmrk,time;
        public CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
            tv_tsty=(TextView)itemView.findViewById(R.id.tv_tsty);
            tv_sub=(TextView)itemView.findViewById(R.id.tv_sub);
            tv_mrkob=(TextView)itemView.findViewById(R.id.tv_mrkob);
            tv_tlmrk=(TextView)itemView.findViewById(R.id.tv_tlmrk);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }
}
