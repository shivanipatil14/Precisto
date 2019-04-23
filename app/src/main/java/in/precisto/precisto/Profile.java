package in.precisto.precisto;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Profile extends Fragment {

    private EditText fname, lname, contact, dob, email, gen, bname, industry, btype;
    private Button edit,save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fname = (EditText) view.findViewById(R.id.et_profile_fname);
        lname = (EditText) view.findViewById(R.id.et_profile_lname);
        contact = (EditText) view.findViewById(R.id.et_profile_contact);
        dob = (EditText) view.findViewById(R.id.et_profile_dob);
        email = (EditText) view.findViewById(R.id.et_profile_email);
        gen = (EditText) view.findViewById(R.id.et_profile_gen);
        bname = (EditText) view.findViewById(R.id.et_profile_business_name);
        industry = (EditText) view.findViewById(R.id.et_profile_industry);
        btype = (EditText) view.findViewById(R.id.et_profile_business_type);
        edit = (Button) view.findViewById(R.id.btn_profile_edit);
        save = (Button) view.findViewById(R.id.btn_profile_save);

        fname.setEnabled(false);
        lname.setEnabled(false);
        contact.setEnabled(false);
        dob.setEnabled(false);
        email.setEnabled(false);
        gen.setEnabled(false);
        bname.setEnabled(false);
        industry.setEnabled(false);
        btype.setEnabled(false);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname.setEnabled(true);
                lname.setEnabled(true);
                contact.setEnabled(true);
                dob.setEnabled(true);
                email.setEnabled(true);
                gen.setEnabled(true);
                bname.setEnabled(true);
                industry.setEnabled(true);
                btype.setEnabled(true);
                save.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("Do you want to save changes?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getContext(), "Profile updated!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });


        return view;
    }
}
