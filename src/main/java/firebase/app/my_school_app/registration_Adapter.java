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
import android.widget.Toast;

import java.util.ArrayList;

public class registration_Adapter extends RecyclerView.Adapter<registration_Adapter.ViewHolder> {


    Context context;
    ArrayList<String> mName=new ArrayList<>();
    ArrayList<String> mClass=new ArrayList<>();
    ArrayList<String> mMobile=new ArrayList<>();
    ArrayList<String> mKey=new ArrayList<>();

    public registration_Adapter(Context context, ArrayList<String> mName, ArrayList<String> mClass, ArrayList<String> mMobile, ArrayList<String> mKey) {
        this.context = context;
        this.mName = mName;
        this.mClass = mClass;
        this.mMobile = mMobile;
        this.mKey = mKey;
    }

    @NonNull
    @Override
    public registration_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_new_admission,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull registration_Adapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_name.setText(mName.get(i));
        viewHolder.tv_class.setText(mClass.get(i));
        viewHolder.tv_mobile.setText(mMobile.get(i));
        viewHolder.adcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,New_Admission_ViewActivity.class);
                intent.putExtra("key",mKey.get(i));
                context.startActivity(intent);
                Toast.makeText(context, "hello!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_class,tv_mobile;
        CardView adcardview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            adcardview=(CardView)itemView.findViewById(R.id.adcardview);
            tv_class=(TextView)itemView.findViewById(R.id.tv_class);
            tv_mobile=(TextView)itemView.findViewById(R.id.tv_mobile);

        }
    }
}
