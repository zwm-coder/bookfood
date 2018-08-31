package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.FeedBack;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;

public class FeedBackService {

    public static boolean handleFeedback(int feedback_id, String result){

        FeedBack feedBack = null;

        try {
            feedBack = DaoFactory.getFeedBackDaoInstance().findById(feedback_id);
            if (feedBack == null){
                return false;
            } else {
                feedBack.setResult(result);
                feedBack.setStatus(1);
                if (!DaoFactory.getFeedBackDaoInstance().doChange(feedBack)){
                    return false;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

}
