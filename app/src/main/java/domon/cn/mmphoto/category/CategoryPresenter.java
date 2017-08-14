package domon.cn.mmphoto.category;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.Category;
import domon.cn.mmphoto.data.PhotoData;

/**
 * Created by Domon on 2017/8/9.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;

    public CategoryPresenter(CategoryContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestCategory() {
        OkGo.<Category>get(Const.REQ_CATEGORY_WITH_TYPE+"1")
                .execute(new JsonCallback<Category>() {
                    @Override
                    public void onSuccess(Response<Category> response) {
                        List<PhotoData> atlasList = null;
                        Category result = response.body();
                        if (result != null) {
                            List<Category.AtlasBean> list = result.getAtlas();
                            if (list != null && list.size() > 0) {
                                for (Category.AtlasBean item : list) {
                                    if (item.getType() == 1) {
                                        atlasList = item.getAtlasList();
                                    }
                                }
                            }
                        }
                        mView.dataSuccess(atlasList);
                    }
                });
    }
}
