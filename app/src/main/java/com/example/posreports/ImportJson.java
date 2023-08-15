package com.example.posreports;

import static android.content.Context.MODE_PRIVATE;
import static com.example.posreports.MainActivity.CONO_PREF;
import static com.example.posreports.MainActivity.IP_PREF;
import static com.example.posreports.MainActivity.IP_SETTINGS;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.posreports.Model.CaptionItemInfo;
import com.example.posreports.Model.EmployModel;
import com.example.posreports.Model.GetCaptionModel;
import com.example.posreports.Model.POSModel;
import com.example.posreports.Model.TimeReortModel;
import com.example.posreports.Model.TransactionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ImportJson {

    private Context context;
    SweetAlertDialog pd;
    String I1,I2,I3,I4,I5,I6,I7,I8,I9,I10,I11,I12,I13,I14,ITEMOCODEs;

    String ip, CoNo;
    String FDate, TDate, FTime, TTime, empNo, chk1, chk2, chk3, chk4, posNo;
    String CONO, dDate1, dDate2, iKind, bGroup, bSub1, bSub2, bSub3, bSub4, bSub5, bSub6, bSub7, sGroup, sSub1, sSub2,
            sSub3,
            sSub4,
            sSub5,
            sSub6,
            sSub7,
            sUserNo,
            sGrp,
            sUnit,
            sKind,
            sItemCode,
            sLoc,
            sModal,
            sSubGrp,
            sDiv,
            PosNo,
            bByPrc,
            FrmPrc,
            ToPrc,
            bSoldByDifPrc,
            iPriceK,
            OFFERNO,
            OFFERNAME,
            BISKIT,
            SALESNO,
            POSNOSTR;

    public ImportJson(Context context) {//, JSONObject obj
//        this.obj = obj;
        this.context = context;
        SharedPreferences sharedPref = context.getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);
        ip = sharedPref.getString(IP_PREF, "");
        CoNo = sharedPref.getString(CONO_PREF, "");

//        if (is != 0) {

//        }

    }
    public void getDataCap( String I1,String I2,String I3,String I4,String I5,String I6,String I7,String I8,String I9,String I10,String I11,String I12,String I13,String I14 ,String ITEMOCODEs){

//        final List<MainSetting> mainSettings = dbHandler.getAllMainSetting();
//        if (mainSettings.size() != 0) {
//            this.ip = mainSettings.get(0).getIP();
//            this.isAssetsIn = mainSettings.get(0).getIsAssest();
//            this.QrUse = mainSettings.get(0).getIsQr();
//            this.onlinePrice=mainSettings.get(0).getOnlinePrice();
//            this.CompanyNo=mainSettings.get(0).getCompanyNo();
//            this.coName=mainSettings.get(0).getCoName();
//        }


        this.ITEMOCODEs=ITEMOCODEs;
        this.I1=I1;
        this.I2=I2;
        this.I3=I3;
        this.I4=I4;
        this.I5=I5;
        this.I6=I6;
        this.I7=I7;
        this.I8=I8;
        this.I9=I9;
        this.I10=I10;
        this.I11=I11;
        this.I12=I12;
        this.I13=I13;
        this.I14=I14;

        new getDataFiltersOnline1().execute();

    }


    public void getFilter() {
        new getCaptionData().execute();
    }

    public void getData() {
        new getDataFiltersOnline().execute();
    }

    public void getEmployData() {
        new getEmploy().execute();
    }

    public void getTimeReport(String FDate, String TDate, String FTime,String TTime,String empNo,String chk1,String chk2,String chk3,String chk4,String posNo) {

        this.FDate=FDate;
        this.TDate=TDate;
        this.FTime=FTime;
        this.TTime=TTime;
        this. empNo=empNo;
        this.chk1=chk1;
        this.chk2=chk2;
        this.chk3=chk3;
        this.chk4=chk4;
        this. posNo=posNo;

        new getTimeReportData().execute();
    }

    public void getPOSData() {
        new getPosNo().execute();
    }

    private class getCaptionData extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();

            try {

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.importData));
                pd.setCancelable(false);
                pd.show();
            } catch (Exception e) {

            }

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getCaption));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://" + ip + "/GetCaption";


                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                Log.e("captionurlC", " ==> " + link + "?" + data);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "\n" +
//                    "{\"CAPTION\":[{\"DESCNAMEA\":\"المجموعة\",\"DESCNAMEE\":\"المجموعة\",\"DESCTYPE\":\"1\"},{\"DESCNAMEA\":\"اللون\",\"DESCNAMEE\":\"اللون\",\"DESCTYPE\":\"2\"},{\"DESCNAMEA\":\"القياس\",\"DESCNAMEE\":\"-\",\"DESCTYPE\":\"3\"},{\"DESCNAMEA\":\"الوحدة\",\"DESCNAMEE\":\"الوحدة\",\"DESCTYPE\":\"4\"},{\"DESCNAMEA\":\"الموديل\",\"DESCNAMEE\":\"\",\"DESCTYPE\":\"5\"},{\"DESCNAMEA\":\"-\",\"DESCNAMEE\":\"-\",\"DESCTYPE\":\"6\"},{\"DESCNAMEA\":\"-\",\"DESCNAMEE\":\"-\",\"DESCTYPE\":\"7\"},{\"DESCNAMEA\":\"8\",\"DESCNAMEE\":\"8\",\"DESCTYPE\":\"8\"},{\"DESCNAMEA\":\"9\",\"DESCNAMEE\":\"9\",\"DESCTYPE\":\"9\"},{\"DESCNAMEA\":\"10\",\"DESCNAMEE\":\"10\",\"DESCTYPE\":\"10\"},{\"DESCNAMEA\":\"11\",\"DESCNAMEE\":\"11\",\"DESCTYPE\":\"11\"},{\"DESCNAMEA\":\"12\",\"DESCNAMEE\":\"12\",\"DESCTYPE\":\"12\"},{\"DESCNAMEA\":\"13\",\"DESCNAMEE\":\"13\",\"DESCTYPE\":\"13\"},{\"DESCNAMEA\":\"14\",\"DESCNAMEE\":\"14\",\"DESCTYPE\":\"14\"},{\"DESCNAMEA\":\"15\",\"DESCNAMEE\":\"15\",\"DESCTYPE\":\"15\"},{\"DESCNAMEA\":\"16\",\"DESCNAMEE\":\"16\",\"DESCTYPE\":\"16\"},{\"DESCNAMEA\":\"17\",\"DESCNAMEE\":\"17\",\"DESCTYPE\":\"17\"},{\"DESCNAMEA\":\"18\",\"DESCNAMEE\":\"18\",\"DESCTYPE\":\"18\"},{\"DESCNAMEA\":\"19\",\"DESCNAMEE\":\"19\",\"DESCTYPE\":\"19\"},{\"DESCNAMEA\":\"20\",\"DESCNAMEE\":\"20\",\"DESCTYPE\":\"20\"}],\"DESC1\":[],\"DESC2\":[{\"Desc_Name\":\"ANTHRACIT\"},{\"Desc_Name\":\"ASARI\"},{\"Desc_Name\":\"BABA BLUE\"},{\"Desc_Name\":\"BAIG\"},{\"Desc_Name\":\"BAUIG\"},{\"Desc_Name\":\"BEIGE\"},{\"Desc_Name\":\"BEIGE  WHITE\"},{\"Desc_Name\":\"BEJ\"},{\"Desc_Name\":\"BEYAZ\"},{\"Desc_Name\":\"BISQUE\"},{\"Desc_Name\":\"BLACK\"},{\"Desc_Name\":\"BLUE\"},{\"Desc_Name\":\"BORDO\"},{\"Desc_Name\":\"BROWN\"},{\"Desc_Name\":\"BRWAN\"},{\"Desc_Name\":\"CAGLA\"},{\"Desc_Name\":\"CAMEL\"},{\"Desc_Name\":\"DARK\"},{\"Desc_Name\":\"DARK BLUE\"},{\"Desc_Name\":\"DARK GRAY\"},{\"Desc_Name\":\"DARK GREY\"},{\"Desc_Name\":\"DARK SILVER\"},{\"Desc_Name\":\"DIRTY\"},{\"Desc_Name\":\"EKRU\"},{\"Desc_Name\":\"GRAY\"},{\"Desc_Name\":\"GREEN\"},{\"Desc_Name\":\"GREY\"},{\"Desc_Name\":\"GRI\"},{\"Desc_Name\":\"INDIGO\"},{\"Desc_Name\":\"JEAN BLUE\"},{\"Desc_Name\":\"KIRMIZI\"},{\"Desc_Name\":\"LACL\"},{\"Desc_Name\":\"LILAC\"},{\"Desc_Name\":\"MARIN\"},{\"Desc_Name\":\"NAR\"},{\"Desc_Name\":\"NAVY\"},{\"Desc_Name\":\"RAD\"},{\"Desc_Name\":\"RED\"},{\"Desc_Name\":\"ROEAL\"},{\"Desc_Name\":\"ROYAL\"},{\"Desc_Name\":\"SILVER\"},{\"Desc_Name\":\"TAS\"},{\"Desc_Name\":\"WHITE\"},{\"Desc_Name\":\"YESIL\"},{\"Desc_Name\":\"YESILI\"}],\"DESC3\":[{\"Desc_Name\":\"0\"},{\"Desc_Name\":\"1\"},{\"Desc_Name\":\"2XL\"},{\"Desc_Name\":\"30\"},{\"Desc_Name\":\"32\"},{\"Desc_Name\":\"33\"},{\"Desc_Name\":\"34\"},{\"Desc_Name\":\"36\"},{\"Desc_Name\":\"38\"},{\"Desc_Name\":\"3XL\"},{\"Desc_Name\":\"40\"},{\"Desc_Name\":\"41\"},{\"Desc_Name\":\"42\"},{\"Desc_Name\":\"43\"},{\"Desc_Name\":\"44\"},{\"Desc_Name\":\"46\"},{\"Desc_Name\":\"48\"},{\"Desc_Name\":\"4XL\"},{\"Desc_Name\":\"50\"},{\"Desc_Name\":\"52\"},{\"Desc_Name\":\"54\"},{\"Desc_Name\":\"56\"},{\"Desc_Name\":\"58\"},{\"Desc_Name\":\"5XL\"},{\"Desc_Name\":\"60\"},{\"Desc_Name\":\"62\"},{\"Desc_Name\":\"64\"},{\"Desc_Name\":\"66\"},{\"Desc_Name\":\"L\"},{\"Desc_Name\":\"M\"},{\"Desc_Name\":\"S\"},{\"Desc_Name\":\"XL\"},{\"Desc_Name\":\"XXL\"}],\"DESC4\":[{\"Desc_Name\":\"``\"}],\"DESC5\":[{\"Desc_Name\":\"1\"},{\"Desc_Name\":\"1010\"},{\"Desc_Name\":\"101010\"},{\"Desc_Name\":\"101011\"},{\"Desc_Name\":\"101012\"},{\"Desc_Name\":\"101013\"},{\"Desc_Name\":\"101014\"},{\"Desc_Name\":\"101015\"},{\"Desc_Name\":\"101016\"},{\"Desc_Name\":\"101017\"},{\"Desc_Name\":\"101018\"},{\"Desc_Name\":\"101019\"},{\"Desc_Name\":\"10102\"},{\"Desc_Name\":\"10103\"},{\"Desc_Name\":\"10104\"},{\"Desc_Name\":\"10105\"},{\"Desc_Name\":\"10106\"},{\"Desc_Name\":\"10107\"},{\"Desc_Name\":\"10108\"},{\"Desc_Name\":\"10109\"},{\"Desc_Name\":\"1011\"},{\"Desc_Name\":\"1031\"},{\"Desc_Name\":\"10454\"},{\"Desc_Name\":\"10500\"},{\"Desc_Name\":\"10504\"},{\"Desc_Name\":\"10505\"},{\"Desc_Name\":\"10506\"},{\"Desc_Name\":\"1095\"},{\"Desc_Name\":\"1096\"},{\"Desc_Name\":\"1097\"},{\"Desc_Name\":\"1098\"},{\"Desc_Name\":\"1099\"},{\"Desc_Name\":\"11\"},{\"Desc_Name\":\"1100\"},{\"Desc_Name\":\"128304\"},{\"Desc_Name\":\"12838\"},{\"Desc_Name\":\"12899\"},{\"Desc_Name\":\"13131\"},{\"Desc_Name\":\"13559\"},{\"Desc_Name\":\"1450\"},{\"Desc_Name\":\"1504\"},{\"Desc_Name\":\"15093\"},{\"Desc_Name\":\"1514\"},{\"Desc_Name\":\"1522\"},{\"Desc_Name\":\"1526\"},{\"Desc_Name\":\"1535\"},{\"Desc_Name\":\"1538\"},{\"Desc_Name\":\"1541\"},{\"Desc_Name\":\"1544\"},{\"Desc_Name\":\"1552\"},{\"Desc_Name\":\"1553\"},{\"Desc_Name\":\"1638\"},{\"Desc_Name\":\"194029\"},{\"Desc_Name\":\"2181\"},{\"Desc_Name\":\"22\"},{\"Desc_Name\":\"2200\"},{\"Desc_Name\":\"220018\"},{\"Desc_Name\":\"220019\"},{\"Desc_Name\":\"220020\"},{\"Desc_Name\":\"224006\"},{\"Desc_Name\":\"224062\"},{\"Desc_Name\":\"229016\"},{\"Desc_Name\":\"2304\"},{\"Desc_Name\":\"2305\"},{\"Desc_Name\":\"2306\"},{\"Desc_Name\":\"233217\"},{\"Desc_Name\":\"233224\"},{\"Desc_Name\":\"233238\"},{\"Desc_Name\":\"23325\"},{\"Desc_Name\":\"23410\"},{\"Desc_Name\":\"23411\"},{\"Desc_Name\":\"23903\"},{\"Desc_Name\":\"26\"},{\"Desc_Name\":\"27\"},{\"Desc_Name\":\"28\"},{\"Desc_Name\":\"29\"},{\"Desc_Name\":\"30\"},{\"Desc_Name\":\"30431\"},{\"Desc_Name\":\"30432\"},{\"Desc_Name\":\"30433\"},{\"Desc_Name\":\"30434\"},{\"Desc_Name\":\"30435\"},{\"Desc_Name\":\"30436\"},{\"Desc_Name\":\"30437\"},{\"Desc_Name\":\"30438\"},{\"Desc_Name\":\"30439\"},{\"Desc_Name\":\"30440\"},{\"Desc_Name\":\"30441\"},{\"Desc_Name\":\"30442\"},{\"Desc_Name\":\"30443\"},{\"Desc_Name\":\"30444\"},{\"Desc_Name\":\"30491\"},{\"Desc_Name\":\"30492\"},{\"Desc_Name\":\"31001\"},{\"Desc_Name\":\"31006\"},{\"Desc_Name\":\"3200\"},{\"Desc_Name\":\"323\"},{\"Desc_Name\":\"3322\"},{\"Desc_Name\":\"35\"},{\"Desc_Name\":\"37\"},{\"Desc_Name\":\"3737\"},{\"Desc_Name\":\"399\"},{\"Desc_Name\":\"3990\"},{\"Desc_Name\":\"40007\"},{\"Desc_Name\":\"40008\"},{\"Desc_Name\":\"40502\"},{\"Desc_Name\":\"40705\"},{\"Desc_Name\":\"40902\"},{\"Desc_Name\":\"41102\"},{\"Desc_Name\":\"41201\"},{\"Desc_Name\":\"4200\"},{\"Desc_Name\":\"45\"},{\"Desc_Name\":\"46\"},{\"Desc_Name\":\"47\"},{\"Desc_Name\":\"48\"},{\"Desc_Name\":\"48201\"},{\"Desc_Name\":\"50\"},{\"Desc_Name\":\"512\"},{\"Desc_Name\":\"513\"},{\"Desc_Name\":\"5200\"},{\"Desc_Name\":\"6000\"},{\"Desc_Name\":\"612\"},{\"Desc_Name\":\"613\"},{\"Desc_Name\":\"6200\"},{\"Desc_Name\":\"70406\"},{\"Desc_Name\":\"7200\"},{\"Desc_Name\":\"770\"},{\"Desc_Name\":\"788\"},{\"Desc_Name\":\"812\"},{\"Desc_Name\":\"813\"},{\"Desc_Name\":\"814\"},{\"Desc_Name\":\"815\"},{\"Desc_Name\":\"817\"},{\"Desc_Name\":\"818\"},{\"Desc_Name\":\"8200\"},{\"Desc_Name\":\"821\"},{\"Desc_Name\":\"833\"},{\"Desc_Name\":\"834\"},{\"Desc_Name\":\"835\"},{\"Desc_Name\":\"8353\"},{\"Desc_Name\":\"836\"},{\"Desc_Name\":\"838\"},{\"Desc_Name\":\"839\"},{\"Desc_Name\":\"8404\"},{\"Desc_Name\":\"842\"},{\"Desc_Name\":\"850\"},{\"Desc_Name\":\"852\"},{\"Desc_Name\":\"853\"},{\"Desc_Name\":\"854\"},{\"Desc_Name\":\"855\"},{\"Desc_Name\":\"857\"},{\"Desc_Name\":\"8801\"},{\"Desc_Name\":\"8821\"},{\"Desc_Name\":\"8855\"},{\"Desc_Name\":\"8865\"},{\"Desc_Name\":\"9111\"},{\"Desc_Name\":\"9200\"},{\"Desc_Name\":\"9204\"},{\"Desc_Name\":\"9222\"},{\"Desc_Name\":\"9442\"}],\"DESC6\":[],\"DESC7\":[],\"DESC8\":[],\"DESC9\":[],\"DESC10\":[],\"DESC11\":[],\"DESC12\":[],\"DESC13\":[],\"DESC14\":[]}";

            if (JsonResponse != null && JsonResponse.contains("CAPTION")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.getCaption));
                try {

                    List<GetCaptionModel> ITEM = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(JsonResponse);
                    JSONArray parentArray = jsonObject.getJSONArray("CAPTION");

                    for (int i = 0; i < parentArray.length(); i++) {

                        JSONObject finalObject = parentArray.getJSONObject(i);

                        GetCaptionModel obj = new GetCaptionModel();

                        obj.setDESCNAMEA(finalObject.getString("DESCNAMEA"));
                        obj.setDESCNAMEE(finalObject.getString("DESCNAMEE"));
                        obj.setDESCTYPE(finalObject.getString("DESCTYPE"));

                        try {
                            String DescName = "DESC" + obj.getDESCTYPE();
                            Log.e("captionType", "Dex==> " + DescName);
                            JSONArray TempDescArray = jsonObject.getJSONArray(DescName);

                            List<String> modelList = new ArrayList<>();

                            for (int j = 0; j < TempDescArray.length(); j++) {

                                JSONObject jsonO = TempDescArray.getJSONObject(j);
                                //DESCModel descModel=new DESCModel();

//                                descModel.setDesc_Name(jsonO.getString("Desc_Name"));

                                if (j == 0) {
                                    modelList.clear();
                                    modelList.add(0, "");

                                }

                                modelList.add(jsonO.getString("Desc_Name"));

                            }

                            obj.setDescList(modelList);
                            ITEM.add(obj);

                        } catch (Exception e) {
//                            modelList.clear();
                            obj.setDescList(new ArrayList<String>());
                            ITEM.add(obj);
                            Log.e("caption Error", "Error in get Desc" + i);
                        }


                    }


                    pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pd.setTitleText(context.getResources().getString(R.string.getCaptionSuccesful));

                    ItemTransaction captionLayout = (ItemTransaction) context;
                    captionLayout.fillAdapter(ITEM);
                    pd.dismiss();
//                    if (pd != null) {
//                        pd.dismiss();
////
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
                    Log.e("caption_Error", "Error ==> " + e.toString());
                }

            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
                }
//                }else{
                Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();
//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
//                }
            }


        }
    }


    private class getDataFiltersOnline extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
            try {

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.importData));
                pd.setCancelable(false);
                pd.show();
            } catch (Exception e) {

            }

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getItemTrancaction));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://" + ip + "/GetItemAction";
//CONO=290&ItemU=&ItemG=&ItemK=&ItemM=&ItemL=&ITEMGS=&ITEMDIV=&ITEMSUB1=&ITEMSUB2=&ITEMSUB3=&ITEMSUB4=&ITEMSUB5=&ITEMSUB6=&ITEMSUB7

                //  CONO      := Request.ContentFields.Values['CONO'];
                //      dDate1    := StrToDateDef(Request.ContentFields.Values['dDate1'],DATE);
                //      dDate2    := StrToDateDef(Request.ContentFields.Values['dDate2'],DATE);
                //      iKind     := StrToIntDef(Request.ContentFields.Values['iKind'],-1);
                //      bGroup    := StrToIntDef(Request.ContentFields.Values['bGroup'],-1);
                //      bSub1     := StrToIntDef(Request.ContentFields.Values['bSub1'],-1);
                //      bSub2     := StrToIntDef(Request.ContentFields.Values['bSub2'],-1);
                //      bSub3     := StrToIntDef(Request.ContentFields.Values['bSub3'],-1);
                //      bSub4     := StrToIntDef(Request.ContentFields.Values['bSub4'],-1);
                //      bSub5     := StrToIntDef(Request.ContentFields.Values['bSub5'],-1);
                //      bSub6     := StrToIntDef(Request.ContentFields.Values['bSub6'],-1);
                //      bSub7     := StrToIntDef(Request.ContentFields.Values['bSub7'],-1);
                //      sGroup    := Request.ContentFields.Values['sGroup'];
                //      sSub1     := Request.ContentFields.Values['sSub1'];
                //      sSub2     := Request.ContentFields.Values['sSub2'];
                //      sSub3     := Request.ContentFields.Values['sSub3'];
                //      sSub4     := Request.ContentFields.Values['sSub4'];
                //      sSub5     := Request.ContentFields.Values['sSub5'];
                //      sSub6     := Request.ContentFields.Values['sSub6'];
                //      sSub7     := Request.ContentFields.Values['sSub7'];
                //      sUserNo   := Request.ContentFields.Values['sUserNo'];
                //      sGrp      := Request.ContentFields.Values['sGrp'];
                //      sUnit     := Request.ContentFields.Values['sUnit'];
                //      sKind     := Request.ContentFields.Values['sKind'];
                //      sItemCode := Request.ContentFields.Values['sItemCode'];
                //      sLoc      := Request.ContentFields.Values['sLoc'];
                //      sModal    := Request.ContentFields.Values['sModal'];
                //      sSubGrp   := Request.ContentFields.Values['sSubGrp'];
                //      sDiv      := Request.ContentFields.Values['sDiv'];
                //      PosNo     := StrToIntDef(Request.ContentFields.Values['PosNo'],-1);
                //      bByPrc    := StrToIntDef(Request.ContentFields.Values['bByPrc'],-1);
                //      FrmPrc    := StrToFloatDef(Request.ContentFields.Values['FrmPrc'],-1);
                //      ToPrc     := StrToFloatDef(Request.ContentFields.Values['ToPrc'],-1);
                //      bSoldByDifPrc := StrToIntDef(Request.ContentFields.Values['bSoldByDifPrc'],-1);
                //      iPriceK   := StrToIntDef(Request.ContentFields.Values['iPriceK'],-1);
                //      OFFERNO   := Request.ContentFields.Values['OFFERNO'];
                //      OFFERNAME := Request.ContentFields.Values['OFFERNAME'];
                //      BISKIT    := StrToIntDef(Request.ContentFields.Values['BISKIT'],-1);
                //      SALESNO   := Request.ContentFields.Values['SALESNO'];
                //      POSNOSTR  := Request.ContentFields.Values['POSNOSTR'];

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8") + "&" +
                        "dDate1=" + URLEncoder.encode(dDate1, "UTF-8") + "&" +
                        "dDate2=" + URLEncoder.encode(dDate2, "UTF-8") + "&" +
                        "iKind=" + URLEncoder.encode(iKind, "UTF-8") + "&" +
                        "bGroup=" + URLEncoder.encode(bGroup, "UTF-8") + "&" +
                        "bSub1=" + URLEncoder.encode(bSub1, "UTF-8") + "&" +
                        "bSub2=" + URLEncoder.encode(bSub2, "UTF-8") + "&" +
                        "bSub3=" + URLEncoder.encode(bSub3, "UTF-8") + "&" +
                        "bSub4=" + URLEncoder.encode(bSub4, "UTF-8") + "&" +
                        "bSub5=" + URLEncoder.encode(bSub5, "UTF-8") + "&" +
                        "bSub6=" + URLEncoder.encode(bSub6, "UTF-8") + "&" +
                        "bSub7=" + URLEncoder.encode(bSub7, "UTF-8") + "&" +
                        "sGroup=" + URLEncoder.encode(sGroup, "UTF-8") + "&" +
                        "sSub1=" + URLEncoder.encode(sSub1, "UTF-8") + "&" +
                        "sSub2=" + URLEncoder.encode(sSub2, "UTF-8") + "&" +
                        "sSub3=" + URLEncoder.encode(sSub3, "UTF-8") + "&" +
                        "sSub4=" + URLEncoder.encode(sSub4, "UTF-8") + "&" +
                        "sSub5=" + URLEncoder.encode(sSub5, "UTF-8") + "&" +
                        "sSub6=" + URLEncoder.encode(sSub6, "UTF-8") + "&" +
                        "sSub7=" + URLEncoder.encode(sSub7, "UTF-8") + "&" +
                        "sUserNo=" + URLEncoder.encode(sUserNo, "UTF-8") + "&" +
                        "sGrp=" + URLEncoder.encode(sGrp, "UTF-8") + "&" +
                        "sUnit=" + URLEncoder.encode(sUnit, "UTF-8") + "&" +
                        "sKind=" + URLEncoder.encode(sKind, "UTF-8") + "&" +
                        "sItemCode=" + URLEncoder.encode(sItemCode, "UTF-8") + "&" +
                        "sLoc=" + URLEncoder.encode(sLoc, "UTF-8") + "&" +
                        "sModal=" + URLEncoder.encode(sModal, "UTF-8") + "&" +
                        "sSubGrp=" + URLEncoder.encode(sSubGrp, "UTF-8") + "&" +
                        "sDiv=" + URLEncoder.encode(sDiv, "UTF-8") + "&" +
                        "PosNo=" + URLEncoder.encode(PosNo, "UTF-8") + "&" +
                        "bByPrc=" + URLEncoder.encode(bByPrc, "UTF-8") + "&" +
                        "FrmPrc=" + URLEncoder.encode(FrmPrc, "UTF-8") + "&" +
                        "ToPrc=" + URLEncoder.encode(ToPrc, "UTF-8") + "&" +
                        "bSoldByDifPrc=" + URLEncoder.encode(bSoldByDifPrc, "UTF-8") + "&" +
                        "iPriceK=" + URLEncoder.encode(iPriceK, "UTF-8") + "&" +
                        "OFFERNO=" + URLEncoder.encode(OFFERNO, "UTF-8") + "&" +
                        "OFFERNAME=" + URLEncoder.encode(OFFERNAME, "UTF-8") + "&" +
                        "BISKIT=" + URLEncoder.encode(BISKIT, "UTF-8") + "&" +
                        "SALESNO=" + URLEncoder.encode(SALESNO, "UTF-8") + "&" +
                        "POSNOSTR=" + URLEncoder.encode(POSNOSTR, "UTF-8");
                Log.e("captionurl", " ==> " + link + "?" + data);

                Log.e("caption data", " ==> " + data);


                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//           JsonResponse="[{\"AccCode\":\"0\",\"ItemNCode\":\"2600000003\",\"ItemOCode\":\"2600000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2600000004\",\"ItemOCode\":\"2600000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2600000005\",\"ItemOCode\":\"2600000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000000\",\"ItemOCode\":\"3000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000001\",\"ItemOCode\":\"3000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000002\",\"ItemOCode\":\"3000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000003\",\"ItemOCode\":\"3000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000004\",\"ItemOCode\":\"3000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000005\",\"ItemOCode\":\"3000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000000\",\"ItemOCode\":\"5000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000001\",\"ItemOCode\":\"5000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000002\",\"ItemOCode\":\"5000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000003\",\"ItemOCode\":\"5000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000004\",\"ItemOCode\":\"5000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000005\",\"ItemOCode\":\"5000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000000\",\"ItemOCode\":\"2800000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000001\",\"ItemOCode\":\"2800000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000002\",\"ItemOCode\":\"2800000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000003\",\"ItemOCode\":\"2800000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000004\",\"ItemOCode\":\"2800000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000005\",\"ItemOCode\":\"2800000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000000\",\"ItemOCode\":\"1000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000001\",\"ItemOCode\":\"1000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000002\",\"ItemOCode\":\"1000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000003\",\"ItemOCode\":\"1000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000004\",\"ItemOCode\":\"1000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000005\",\"ItemOCode\":\"1000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000000\",\"ItemOCode\":\"1100000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000001\",\"ItemOCode\":\"1100000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000002\",\"ItemOCode\":\"1100000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000003\",\"ItemOCode\":\"1100000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000000\",\"ItemOCode\":\"2200000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000001\",\"ItemOCode\":\"2200000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000002\",\"ItemOCode\":\"2200000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000003\",\"ItemOCode\":\"2200000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800000\",\"ItemOCode\":\"7708800000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800001\",\"ItemOCode\":\"7708800001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800002\",\"ItemOCode\":\"7708800002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800003\",\"ItemOCode\":\"7708800003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180000\",\"ItemOCode\":\"2200180000\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180001\",\"ItemOCode\":\"2200180001\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180002\",\"ItemOCode\":\"2200180002\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180003\",\"ItemOCode\":\"2200180003\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180004\",\"ItemOCode\":\"2200180004\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180005\",\"ItemOCode\":\"2200180005\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180006\",\"ItemOCode\":\"2200180006\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180007\",\"ItemOCode\":\"2200180007\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180008\",\"ItemOCode\":\"2200180008\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180009\",\"ItemOCode\":\"2200180009\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180010\",\"ItemOCode\":\"2200180010\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180011\",\"ItemOCode\":\"2200180011\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180012\",\"ItemOCode\":\"2200180012\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180013\",\"ItemOCode\":\"2200180013\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180014\",\"ItemOCode\":\"2200180014\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190000\",\"ItemOCode\":\"2200190000\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190001\",\"ItemOCode\":\"2200190001\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190002\",\"ItemOCode\":\"2200190002\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190003\",\"ItemOCode\":\"2200190003\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190004\",\"ItemOCode\":\"2200190004\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190005\",\"ItemOCode\":\"2200190005\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190006\",\"ItemOCode\":\"2200190006\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190007\",\"ItemOCode\":\"2200190007\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190008\",\"ItemOCode\":\"2200190008\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190009\",\"ItemOCode\":\"2200190009\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190010\",\"ItemOCode\":\"2200190010\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190011\",\"ItemOCode\":\"2200190011\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190012\",\"ItemOCode\":\"2200190012\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190013\",\"ItemOCode\":\"2200190013\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190014\",\"ItemOCode\":\"2200190014\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190015\",\"ItemOCode\":\"2200190015\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190016\",\"ItemOCode\":\"2200190016\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190017\",\"ItemOCode\":\"2200190017\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190018\",\"ItemOCode\":\"2200190018\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190019\",\"ItemOCode\":\"2200190019\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200000\",\"ItemOCode\":\"2200200000\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200001\",\"ItemOCode\":\"2200200001\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200002\",\"ItemOCode\":\"2200200002\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200003\",\"ItemOCode\":\"2200200003\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1100110002\",\"ItemOCode\":\"1100110002\",\"ItemNameA\":\"BOXSER\",\"ItemNameE\":\"BOXSER\",\"InDate\":\"13\\/04\\/2023 10:06:07 م\",\"F_D\":\"5\",\"AVLQTY\":\"78\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200004\",\"ItemOCode\":\"2200200004\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200005\",\"ItemOCode\":\"2200200005\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200006\",\"ItemOCode\":\"2200200006\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200007\",\"ItemOCode\":\"2200200007\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200008\",\"ItemOCode\":\"2200200008\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200009\",\"ItemOCode\":\"2200200009\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200010\",\"ItemOCode\":\"2200200010\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200011\",\"ItemOCode\":\"2200200011\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200012\",\"ItemOCode\":\"2200200012\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200013\",\"ItemOCode\":\"2200200013\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200014\",\"ItemOCode\":\"2200200014\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200015\",\"ItemOCode\":\"2200200015\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200016\",\"ItemOCode\":\"2200200016\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200017\",\"ItemOCode\":\"2200200017\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200018\",\"ItemOCode\":\"2200200018\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200019\",\"ItemOCode\":\"2200200019\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200020\",\"ItemOCode\":\"2200200020\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000000\",\"ItemOCode\":\"1552000000\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000001\",\"ItemOCode\":\"1552000001\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000002\",\"ItemOCode\":\"1552000002\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000003\",\"ItemOCode\":\"1552000003\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000004\",\"ItemOCode\":\"1552000004\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000005\",\"ItemOCode\":\"1552000005\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000006\",\"ItemOCode\":\"1552000006\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000007\",\"ItemOCode\":\"1552000007\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000008\",\"ItemOCode\":\"1552000008\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000009\",\"ItemOCode\":\"1552000009\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000010\",\"ItemOCode\":\"1552000010\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000011\",\"ItemOCode\":\"1552000011\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000012\",\"ItemOCode\":\"1552000012\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000013\",\"ItemOCode\":\"1552000013\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000014\",\"ItemOCode\":\"1552000014\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000015\",\"ItemOCode\":\"1552000015\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000016\",\"ItemOCode\":\"1552000016\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000017\",\"ItemOCode\":\"1552000017\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000018\",\"ItemOCode\":\"1552000018\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000019\",\"ItemOCode\":\"1552000019\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000020\",\"ItemOCode\":\"1552000020\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1289900016\",\"ItemOCode\":\"1289900016\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800000\",\"ItemOCode\":\"1283800000\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800001\",\"ItemOCode\":\"1283800001\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800002\",\"ItemOCode\":\"1283800002\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800003\",\"ItemOCode\":\"1283800003\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800004\",\"ItemOCode\":\"1283800004\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800005\",\"ItemOCode\":\"1283800005\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800006\",\"ItemOCode\":\"1283800006\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800007\",\"ItemOCode\":\"1283800007\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800008\",\"ItemOCode\":\"1283800008\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800009\",\"ItemOCode\":\"1283800009\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800010\",\"ItemOCode\":\"1283800010\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800011\",\"ItemOCode\":\"1283800011\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800012\",\"ItemOCode\":\"1283800012\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800013\",\"ItemOCode\":\"1283800013\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800014\",\"ItemOCode\":\"1283800014\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800015\",\"ItemOCode\":\"1283800015\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040000\",\"ItemOCode\":\"1283040000\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040001\",\"ItemOCode\":\"1283040001\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040002\",\"ItemOCode\":\"1283040002\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040003\",\"ItemOCode\":\"1283040003\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040004\",\"ItemOCode\":\"1283040004\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040005\",\"ItemOCode\":\"1283040005\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040006\",\"ItemOCode\":\"1283040006\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040007\",\"ItemOCode\":\"1283040007\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040008\",\"ItemOCode\":\"1283040008\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000000\",\"ItemOCode\":\"3500000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000001\",\"ItemOCode\":\"3500000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000002\",\"ItemOCode\":\"3500000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000003\",\"ItemOCode\":\"3500000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000004\",\"ItemOCode\":\"3500000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000005\",\"ItemOCode\":\"3500000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"}]";
            if (JsonResponse != null && JsonResponse.contains("ItemOCode")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.DataGet));


                try {

                    List<TransactionModel> captionItem = new ArrayList<>();
                    JSONArray parentArray = new JSONArray(JsonResponse);
                    for (int i = 0; i < parentArray.length(); i++) {
                        JSONObject finalObject = parentArray.getJSONObject(i);

                        TransactionModel captionItemInfo = new TransactionModel();
//{"AccCode":"0","ItemNCode":"7708800120","ItemOCode":"7708800120","ItemNameA":"SUIT WOLL","ItemNameE":"SUIT WOLL","InDate":"18\/04\/2023","F_D":"200","AVLQTY":"1"}
//                        captionItemInfo.setItemNCode(finalObject.getString("ItemNCode"));
//                        captionItemInfo.setAccCode(finalObject.getString("AccCode"));
//                        captionItemInfo.setItemOCode(finalObject.getString("ItemOCode"));
//                        captionItemInfo.setInDate(finalObject.getString("InDate"));
//                        captionItemInfo.setItemNameA(finalObject.getString("ItemNameA"));
//                        captionItemInfo.setItemNameE(finalObject.getString("ItemNameE"));
//                        captionItemInfo.setF_D(finalObject.getString("F_D"));
//                        captionItemInfo.setAVLQTY(finalObject.getString("AVLQTY"));

                        //"ITEMUNIT": "",
                        ////        "ITEMGROUP": "BLUES",
                        ////        "ITEMCOLOR": "BLUE",
                        ////        "ITEMSIZE": "XL",
                        ////        "ITEMMODEL": "10454",
                        ////        "ITEMGS": "",
                        ////        "ITEMDIV": "",
                        ////        "ITEMSUB1": "",
                        ////        "ITEMSUB2": "",
                        ////        "ITEMSUB3": "",
                        ////        "ITEMSUB4": "",
                        ////        "ITEMSUB5": "",
                        ////        "ITEMSUB6": "",
                        ////        "ITEMSUB7": ""

                        try {
//                            captionItemInfo.setITEMUNIT(finalObject.getString("ITEMUNIT"));
//                            captionItemInfo.setITEMGROUP(finalObject.getString("ITEMGROUP"));
//                            captionItemInfo.setITEMCOLOR(finalObject.getString("ITEMCOLOR"));
//                            captionItemInfo.setITEMSIZE(finalObject.getString("ITEMSIZE"));
//                            captionItemInfo.setITEMMODEL(finalObject.getString("ITEMMODEL"));
//                            captionItemInfo.setITEMGS(finalObject.getString("ITEMGS"));
//                            captionItemInfo.setITEMDIV(finalObject.getString("ITEMDIV"));
//                            captionItemInfo.setITEMSUB1(finalObject.getString("ITEMSUB1"));
//                            captionItemInfo.setITEMSUB2(finalObject.getString("ITEMSUB2"));
//                            captionItemInfo.setITEMSUB3(finalObject.getString("ITEMSUB3"));
//                            captionItemInfo.setITEMSUB4(finalObject.getString("ITEMSUB4"));
//                            captionItemInfo.setITEMSUB5(finalObject.getString("ITEMSUB5"));
//                            captionItemInfo.setITEMSUB6(finalObject.getString("ITEMSUB6"));
//                            captionItemInfo.setITEMSUB7(finalObject.getString("ITEMSUB7"));
                        } catch (Exception r) {

                        }
                        captionItem.add(captionItemInfo);

                    }

                    ItemTransaction captionLayout = (ItemTransaction) context;
                    captionLayout.fillArrayDataAfterFilter(captionItem);


                    try {
                        pd.dismiss();
                    } catch (Exception e) {
                        Log.e("caption_ErrorD", "ErrorDialog ==> " + e.toString());

                    }


                } catch (Exception e) {
                    Log.e("caption_ErrorD", "Error ==> " + e.toString());
                }


            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
//                    }
//                }else{
                    Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();

                    ItemTransaction captionLayout = (ItemTransaction) context;
                    captionLayout.fillArrayDataAfterFilter(new ArrayList<TransactionModel>());

//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
                }
            }


        }
    }

    private class getTimeReportData extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();

            try {

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.importData));
                pd.setCancelable(false);
                pd.show();
            } catch (Exception e) {

            }

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getCaption));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://" + ip + "/GetClockRep";

//CONO&dFromDate&dToDate&sFromTime&sToTime&sEmpNo&Chk1&Chk2&Chk3&Chk4&iPosNo

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8") + "&"
                        + "dFromDate=" + URLEncoder.encode(FDate, "UTF-8") + "&"
                        + "dToDate=" + URLEncoder.encode(TDate, "UTF-8") + "&"
                        + "sFromTime=" + URLEncoder.encode(FTime, "UTF-8") + "&"
                        + "sToTime=" + URLEncoder.encode(TTime, "UTF-8") + "&"
                        + "sEmpNo=" + URLEncoder.encode(empNo, "UTF-8") + "&"
                        + "Chk1=" + URLEncoder.encode(chk1, "UTF-8") + "&"
                        + "Chk2=" + URLEncoder.encode(chk2, "UTF-8") + "&"
                        + "Chk3=" + URLEncoder.encode(chk3, "UTF-8") + "&"
                        + "Chk4=" + URLEncoder.encode(chk4, "UTF-8") + "&"
                        + "iPosNo=" + URLEncoder.encode(posNo, "UTF-8");

                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                Log.e("captionurlC", " ==> " + link + "?" + data);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"EMPNO\":\"1\",\"EMPNAME\":\"Alaa\",\"TDATE\":\"01\\/01\\/2018\",\"TTIME\":\"10:30\",\"TRANSKIND\":\"1\",\"TRANSNAME\":\"Enter\",\"DEPTNO\":\"1\",\"SHIFTNO\":\"1\",\"JOPTITIL\":\"1\",\"NFIELD1\":\"1\",\"NFIELD2\":\"1\",\"CFIELD1\":\"1\",\"CFIELD2\":\"1\",\"CFIELD3\":\"1\",\"CLOSED\":\"1\",\"TDCDATE\":\"31\\/12\\/1899\",\"EXPORTED\":\"1\",\"POSNO\":\"2\",\"USERNO\":\"1\",\"USERNAME\":\"CASHIR1\",\"PASSWORD\":\"\",\"SUPERVISOR\":\"0\",\"ADDITEM\":\"1\",\"CHANGEITEMINFO\":\"0\",\"REPRINTREC\":\"1\",\"RETURNITEM\":\"1\",\"ULOGGEDIN\":\"\",\"ACHANGETIME\":\"\",\"PASSWORDSTR\":\"350\",\"POSNO_1\":\"-1\",\"INACTIVE\":\"1\",\"INDATE\":\"10\\/10\\/2019 07:00:54 م\",\"CREATED_BY\":\"\",\"UPDATED_BY\":\"\",\"CREATION_DATE\":\"16\\/12\\/2021 03:53:08 م\"}]\n";
            if (JsonResponse != null && JsonResponse.contains("EMPNO")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.getData));

                List<TimeReortModel> ITEM = new ArrayList<>();

                try {
                    JSONArray jsonArray=new JSONArray(JsonResponse);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        TimeReortModel j=new TimeReortModel();

//                        [{"EMPNO":"1","EMPNAME":"Alaa","TDATE":"01\/01\/2018","TTIME":"10:30","TRANSKIND":"1","TRANSNAME":"Enter","DEPTNO":"1","SHIFTNO":"1","JOPTITIL":"1","NFIELD1":"1","NFIELD2":"1","CFIELD1":"1","CFIELD2":"1","CFIELD3":"1","CLOSED":"1","TDCDATE":"31\/12\/1899","EXPORTED":"1","POSNO":"2","USERNO":"1","USERNAME":"CASHIR1","PASSWORD":"","SUPERVISOR":"0","ADDITEM":"1","CHANGEITEMINFO":"0","REPRINTREC":"1","RETURNITEM":"1","ULOGGEDIN":"","ACHANGETIME":"","PASSWORDSTR":"350","POSNO_1":"-1","INACTIVE":"1","INDATE":"10\/10\/2019 07:00:54 م","CREATED_BY":"","UPDATED_BY":"","CREATION_DATE":"16\/12\/2021 03:53:08 م"}]
                        j.setEmpNo(jsonObject.getString("EMPNO"));
                        j.setEmpName(jsonObject.getString("EMPNAME"));
                        j.setTranceDate(jsonObject.getString("TDATE"));
                        j.setTranceTime(jsonObject.getString("TTIME"));
                        j.setAction(jsonObject.getString("TRANSNAME"));
                        j.setPosNo(jsonObject.getString("POSNO_1"));

                        ITEM.add(j);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }





                pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pd.setTitleText(context.getResources().getString(R.string.getCaptionSuccesful));

                TimeReportActivity captionLayout = (TimeReportActivity) context;
                captionLayout.fillArrayDataAfterFilter(ITEM);
                pd.dismiss();
//                    if (pd != null) {
//                        pd.dismiss();
////
//                    }

            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
                }
//                }else{
                Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();
//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
//                }
            }


        }
    }


    private class getEmploy extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();

            try {

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.importData));
                pd.setCancelable(false);
                pd.show();
            } catch (Exception e) {

            }

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getCaption));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://" + ip + "/GetPOSSALESPERSONS";

//CONO&dFromDate&dToDate&sFromTime&sToTime&sEmpNo&Chk1&Chk2&Chk3&Chk4&iPosNo

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8") ;

                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                Log.e("captionurlC", " ==> " + link + "?" + data);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"USERNO\":\"5001\",\"USERNAME\":\"ABU ASAL\"},{\"USERNO\":\"6021\",\"USERNAME\":\"AKNAN\"},{\"USERNO\":\"8007\",\"USERNAME\":\"ALIAA\"},{\"USERNO\":\"951\",\"USERNAME\":\"BASSAM\"},{\"USERNO\":\"1\",\"USERNAME\":\"CASHIR1\"},{\"USERNO\":\"2\",\"USERNAME\":\"CASHIR2\"},{\"USERNO\":\"7006\",\"USERNAME\":\"EMIEL\"},{\"USERNO\":\"7029\",\"USERNAME\":\"FARAH\"},{\"USERNO\":\"1008\",\"USERNAME\":\"GHAITH TWAIEQ\"},{\"USERNO\":\"6020\",\"USERNAME\":\"HEBA\"},{\"USERNO\":\"1111\",\"USERNAME\":\"IT\"},{\"USERNO\":\"99\",\"USERNAME\":\"JALAL ABUHASSAN\"},{\"USERNO\":\"6002\",\"USERNAME\":\"LOAI\"},{\"USERNO\":\"1888\",\"USERNAME\":\"MALL SUPERVISOR\"},{\"USERNO\":\"7050\",\"USERNAME\":\"MOH'D QNANWEH\"},{\"USERNO\":\"8015\",\"USERNAME\":\"MOH'D SUHAIL\"},{\"USERNO\":\"1980\",\"USERNAME\":\"MOHAMMAD ALAZAB\"},{\"USERNO\":\"11\",\"USERNAME\":\"MOZAIC\"},{\"USERNO\":\"7040\",\"USERNAME\":\"ODAI\"},{\"USERNO\":\"1122\",\"USERNAME\":\"QASSEM\"},{\"USERNO\":\"7045\",\"USERNAME\":\"RAHAF\"},{\"USERNO\":\"7075\",\"USERNAME\":\"RANEEM\"},{\"USERNO\":\"7074\",\"USERNAME\":\"RISAM\"},{\"USERNO\":\"7076\",\"USERNAME\":\"RO'AA\"},{\"USERNO\":\"2000\",\"USERNAME\":\"SECOND MANAGER\"},{\"USERNO\":\"3000\",\"USERNAME\":\"STORE MANAGER\"},{\"USERNO\":\"5000\",\"USERNAME\":\"TEST 1\"},{\"USERNO\":\"6000\",\"USERNAME\":\"TEST 2\"},{\"USERNO\":\"2020\",\"USERNAME\":\"TEST CHANGE LANG\"},{\"USERNO\":\"9000\",\"USERNAME\":\"TEST NO PREV\"},{\"USERNO\":\"4000\",\"USERNAME\":\"TEST NON MASTER\"},{\"USERNO\":\"4500\",\"USERNAME\":\"TEST NON USER W PERM\"},{\"USERNO\":\"3\",\"USERNAME\":\"TEST PRICE\"},{\"USERNO\":\"85\",\"USERNAME\":\"TEST RECIVABLE\"},{\"USERNO\":\"86\",\"USERNAME\":\"TEST RECIVABLE1\"},{\"USERNO\":\"50000\",\"USERNAME\":\"TEST TRANSFER RECV\"},{\"USERNO\":\"7000\",\"USERNAME\":\"TEST USER 1\"},{\"USERNO\":\"8000\",\"USERNAME\":\"TEST USER 2\"},{\"USERNO\":\"10000\",\"USERNAME\":\"TEST10000\"},{\"USERNO\":\"12345\",\"USERNAME\":\"TESTFP\"},{\"USERNO\":\"7072\",\"USERNAME\":\"ZAID\"},{\"USERNO\":\"2031\",\"USERNAME\":\"اسامة طبيشات\"},{\"USERNO\":\"9126\",\"USERNAME\":\"اشرف ابو دولة\"},{\"USERNO\":\"1000\",\"USERNAME\":\"المستخدم الرئيسي\"},{\"USERNO\":\"4011\",\"USERNAME\":\"تامر جوارنة\"},{\"USERNO\":\"4046\",\"USERNAME\":\"جواد\"},{\"USERNO\":\"7034\",\"USERNAME\":\"حنا معايعة\"},{\"USERNO\":\"7019\",\"USERNAME\":\"سامر\"},{\"USERNO\":\"4064\",\"USERNAME\":\"عدي العزام\"},{\"USERNO\":\"7023\",\"USERNAME\":\"عمر مخادمة\"},{\"USERNO\":\"4171\",\"USERNAME\":\"محمد ابو حسان\"},{\"USERNO\":\"7098\",\"USERNAME\":\"محمد الزعبي\"},{\"USERNO\":\"4012\",\"USERNAME\":\"محمد خرطبيل\"},{\"USERNO\":\"7088\",\"USERNAME\":\"هيا حداد\"},{\"USERNO\":\"7031\",\"USERNAME\":\"وسام\"}]";
        if (JsonResponse != null && JsonResponse.contains("USERNO")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.getData));

                List<EmployModel> ITEM = new ArrayList<>();

                try {
                    JSONArray jsonArray=new JSONArray(JsonResponse);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        EmployModel j=new EmployModel();

//                        [{"EMPNO":"1","EMPNAME":"Alaa","TDATE":"01\/01\/2018","TTIME":"10:30","TRANSKIND":"1","TRANSNAME":"Enter","DEPTNO":"1","SHIFTNO":"1","JOPTITIL":"1","NFIELD1":"1","NFIELD2":"1","CFIELD1":"1","CFIELD2":"1","CFIELD3":"1","CLOSED":"1","TDCDATE":"31\/12\/1899","EXPORTED":"1","POSNO":"2","USERNO":"1","USERNAME":"CASHIR1","PASSWORD":"","SUPERVISOR":"0","ADDITEM":"1","CHANGEITEMINFO":"0","REPRINTREC":"1","RETURNITEM":"1","ULOGGEDIN":"","ACHANGETIME":"","PASSWORDSTR":"350","POSNO_1":"-1","INACTIVE":"1","INDATE":"10\/10\/2019 07:00:54 م","CREATED_BY":"","UPDATED_BY":"","CREATION_DATE":"16\/12\/2021 03:53:08 م"}]
                        j.setUSERNAME(jsonObject.getString("USERNAME"));
                        j.setUSERNO(jsonObject.getString("USERNO"));



                        ITEM.add(j);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }





                pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pd.setTitleText(context.getResources().getString(R.string.getCaptionSuccesful));

                TimeReportActivity captionLayout = (TimeReportActivity) context;
                captionLayout.openSearchDialog(ITEM);
                pd.dismiss();
//                    if (pd != null) {
//                        pd.dismiss();
////
//                    }

            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
                }
//                }else{
                Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();
//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
//                }
            }


        }
    }

    private class getPosNo extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();

            try {

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.importData));
                pd.setCancelable(false);
                pd.show();
            } catch (Exception e) {

            }

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getCaption));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://" + ip + "/GetPosNo";

//CONO&dFromDate&dToDate&sFromTime&sToTime&sEmpNo&Chk1&Chk2&Chk3&Chk4&iPosNo

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8") ;

                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                Log.e("captionurlC", " ==> " + link + "?" + data);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse ="[{\"POSNO\":\"0\",\"POSNAME\":\"POS0\"},{\"POSNO\":\"1\",\"POSNAME\":\"POS1\"},{\"POSNO\":\"2\",\"POSNAME\":\"POS2\"},{\"POSNO\":\"3\",\"POSNAME\":\"PSO3\"},{\"POSNO\":\"119\",\"POSNAME\":\"\"}]";
        if (JsonResponse != null && JsonResponse.contains("POSNO")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.getData));

                List<POSModel> ITEM = new ArrayList<>();
                List<String> posNameList=new ArrayList<>();

                try {
                    JSONArray jsonArray=new JSONArray(JsonResponse);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        POSModel j=new POSModel();

//                        [{"EMPNO":"1","EMPNAME":"Alaa","TDATE":"01\/01\/2018","TTIME":"10:30","TRANSKIND":"1","TRANSNAME":"Enter","DEPTNO":"1","SHIFTNO":"1","JOPTITIL":"1","NFIELD1":"1","NFIELD2":"1","CFIELD1":"1","CFIELD2":"1","CFIELD3":"1","CLOSED":"1","TDCDATE":"31\/12\/1899","EXPORTED":"1","POSNO":"2","POSNO":"1","USERNAME":"CASHIR1","PASSWORD":"","SUPERVISOR":"0","ADDITEM":"1","CHANGEITEMINFO":"0","REPRINTREC":"1","RETURNITEM":"1","ULOGGEDIN":"","ACHANGETIME":"","PASSWORDSTR":"350","POSNO_1":"-1","INACTIVE":"1","INDATE":"10\/10\/2019 07:00:54 م","CREATED_BY":"","UPDATED_BY":"","CREATION_DATE":"16\/12\/2021 03:53:08 م"}]
                        j.setPOSNAME(jsonObject.getString("POSNAME"));
                        j.setPOSNO(jsonObject.getString("POSNO"));
                        posNameList.add(jsonObject.getString("POSNAME"));


                        ITEM.add(j);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }





                pd.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pd.setTitleText(context.getResources().getString(R.string.getCaptionSuccesful));

                TimeReportActivity captionLayout = (TimeReportActivity) context;
                captionLayout.fillSpinerPOsNo(posNameList,ITEM);
                pd.dismiss();
//                    if (pd != null) {
//                        pd.dismiss();
////
//                    }

            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
                }
//                }else{
                Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();
//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
//                }
            }


        }
    }


    private class getDataFiltersOnline1 extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();

            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.getData));

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://"+ip + "/GetItemsInfo";
//CONO=290&ItemU=&ItemG=&ItemK=&ItemM=&ItemL=&ITEMGS=&ITEMDIV=&ITEMSUB1=&ITEMSUB2=&ITEMSUB3=&ITEMSUB4=&ITEMSUB5=&ITEMSUB6=&ITEMSUB7

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8")+"&"+
                        "ItemU=" + URLEncoder.encode(I1, "UTF-8")+"&"+
                        "ItemG=" + URLEncoder.encode(I2, "UTF-8")+"&"+
                        "ItemK=" + URLEncoder.encode(I3, "UTF-8")+"&"+
                        "ItemM=" + URLEncoder.encode(I4, "UTF-8")+"&"+
                        "ItemL=" + URLEncoder.encode(I5, "UTF-8")+"&"+
                        "ITEMGS=" + URLEncoder.encode(I6, "UTF-8")+"&"+
                        "ITEMDIV=" + URLEncoder.encode(I7, "UTF-8")+"&"+
                        "ITEMSUB1=" + URLEncoder.encode(I8, "UTF-8")+"&"+
                        "ITEMSUB2=" + URLEncoder.encode(I9, "UTF-8")+"&"+
                        "ITEMSUB3=" + URLEncoder.encode(I10, "UTF-8")+"&"+
                        "ITEMSUB4=" + URLEncoder.encode(I11, "UTF-8")+"&"+
                        "ITEMSUB5=" + URLEncoder.encode(I12, "UTF-8")+"&"+
                        "ITEMSUB6=" + URLEncoder.encode(I13, "UTF-8")+"&"+
                        "ITEMSUB7=" + URLEncoder.encode(I14, "UTF-8")+"&"+
                        "ITEMOCODE=" + URLEncoder.encode(ITEMOCODEs, "UTF-8");
                Log.e("captionurl"," ==> "+link+"?"+data);

                Log.e("caption data"," ==> "+data);


                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.e("tag", "GetCaption -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//           JsonResponse="[{\"AccCode\":\"0\",\"ItemNCode\":\"2600000003\",\"ItemOCode\":\"2600000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2600000004\",\"ItemOCode\":\"2600000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2600000005\",\"ItemOCode\":\"2600000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000000\",\"ItemOCode\":\"3000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000001\",\"ItemOCode\":\"3000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000002\",\"ItemOCode\":\"3000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000003\",\"ItemOCode\":\"3000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000004\",\"ItemOCode\":\"3000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3000000005\",\"ItemOCode\":\"3000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000000\",\"ItemOCode\":\"5000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000001\",\"ItemOCode\":\"5000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000002\",\"ItemOCode\":\"5000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000003\",\"ItemOCode\":\"5000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000004\",\"ItemOCode\":\"5000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"5000000005\",\"ItemOCode\":\"5000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000000\",\"ItemOCode\":\"2800000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000001\",\"ItemOCode\":\"2800000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000002\",\"ItemOCode\":\"2800000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000003\",\"ItemOCode\":\"2800000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000004\",\"ItemOCode\":\"2800000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2800000005\",\"ItemOCode\":\"2800000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000000\",\"ItemOCode\":\"1000000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000001\",\"ItemOCode\":\"1000000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000002\",\"ItemOCode\":\"1000000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000003\",\"ItemOCode\":\"1000000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000004\",\"ItemOCode\":\"1000000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1000000005\",\"ItemOCode\":\"1000000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000000\",\"ItemOCode\":\"1100000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000001\",\"ItemOCode\":\"1100000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000002\",\"ItemOCode\":\"1100000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1100000003\",\"ItemOCode\":\"1100000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000000\",\"ItemOCode\":\"2200000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000001\",\"ItemOCode\":\"2200000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000002\",\"ItemOCode\":\"2200000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"2200000003\",\"ItemOCode\":\"2200000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800000\",\"ItemOCode\":\"7708800000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800001\",\"ItemOCode\":\"7708800001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800002\",\"ItemOCode\":\"7708800002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"7708800003\",\"ItemOCode\":\"7708800003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"200\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180000\",\"ItemOCode\":\"2200180000\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180001\",\"ItemOCode\":\"2200180001\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180002\",\"ItemOCode\":\"2200180002\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180003\",\"ItemOCode\":\"2200180003\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180004\",\"ItemOCode\":\"2200180004\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180005\",\"ItemOCode\":\"2200180005\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180006\",\"ItemOCode\":\"2200180006\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180007\",\"ItemOCode\":\"2200180007\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180008\",\"ItemOCode\":\"2200180008\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180009\",\"ItemOCode\":\"2200180009\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180010\",\"ItemOCode\":\"2200180010\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180011\",\"ItemOCode\":\"2200180011\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180012\",\"ItemOCode\":\"2200180012\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180013\",\"ItemOCode\":\"2200180013\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200180014\",\"ItemOCode\":\"2200180014\",\"ItemNameA\":\"SHIRT SLIM\",\"ItemNameE\":\"SHIRT SLIM\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"65\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190000\",\"ItemOCode\":\"2200190000\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190001\",\"ItemOCode\":\"2200190001\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190002\",\"ItemOCode\":\"2200190002\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190003\",\"ItemOCode\":\"2200190003\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190004\",\"ItemOCode\":\"2200190004\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190005\",\"ItemOCode\":\"2200190005\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190006\",\"ItemOCode\":\"2200190006\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190007\",\"ItemOCode\":\"2200190007\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190008\",\"ItemOCode\":\"2200190008\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190009\",\"ItemOCode\":\"2200190009\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190010\",\"ItemOCode\":\"2200190010\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190011\",\"ItemOCode\":\"2200190011\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190012\",\"ItemOCode\":\"2200190012\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190013\",\"ItemOCode\":\"2200190013\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190014\",\"ItemOCode\":\"2200190014\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190015\",\"ItemOCode\":\"2200190015\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190016\",\"ItemOCode\":\"2200190016\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190017\",\"ItemOCode\":\"2200190017\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190018\",\"ItemOCode\":\"2200190018\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200190019\",\"ItemOCode\":\"2200190019\",\"ItemNameA\":\"BLUES\",\"ItemNameE\":\"BLUES\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200000\",\"ItemOCode\":\"2200200000\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200001\",\"ItemOCode\":\"2200200001\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200002\",\"ItemOCode\":\"2200200002\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200003\",\"ItemOCode\":\"2200200003\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1100110002\",\"ItemOCode\":\"1100110002\",\"ItemNameA\":\"BOXSER\",\"ItemNameE\":\"BOXSER\",\"InDate\":\"13\\/04\\/2023 10:06:07 م\",\"F_D\":\"5\",\"AVLQTY\":\"78\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200004\",\"ItemOCode\":\"2200200004\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200005\",\"ItemOCode\":\"2200200005\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200006\",\"ItemOCode\":\"2200200006\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200007\",\"ItemOCode\":\"2200200007\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200008\",\"ItemOCode\":\"2200200008\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200009\",\"ItemOCode\":\"2200200009\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200010\",\"ItemOCode\":\"2200200010\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200011\",\"ItemOCode\":\"2200200011\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200012\",\"ItemOCode\":\"2200200012\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200013\",\"ItemOCode\":\"2200200013\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200014\",\"ItemOCode\":\"2200200014\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200015\",\"ItemOCode\":\"2200200015\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200016\",\"ItemOCode\":\"2200200016\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"0\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200017\",\"ItemOCode\":\"2200200017\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200018\",\"ItemOCode\":\"2200200018\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200019\",\"ItemOCode\":\"2200200019\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"2200200020\",\"ItemOCode\":\"2200200020\",\"ItemNameA\":\"BLAZER\",\"ItemNameE\":\"BLAZER\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"250\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000000\",\"ItemOCode\":\"1552000000\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"1\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000001\",\"ItemOCode\":\"1552000001\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000002\",\"ItemOCode\":\"1552000002\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000003\",\"ItemOCode\":\"1552000003\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000004\",\"ItemOCode\":\"1552000004\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000005\",\"ItemOCode\":\"1552000005\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000006\",\"ItemOCode\":\"1552000006\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000007\",\"ItemOCode\":\"1552000007\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000008\",\"ItemOCode\":\"1552000008\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000009\",\"ItemOCode\":\"1552000009\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000010\",\"ItemOCode\":\"1552000010\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000011\",\"ItemOCode\":\"1552000011\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000012\",\"ItemOCode\":\"1552000012\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000013\",\"ItemOCode\":\"1552000013\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000014\",\"ItemOCode\":\"1552000014\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000015\",\"ItemOCode\":\"1552000015\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000016\",\"ItemOCode\":\"1552000016\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000017\",\"ItemOCode\":\"1552000017\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000018\",\"ItemOCode\":\"1552000018\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"4\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000019\",\"ItemOCode\":\"1552000019\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"\",\"ItemNCode\":\"1552000020\",\"ItemOCode\":\"1552000020\",\"ItemNameA\":\"TROUSERS\",\"ItemNameE\":\"TROUSERS\",\"InDate\":\"15\\/04\\/2023\",\"F_D\":\"55\",\"AVLQTY\":\"2\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1289900016\",\"ItemOCode\":\"1289900016\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800000\",\"ItemOCode\":\"1283800000\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800001\",\"ItemOCode\":\"1283800001\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800002\",\"ItemOCode\":\"1283800002\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800003\",\"ItemOCode\":\"1283800003\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800004\",\"ItemOCode\":\"1283800004\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800005\",\"ItemOCode\":\"1283800005\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800006\",\"ItemOCode\":\"1283800006\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800007\",\"ItemOCode\":\"1283800007\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800008\",\"ItemOCode\":\"1283800008\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800009\",\"ItemOCode\":\"1283800009\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800010\",\"ItemOCode\":\"1283800010\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800011\",\"ItemOCode\":\"1283800011\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800012\",\"ItemOCode\":\"1283800012\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800013\",\"ItemOCode\":\"1283800013\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800014\",\"ItemOCode\":\"1283800014\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283800015\",\"ItemOCode\":\"1283800015\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040000\",\"ItemOCode\":\"1283040000\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040001\",\"ItemOCode\":\"1283040001\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040002\",\"ItemOCode\":\"1283040002\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040003\",\"ItemOCode\":\"1283040003\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040004\",\"ItemOCode\":\"1283040004\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040005\",\"ItemOCode\":\"1283040005\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040006\",\"ItemOCode\":\"1283040006\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040007\",\"ItemOCode\":\"1283040007\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"1283040008\",\"ItemOCode\":\"1283040008\",\"ItemNameA\":\"BLAZER WOLL\",\"ItemNameE\":\"BLAZER WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"180\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000000\",\"ItemOCode\":\"3500000000\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000001\",\"ItemOCode\":\"3500000001\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000002\",\"ItemOCode\":\"3500000002\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000003\",\"ItemOCode\":\"3500000003\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"0\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000004\",\"ItemOCode\":\"3500000004\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"},{\"AccCode\":\"0\",\"ItemNCode\":\"3500000005\",\"ItemOCode\":\"3500000005\",\"ItemNameA\":\"SUIT WOLL\",\"ItemNameE\":\"SUIT WOLL\",\"InDate\":\"18\\/04\\/2023\",\"F_D\":\"225\",\"AVLQTY\":\"1\"}]";
            if (JsonResponse != null && JsonResponse.contains("ItemOCode")) {
                Log.e("CAPTION", "****Success");

                pd.getProgressHelper().setBarColor(Color.parseColor("#1F6301"));
                pd.setTitleText(context.getResources().getString(R.string.DataGet));



                try{

                    List<CaptionItemInfo> captionItem=new ArrayList<>();
                    JSONArray parentArray = new JSONArray(JsonResponse);
                    for (int i=0;i<parentArray.length();i++){
                        JSONObject finalObject = parentArray.getJSONObject(i);

                        CaptionItemInfo captionItemInfo=new CaptionItemInfo();
//{"AccCode":"0","ItemNCode":"7708800120","ItemOCode":"7708800120","ItemNameA":"SUIT WOLL","ItemNameE":"SUIT WOLL","InDate":"18\/04\/2023","F_D":"200","AVLQTY":"1"}
                        captionItemInfo.setItemNCode(finalObject.getString("ItemNCode"));
                        captionItemInfo.setAccCode(finalObject.getString("AccCode"));
                        captionItemInfo.setItemOCode(finalObject.getString("ItemOCode"));
                        captionItemInfo.setInDate(finalObject.getString("InDate"));
                        captionItemInfo.setItemNameA(finalObject.getString("ItemNameA"));
                        captionItemInfo.setItemNameE(finalObject.getString("ItemNameE"));
                        captionItemInfo.setF_D(finalObject.getString("F_D"));
                        captionItemInfo.setAVLQTY(finalObject.getString("AVLQTY"));

                        //"ITEMUNIT": "",
                        ////        "ITEMGROUP": "BLUES",
                        ////        "ITEMCOLOR": "BLUE",
                        ////        "ITEMSIZE": "XL",
                        ////        "ITEMMODEL": "10454",
                        ////        "ITEMGS": "",
                        ////        "ITEMDIV": "",
                        ////        "ITEMSUB1": "",
                        ////        "ITEMSUB2": "",
                        ////        "ITEMSUB3": "",
                        ////        "ITEMSUB4": "",
                        ////        "ITEMSUB5": "",
                        ////        "ITEMSUB6": "",
                        ////        "ITEMSUB7": ""

                        try {
                            captionItemInfo.setITEMUNIT(finalObject.getString("ITEMUNIT"));
                            captionItemInfo.setITEMGROUP(finalObject.getString("ITEMGROUP"));
                            captionItemInfo.setITEMCOLOR(finalObject.getString("ITEMCOLOR"));
                            captionItemInfo.setITEMSIZE(finalObject.getString("ITEMSIZE"));
                            captionItemInfo.setITEMMODEL(finalObject.getString("ITEMMODEL"));
                            captionItemInfo.setITEMGS(finalObject.getString("ITEMGS"));
                            captionItemInfo.setITEMDIV(finalObject.getString("ITEMDIV"));
                            captionItemInfo.setITEMSUB1(finalObject.getString("ITEMSUB1"));
                            captionItemInfo.setITEMSUB2(finalObject.getString("ITEMSUB2"));
                            captionItemInfo.setITEMSUB3(finalObject.getString("ITEMSUB3"));
                            captionItemInfo.setITEMSUB4(finalObject.getString("ITEMSUB4"));
                            captionItemInfo.setITEMSUB5(finalObject.getString("ITEMSUB5"));
                            captionItemInfo.setITEMSUB6(finalObject.getString("ITEMSUB6"));
                            captionItemInfo.setITEMSUB7(finalObject.getString("ITEMSUB7"));
                        }catch (Exception r){

                        }
                        captionItem.add(captionItemInfo);

                    }

                    ItemTransaction captionLayout=(ItemTransaction) context;
                    captionLayout.fillArrayDataAfterFillters(captionItem);



                    try {
                        pd.dismiss();
                    }catch (Exception e){
                        Log.e("caption_ErrorD","ErrorDialog ==> "+e.toString());

                    }


                }catch (Exception e){
                    Log.e("caption_ErrorD","Error ==> "+e.toString());
                }



            } else {
                Log.e("TAG_GetStor", "****Failed to export data");
//                if (!JsonResponse.contains("<title>Title of the document</title>")) {
                if (pd != null) {
                    pd.dismiss();
//                    }
//                }else{
                    Toast.makeText(context, "no Parameter", Toast.LENGTH_SHORT).show();

                    ItemTransaction captionLayout=(ItemTransaction) context;
                    captionLayout.fillArrayDataAfterFillters(new ArrayList<CaptionItemInfo>());

//                    if (pd != null) {
//                        pd.dismiss();
//
//                    }
                }
            }


        }
    }


}
