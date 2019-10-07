package com.example.contactv1;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class CustomAdapter  extends BaseAdapter {
    private List<ContactData> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CustomAdapter(Context aContext, List<ContactData> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {

        return listData.size();
    }
    @Override
    public Object getItem(int position) {

        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {

        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.contact_avatar);
            holder.nameContact = (TextView) convertView.findViewById(R.id.name_contact);
            holder.imgCall=(ImageView)convertView.findViewById(R.id.btn_call);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContactData contactData = this.listData.get(position);
        holder.nameContact.setText(contactData.getContactName());
        int imageId = this.getMipmapResIdByName(contactData.getAvatar());
        holder.avatar.setImageResource(imageId);
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",listData.get(position).getPhoneNumber(),null));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView avatar;
        TextView nameContact;
        ImageView imgCall;
    }
}