package com.muhammadyaseen.classifiedapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forget_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forget_Fragment extends Fragment {
    NavController navController;
    EditText email_forget;
    Button sendEmail_forget;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private Pattern Email_Pattern = Pattern.compile( "^(.+)@(.+)$");


    private boolean validate_email() {
        String emailInput = email_forget.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email_forget.setError("Field can't be empty");
            return false;

        }
        else if(Email_Pattern.matcher(emailInput).matches()){
            email_forget.setError(null);
            return true;
        }else{
            email_forget.setError("Valid Email");
            return false;
        }

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public forget_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forget_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static forget_Fragment newInstance(String param1, String param2) {
        forget_Fragment fragment = new forget_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        firebaseAuth= FirebaseAuth.getInstance();
        email_forget=view.findViewById(R.id.email_forget);
        sendEmail_forget=view.findViewById(R.id.sendEmail_forget);
        progressDialog=new ProgressDialog(getContext());
        sendEmail_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate_email()){
                    progressDialog.setMessage("Check your Email");
                    progressDialog.show();
                    String User_email=email_forget.getText().toString();
                    progressDialog.setMessage(" Check your Register Email ");
                    progressDialog.show();
                    sendEmail_forget.setEnabled(false);

                    firebaseAuth.sendPasswordResetEmail(User_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Check your Email and Login with New Passwords", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_forget_Fragment_to_garageSale_login_fragment);


                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Register with valid Email", Toast.LENGTH_SHORT).show();
                                sendEmail_forget.setEnabled(true);
                            }
                        }
                    });





                }else{
                    progressDialog.dismiss();
                }


            }
        });


    }
}