package com.example.moodapp.mood.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.moodapp.R;
import com.example.moodapp.database.MoodRecord;

import java.util.List;

/**
 * mood adapter
 */
public class MoodAdapter extends BaseQuickAdapter<MoodRecord,BaseViewHolder> {
    public MoodAdapter(List<MoodRecord> data) {
        super(R.layout.view_mood, data);
    }
    private int  position= -1;//index

    @Override
    protected void convert(@NonNull BaseViewHolder holder, MoodRecord moodBean) {
        holder.setText(R.id.mood_tag,moodBean.mood);//mood
        int icon = 0;//define icon
        String mood = moodBean.mood;
        switch (mood) {
            case "Happy":
                icon = R.drawable.smile;
                break;
            case "Excited":
                icon = R.drawable.smiley;
                break;
            case "Relax":
                icon = R.drawable.cool;
                break;
            case "Celebrate":
                icon = R.drawable.party;
                break;
            case "Sad":
                icon = R.drawable.sad;
                break;
            case "Angry":
                icon = R.drawable.angry;
                break;
            case "Bored":
                icon = R.drawable.confused;
                break;
            case "Hurt":
                icon = R.drawable.hurt;
                break;
        }
        holder.setImageResource(R.id.mood_icon,icon);//emoji

        //white backgroud
        if (position == getItemPosition(moodBean)){
            holder.setBackgroundResource(R.id.mood_icon,R.drawable.shape_r8_white);
        }else {
            holder.setBackgroundResource(R.id.mood_icon,0);
        }
    }

    public void setPosition(int position){
        this.position = position;
    }

}
