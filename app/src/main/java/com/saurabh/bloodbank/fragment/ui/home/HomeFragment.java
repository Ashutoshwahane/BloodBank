package com.saurabh.bloodbank.fragment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabh.bloodbank.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Bloodbank");
    TextView first_name_textview, last_name_textview;
    Switch donor_switch, reciever_switch;
    Button submit_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        first_name_textview  = root.findViewById(R.id.firstnameEditText);
        last_name_textview = root.findViewById(R.id.lastnameEditText);
        donor_switch = root.findViewById(R.id.donorSwitch);
        reciever_switch = root.findViewById(R.id.recieverSwitch);
        submit_button = root.findViewById(R.id.submit_button);


        submit_button.setOnClickListener(this);



        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        String first_name_txt = first_name_textview.getText().toString();
        String last_name_txt = last_name_textview.getText().toString();
        Boolean donor = donor_switch.isChecked();
        if(donor){
            String donor_txt = "donor";
            myRef.setValue(first_name_txt,last_name_txt);
        }

    }
}
