package com.xpple.fruits.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * APP个性化配置管理工具
 *
 * @author nEdAy
 */
public class SharedPreferencesHelper {
    private static final String PREFERENCE_SETTINGS = "_Settings";
    private static final String SETTING_FIRST = "setting_first";
    private static final String SETTING_NOTIFY = "setting_notify";
    private static final String SETTING_VOICE = "setting_voice";
    private static final String SETTING_VIBRATE = "setting_vibrate";
    private static final String SETTING_QUIET = "setting_quiet";
    private static final String SETTING_QUIET_PERIOD = "setting_quiet_period";
    private static final String SETTING_SHAKE_VOICE = "setting_shake_voice";
    private static final String SETTING_PROVINCE_FLOW_MODEL = "setting_province_flow_model ";
    private static final String USER_ID = "user_id";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_NAME = "user_name";
    private static final String USER_NICKNAME = "user_nickname";
    private static final String USER_LOGINKEY = "user_loginkey";
    private static final String USER_TOKEN = "user_token";
    private static final String USER_AREA_NAME = "user_area_name";
    private static final String USER_AREA_ID = "user_area_id";
    private static final String USER_GET_COIN = "user_get_coin";
    private static final String USER_GOLD_COIN = "user_gold_coin";
    private static final String USER_RECEIVER = "user_receiver";
    private static final String USER_RECEIVER_PHONE = "user_receiver_phone";
    private static final String USER_RECEIVER_AREA = "user_receiver_area";
    private static final String USER_RECEIVER_ADDRESS = "user_receiver_address";

    private static final String CENTER_BG = "center_bg";
    private static SharedPreferences.Editor editor;
    private final SharedPreferences mSharedPreferences;

    /**
     * 初始化SharedPreferences
     *
     * @param context 上下文
     */
    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_SETTINGS,
                Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    /**
     * 是否首次导航
     */
    public boolean isAllowFirst() {
        return mSharedPreferences.getBoolean(SETTING_FIRST, true);
    }

    public void setAllowFirstEnable(boolean isFirst) {
        editor.putBoolean(SETTING_FIRST, isFirst);
        editor.commit();
    }


    /**
     * 是否允许推送通知
     */
    public boolean isAllowPushNotify() {
        return mSharedPreferences.getBoolean(SETTING_NOTIFY, true);
    }

    public void setPushNotifyEnable(boolean isChecked) {
        editor.putBoolean(SETTING_NOTIFY, isChecked);
        editor.commit();
    }

    /**
     * 是否允许声音
     */
    public boolean isAllowVoice() {
        return mSharedPreferences.getBoolean(SETTING_VOICE, true);
    }

    public void setAllowVoiceEnable(boolean isChecked) {
        editor.putBoolean(SETTING_VOICE, isChecked);
        editor.commit();
    }

    /**
     * 是否允许震动
     */
    public boolean isAllowVibrate() {
        return mSharedPreferences.getBoolean(SETTING_VIBRATE, true);
    }

    public void setAllowVibrateEnable(boolean isChecked) {
        editor.putBoolean(SETTING_VIBRATE, isChecked);
        editor.commit();
    }

    /**
     * 允许靜音
     */
    public boolean isAllowQuiet() {
        return mSharedPreferences.getBoolean(SETTING_QUIET, true);
    }

    public void setAllowQuietEnable(boolean isChecked) {
        editor.putBoolean(SETTING_QUIET, isChecked);
        editor.commit();
    }

    /**
     * 靜音時段
     */
    public String getQuitePeriod() {
        return mSharedPreferences.getString(SETTING_QUIET_PERIOD, "[0,0,6,0]");
    }

    public void setQuitePeriod(String quitePeriod) {
        editor.putString(SETTING_QUIET_PERIOD, quitePeriod);
        editor.commit();
    }

    /**
     * 是否允许摇一摇声音
     */
    public boolean isAllowShakeVoice() {
        return mSharedPreferences.getBoolean(SETTING_SHAKE_VOICE, false);
    }

    public void setAllowShakeVoiceEnable(boolean isChecked) {
        editor.putBoolean(SETTING_SHAKE_VOICE, isChecked);
        editor.commit();
    }

    /**
     * 是否开启省流模式
     */
    public boolean isAllowProvinceFlowModel() {
        return mSharedPreferences.getBoolean(SETTING_PROVINCE_FLOW_MODEL, false);
    }

    public void setAllowProvinceFlowModelEnable(boolean isProvinceFlowModel) {
        editor.putBoolean(SETTING_PROVINCE_FLOW_MODEL, isProvinceFlowModel);
        editor.commit();
    }

    /**
     * 记录用户Id
     * @return
     */
    public int getUserId()
    {
        return mSharedPreferences.getInt(USER_ID,0);
    }

    public void setUserId(int userId)
    {
        editor.putInt(USER_ID,userId);
        editor.commit();
    }

    /**
     * 记录登录用户的手机号码
     */
    public String getUserPhone() {
        return mSharedPreferences.getString(USER_PHONE, "");
    }

    public void setUserPhone(String userPhone) {
        editor.putString(USER_PHONE, userPhone);
        editor.commit();
    }

    /**
     * 记录登录的用户名
     * @return
     */
    public String getUserName(){return  mSharedPreferences.getString(USER_NAME,"");}

    public void setUserName(String username)
    {
        editor.putString(USER_NAME, username);
        editor.commit();
    }

    /**
     * 记录登录的昵称
     * @return
     */
    public String getUserNickname(){return  mSharedPreferences.getString(USER_NICKNAME,"");}

    public void setUserNickname(String nickname)
    {
        editor.putString(USER_NICKNAME, nickname);
        editor.commit();
    }

    /**
     * 记录登录的key
     * @return
     */
    public String getUserLoginkey(){return  mSharedPreferences.getString(USER_LOGINKEY,"");}

    public void setUserLoginkey(String loginkey)
    {
        editor.putString(USER_LOGINKEY, loginkey);
        editor.commit();
    }

    /**
     * 记录登录的token
     * @return
     */
    public String getUserToken(){return  mSharedPreferences.getString(USER_TOKEN,"");}

    public void setUserToken(String token)
    {
        editor.putString(USER_TOKEN, token);
        editor.commit();
    }

    /**
     *记录用户区域Id
     * @return
     */
    public int getUserAreaId(){return mSharedPreferences.getInt(USER_AREA_ID,0);}

    public void setUserAreaId(int area)
    {
        editor.putInt(USER_AREA_ID,area);
        editor.commit();
    }

    /**
     *记录用户区域名
     * @return
     */
    public String getUserAreaName(){return mSharedPreferences.getString(USER_AREA_NAME,"");}

    public void setUserAreaName(String area)
    {
        editor.putString(USER_AREA_NAME,area);
        editor.commit();
    }

    public int getUserGetCoin()
    {
        return mSharedPreferences.getInt(USER_GET_COIN,0);
    }

    public void setUserGetCoin(int getCoin)
    {
        editor.putInt(USER_GET_COIN,getCoin);
        editor.commit();
    }

    public int getUserGoldCoin()
    {
        return mSharedPreferences.getInt(USER_GOLD_COIN,0);
    }

    public void setUserGoldCoin(int goldCoin)
    {
        editor.putInt(USER_GOLD_COIN,goldCoin);
        editor.commit();
    }

    public String getUserReceiver()
    {
        return mSharedPreferences.getString(USER_RECEIVER,"");
    }

    public void setUserReceiver(String receiver)
    {
        editor.putString(USER_RECEIVER,receiver);
    }

    public String getUserReceiverPhone()
    {
        return mSharedPreferences.getString(USER_RECEIVER_PHONE,"");
    }

    public void setUserReceiverPhone(String phone)
    {
        editor.putString(USER_RECEIVER_PHONE,phone);
        editor.commit();
    }

    public String getUserReceiverArea()
    {
        return mSharedPreferences.getString(USER_RECEIVER_AREA,"");
    }

    public void setUserReceiverArea(String area)
    {
        editor.putString(USER_RECEIVER_AREA,area);
        editor.commit();
    }

    public String getUserReceiverAddress()
    {
        return mSharedPreferences.getString(USER_RECEIVER_ADDRESS,"");
    }

    public void setUserReceiverAddress(String address)
    {
        editor.putString(USER_RECEIVER_ADDRESS,address);
        editor.commit();
    }

    /**
     * 用户选择设置的背景图片序数
     */
    public int getCenterBg() {
        return mSharedPreferences.getInt(CENTER_BG, 2);
    }

    public void setCenterBg(int centerBg) {
        editor.putInt(CENTER_BG, centerBg);
        editor.commit();
    }
}
