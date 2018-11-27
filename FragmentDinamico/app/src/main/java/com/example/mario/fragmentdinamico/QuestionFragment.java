package com.example.mario.fragmentdinamico;

import android.support.v4.app.Fragment;

public abstract class QuestionFragment extends Fragment {
    private String question;

    public String getQuestion() {
        return question;
    }
    public abstract String getAnswer();

    public static class QuestionFragmentBuilder {
        private Question question;
        private Answer answer;
        private QuestionFragment QUESTIONS;

        public QuestionFragmentBuilder(Question question, Answer answer) {
            this.question = question;
            this.answer=answer;
        }

        public QuestionFragment build() {
//            if (question.getData().getType()== QuestionData.QuestionType.OPEN) {
                QuestionFragment questionFragment = OpenQuestion.newInstance(question, answer);
                QUESTIONS=questionFragment;
//            }
//            if (question.getData().getType()== QuestionData.QuestionType.SINGLE_CHOICE) {
//                QuestionFragment questionFragment = OpenQuestion.newInstance(question, answer);
//                QUESTIONS=questionFragment;
//            }
//            if (question.getData().getType()== QuestionData.QuestionType.TRUE_FALSE) {
//                QuestionFragment questionFragment = OpenQuestion.newInstance(question, answer);
//                QUESTIONS=questionFragment;
//            }
            return QUESTIONS;
        }
    }

}

