package com.mxx.myimmatationdemo.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mxx.myimmatationdemo.bean.TVBean;
import com.mxx.myimmatationdemo.bean.TechnologyBean;
import com.mxx.myimmatationdemo.bean.TvTaiModel;
import com.mxx.myimmatationdemo.myinterface.RequestCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mxx.myimmatationdemo.R.layout.a;


/**
 * Created by 98179 on 2019/6/28.
 */

public class RequestMethod {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void getData(final int mPage, final RequestCallBack callBack){
        final List<TechnologyBean> technologyBeanList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    String url = "http://www.jcodecraeer.com/essence/index_1_"+mPage+".html";
                    doc = Jsoup.connect(url).get();
                    Elements content_elements = doc.select("ul.archive-list>li.archive-item.clearfix");
                    Log.e("==1==", "-----------"+content_elements.size());
                    Log.e("==1==",technologyBeanList.size()+"        i="+mPage);
                    for (int j = 0; j < content_elements.size(); j++) {
                        TechnologyBean technologyBean = new TechnologyBean();
                        technologyBean.setUrl("http://www.jcodecraeer.com"+content_elements.get(j).select("a").attr("href"));
                        technologyBean.setImgUrl("http://www.jcodecraeer.com"+content_elements.get(j).select("div>img").attr("src"));
                        technologyBean.setTitle(content_elements.get(j).select("h3").text());
                        technologyBean.setContent(content_elements.get(j).select("p").text());
                        technologyBeanList.add(technologyBean);
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(technologyBeanList);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public  static  void getHomeData(final String url, final RequestCallBack callBack){
        final List<TechnologyBean> technologyBeanList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                    Elements content_elements = doc.select("div.list_con");
                    Elements titles_elements = doc.select("div.title");
                    Elements elements  = doc.select("div.summary.oneline");
                    for (int j = 0; j < content_elements.size(); j++) {
                        TechnologyBean technologyBean = new TechnologyBean();
                        technologyBean.setUrl(titles_elements.get(j).select("h2>a").attr("href"));
                        technologyBean.setTitle(titles_elements.get(j).select("h2>a").text());
                        technologyBean.setImgUrl(content_elements.get(j).select("dl.list_userbar>dt>a>img").attr("src"));
                        technologyBean.setNameUrl(content_elements.get(j).select("dl.list_userbar>dt>a").attr("href"));
                        technologyBean.setTime(content_elements.get(j).select("dd.time").text());
                        technologyBean.setName(content_elements.get(j).select("dd.name>a").text());
                        technologyBean.setContent(elements.get(j).text());
                        technologyBean.setReadNum(content_elements.get(j).select("div.interactive.floatR>dd.read_num>a>span.num").text());
                        technologyBean.setAppraiseNum(content_elements.get(j).select("div.interactive.floatR>dd.common_num >a>span.num").text());
                        Log.e("getHomeData",technologyBean.toString());
                        technologyBeanList.add(technologyBean);
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(technologyBeanList);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void get(String url, final RequestCallBack callBack){
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                ToastUtil.showToast(GetActivity.this, "Get 失败");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.success(responseStr);
                            }
                        });
                    }
                });
            }
        });
    }


    //获取视频
    public  static void getVideoData(final String url, final RequestCallBack callBack){
         final List<TvTaiModel> listData = new ArrayList<>();

      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  Document doc = Jsoup.connect(url).get();
                  Elements mv_list_vertical = doc.select("div.list_mov_poster.img-responsive");
                  Log.e("mv_list_vertical.size",mv_list_vertical.size()+"");
                  Elements mv_list_title = doc.select("div.list_mov_title");
                  for (int j = 0; j < mv_list_vertical.size(); j++) {
                      TvTaiModel yinYueTaiModel = new TvTaiModel();
                      yinYueTaiModel.setHref("http://www.27k.cc"+mv_list_vertical.get(j).select("a").attr("href"));
                      yinYueTaiModel.setImg(mv_list_vertical.get(j).select("a>img").attr("src"));
                      yinYueTaiModel.setSeries(mv_list_vertical.get(j).select("span.corner.version.version4").text());
                      yinYueTaiModel.setGrade(mv_list_vertical.get(j).select("span.corner.score").text());
                      yinYueTaiModel.setTitle(mv_list_title.get(j).select("h4>a").text());
                      Log.e("yinYueTaiModel",yinYueTaiModel.toString());
                      listData.add(yinYueTaiModel);
                  }

                  mHandler.post(new Runnable() {
                      @Override
                      public void run() {
                          callBack.success(listData);
                      }
                  });
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      }).start();
    }


    //获取视频
    public  static void getMovieData(final String url, final RequestCallBack callBack){
        final List<TvTaiModel> listData = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements mv_list_vertical = doc.select("div.list_mov_poster.img-responsive");
                    Log.e("mv_list_vertical.size",mv_list_vertical.size()+"");
                    Elements mv_list_title = doc.select("div.list_mov_title");
                    for (int j = 0; j < mv_list_vertical.size(); j++) {
                        TvTaiModel yinYueTaiModel = new TvTaiModel();
                        yinYueTaiModel.setHref("http://www.27k.cc"+mv_list_vertical.get(j).select("a").attr("href"));
                        yinYueTaiModel.setImg(mv_list_vertical.get(j).select("a>img").attr("src"));
                        yinYueTaiModel.setSeries(mv_list_vertical.get(j).select("span.corner.version.version4").text());
                        yinYueTaiModel.setGrade(mv_list_vertical.get(j).select("span.corner.score").text());
                        yinYueTaiModel.setTitle(mv_list_title.get(j).select("h4>a").text());
                        Log.e("yinYueTaiModel",yinYueTaiModel.toString());
                        listData.add(yinYueTaiModel);
                    }

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(listData);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //获取视频
    public  static void getTvData(final String url, final RequestCallBack callBack){
        final List<TVBean> listData = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements mv_list_vertical = doc.select("div.videourl.clearfix>ul>li");
                    Log.e("mv_list_vertical.size",mv_list_vertical.size()+"");
                    for (int j = 0; j < mv_list_vertical.size()/2; j++) {
                        TVBean tvBean = new TVBean();
                        tvBean.setTitle(mv_list_vertical.get(j).select("li>a").text());
                        tvBean.setUrl("http://www.27k.cc"+mv_list_vertical.get(j).select("li>a").attr("href"));
                        Log.e("tvbean",tvBean.toString());
                        listData.add(tvBean);
                    }

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(listData);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
