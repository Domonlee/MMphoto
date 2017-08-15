package domon.cn.mmphoto.category;

import java.util.List;

import domon.cn.mmphoto.base.BasePresenter;
import domon.cn.mmphoto.base.BaseView;
import domon.cn.mmphoto.data.PhotoData;

/**
 * Created by Domon on 2017/8/9.
 */

public interface CategoryContract {


    interface View extends BaseView<Presenter> {

        void dataSuccess(List<PhotoData> list);
    }

    interface Presenter extends BasePresenter {
       void requestCategory();
    }
}
