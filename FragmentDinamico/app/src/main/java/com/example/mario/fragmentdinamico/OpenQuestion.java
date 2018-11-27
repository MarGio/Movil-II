package com.example.mario.fragmentdinamico;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OpenQuestion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OpenQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenQuestion extends QuestionFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "question";
    private static final String ARG_PARAM2 = "answer";

    // TODO: Rename and change types of parameters
    private Question  question;
    private Answer answer;

    private OnFragmentInteractionListener mListener;

    public OpenQuestion() {
        // Required empty public constructor
    }
    EditText answera;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment OpenQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenQuestion newInstance(Question question, Answer answer) {
        OpenQuestion fragment = new OpenQuestion();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, question);
        args.putSerializable(ARG_PARAM2, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(ARG_PARAM1);
            answer = (Answer) getArguments().getSerializable(ARG_PARAM2);
        }
    }
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if (getArguments() != null) {
            View view = inflater.inflate(R.layout.fragment_open_question, container, false);
            editText = (EditText) view.findViewById(R.id.txtAnswer);
            String respuestaPrevia = answer.getAnswer();
            editText.setText(respuestaPrevia);
//        }
        //inicializar componentes
        //crear objetos de clases de question and Answers
        //dar el texto a question con el objeto
        //acciones de botones
        //guardar la respuesta

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private Activity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity=(Activity) context;
            //mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getAnswer() {
        return null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
