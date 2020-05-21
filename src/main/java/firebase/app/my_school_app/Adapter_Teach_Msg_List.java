package firebase.app.my_school_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter_Teach_Msg_List extends RecyclerView.Adapter<Adapter_Teach_Msg_List.ViewHolder> {

    Context context;
    ArrayList<String> mName=new ArrayList<String>();
    ArrayList<String> mDp=new ArrayList<String>();
    ArrayList<String> mFname=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mmobile=new ArrayList<String>();

    public Adapter_Teach_Msg_List(Context context, ArrayList<String> mName, ArrayList<String> mDp, ArrayList<String> mFname, ArrayList<String> mKey, ArrayList<String> mmobile) {
        this.context = context;
        this.mName = mName;
        this.mDp = mDp;
        this.mFname = mFname;
        this.mKey = mKey;
        this.mmobile = mmobile;
    }

    @NonNull
    @Override
    public Adapter_Teach_Msg_List.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lists,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Teach_Msg_List.ViewHolder viewHolder, final int i) {

        viewHolder.tv_listname.setText(mName.get(i));
        viewHolder.tv_listfather.setText(mFname.get(i));
        viewHolder.tv_listmather.setText(mmobile.get(i));
        viewHolder.tv_listname.setText(mName.get(i));
        viewHolder.tv_listfather.setText(mFname.get(i));
        viewHolder.tv_listmather.setText(mmobile.get(i));
        Glide.with(context).load(mDp.get(i)).asBitmap().into(viewHolder.iv_listphoto);

        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Send_Teacher_Msg.class);
                intent.putExtra("key",mKey.get(i));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_listphoto;
        public TextView tv_listname,tv_listfather,tv_listmather;
        CardView cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_listfather=(TextView)itemView.findViewById(R.id.tv_listfather);
            iv_listphoto=(ImageView)itemView.findViewById(R.id.iv_listphoto);
            tv_listname=(TextView)itemView.findViewById(R.id.tv_listname);
            tv_listmather=(TextView)itemView.findViewById(R.id.tv_listmather);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
