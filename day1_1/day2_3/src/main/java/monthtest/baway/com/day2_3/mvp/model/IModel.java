package monthtest.baway.com.day2_3.mvp.model;

public interface IModel {
    void httpGet(String url, CallBack callBack);

    interface CallBack {
        void onSuccess(Object objs);

        void onFail(Object objf);

    }
}
