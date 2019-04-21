package in.precisto.precisto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Profile extends Fragment{

    private EditText fname,lname,contact,dob,email,gen,bname,industry,btype;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);

        fname=(EditText)view.findViewById(R.id.et_profile_fname);
        lname=(EditText)view.findViewById(R.id.et_profile_lname);
        contact=(EditText)view.findViewById(R.id.et_profile_contact);
        dob=(EditText)view.findViewById(R.id.et_profile_dob);
        email=(EditText)view.findViewById(R.id.et_profile_email);
        gen=(EditText)view.findViewById(R.id.et_profile_gen);
        bname=(EditText)view.findViewById(R.id.et_profile_business_name);
        industry=(EditText)view.findViewById(R.id.et_profile_industry);
        btype=(EditText)view.findViewById(R.id.et_profile_business_type);

        fname.setEnabled(false);
        lname.setEnabled(false);
        contact.setEnabled(false);
        dob.setEnabled(false);
        email.setEnabled(false);
        gen.setEnabled(false);
        bname.setEnabled(false);
        industry.setEnabled(false);
        btype.setEnabled(false);

        return view;
    }

    public void edit(View view) {

        fname.setEnabled(true);
        lname.setEnabled(true);
        contact.setEnabled(true);
        dob.setEnabled(true);
        email.setEnabled(true);
        gen.setEnabled(true);
        bname.setEnabled(true);
        industry.setEnabled(true);
        btype.setEnabled(true);

    }

}
