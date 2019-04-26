package in.precisto.precisto;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends Fragment {

    EditText fname, lname, contact, dob, email, gen, bname, industry, btype;
    Button edit, save;
    public static Profile ptemp = new Profile();
    DatabaseReference mProfileReference;
    ProgressDialog mProfileDialog;

    UserInfo userInfo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfileDialog = new ProgressDialog(getContext());

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //mProfileReference = FirebaseDatabase.getInstance().getReference().child("P_user").child("uid");

        mProfileReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("ProfileInfo");


        ptemp.fname = (EditText) view.findViewById(R.id.et_profile_fname);
        ptemp.lname = (EditText) view.findViewById(R.id.et_profile_lname);
        ptemp.contact = (EditText) view.findViewById(R.id.et_profile_contact);
        ptemp.dob = (EditText) view.findViewById(R.id.et_profile_dob);
        ptemp.email = (EditText) view.findViewById(R.id.et_profile_email);
        ptemp.gen = (EditText) view.findViewById(R.id.et_profile_gen);
        ptemp.bname = (EditText) view.findViewById(R.id.et_profile_business_name);
        ptemp.industry = (EditText) view.findViewById(R.id.et_profile_industry);
        ptemp.btype = (EditText) view.findViewById(R.id.et_profile_business_type);
        ptemp.edit = (Button) view.findViewById(R.id.btn_profile_edit);
        ptemp.save = (Button) view.findViewById(R.id.btn_profile_save);

        ptemp.fname.setEnabled(false);
        ptemp.lname.setEnabled(false);
        ptemp.contact.setEnabled(false);
        ptemp.dob.setEnabled(false);
        ptemp.email.setEnabled(false);
        ptemp.gen.setEnabled(false);
        ptemp.bname.setEnabled(false);
        ptemp.industry.setEnabled(false);
        ptemp.btype.setEnabled(false);

        mProfileDialog.setTitle("Loading details");
        mProfileDialog.setMessage("Please wait while we fetch your profile information");
        mProfileDialog.setCanceledOnTouchOutside(false);
        mProfileDialog.show();


        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mProfileDialog.dismiss();

                userInfo = dataSnapshot.getValue(UserInfo.class);
                ptemp.fname.setText(userInfo.getFirstName());
                ptemp.lname.setText(userInfo.getLastName());
                ptemp.contact.setText(userInfo.getContact());
                ptemp.dob.setText(userInfo.getDob());
                ptemp.email.setText(userInfo.getEmail());
                ptemp.gen.setText(userInfo.getGender());
                ptemp.bname.setText(userInfo.getBusinessName());
                ptemp.industry.setText(userInfo.getIndustry());
                ptemp.btype.setText(userInfo.getBusinessType());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                mProfileDialog.dismiss();

                Toast.makeText(getContext(), "Failed to load details", Toast.LENGTH_SHORT).show();
            }
        };
        mProfileReference.addValueEventListener(profileListener);

        ptemp.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ptemp.fname.setEnabled(true);
                ptemp.lname.setEnabled(true);
                ptemp.contact.setEnabled(true);
                ptemp.dob.setEnabled(true);
                ptemp.email.setEnabled(true);
                ptemp.gen.setEnabled(true);
                ptemp.bname.setEnabled(true);
                ptemp.industry.setEnabled(true);
                ptemp.btype.setEnabled(true);
                ptemp.save.setVisibility(View.VISIBLE);
                ptemp.edit.setVisibility(View.INVISIBLE);

                MainActivity.flag = true;
            }
        });

        ptemp.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ptemp.fname.setEnabled(false);
                ptemp.lname.setEnabled(false);
                ptemp.contact.setEnabled(false);
                ptemp.dob.setEnabled(false);
                ptemp.email.setEnabled(false);
                ptemp.gen.setEnabled(false);
                ptemp.bname.setEnabled(false);
                ptemp.industry.setEnabled(false);
                ptemp.btype.setEnabled(false);

                ptemp.save.setVisibility(View.INVISIBLE);
                ptemp.edit.setVisibility(View.VISIBLE);

                mProfileDialog.setTitle("Saving Information");
                mProfileDialog.setCanceledOnTouchOutside(false);
                mProfileDialog.show();

                userInfo.setFirstName(ptemp.fname.getText().toString());
                userInfo.setLastName(ptemp.lname.getText().toString());
                userInfo.setContact(ptemp.contact.getText().toString());
                userInfo.setDob(ptemp.dob.getText().toString());
                userInfo.setEmail(ptemp.email.getText().toString());
                userInfo.setGender(ptemp.gen.getText().toString());
                userInfo.setBusinessName(ptemp.bname.getText().toString());
                userInfo.setIndustry(ptemp.industry.getText().toString());
                userInfo.setBusinessType(ptemp.btype.getText().toString());

                mProfileReference.setValue(userInfo);

                mProfileDialog.dismiss();

            }
        });


        return view;
    }
}
