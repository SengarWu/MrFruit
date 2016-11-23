package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.base.Callback;
import com.xpple.fruits.base.SMSManager;
import com.xpple.fruits.me.presenter.RegisterPresenter;
import com.xpple.fruits.me.presenter.RegisterPresenterImpl;
import com.xpple.fruits.me.view.RegisterView;
import com.xpple.fruits.utils.StringUtil;
import com.xpple.fruits.view.DeletableEditText;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,RegisterView{

    private ImageButton ib_back;
    private TextView tv_title;
    private DeletableEditText et_register_username;
    private DeletableEditText et_register_phone;
    private DeletableEditText et_register_code;
    private DeletableEditText et_register_password;
    private DeletableEditText et_register_invite_code;
    private Button btn_register_code;
    private Button btn_register_confirm;

    private String username;
    private String phone;
    private String code;
    private String password;
    private String inviteCode;
    private static final String TAG = "RegisterActivity";

    private ProgressBar progressBar;

    RegisterPresenter presenter;

    private final int LOGIN_DELAY = 1001;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case LOGIN_DELAY:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        presenter = new RegisterPresenterImpl(this);
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(this);
        tv_title = $(R.id.tv_title);
        tv_title.setText("注册");
        et_register_username = $(R.id.et_register_username);
        et_register_phone = $(R.id.et_register_phone);
        et_register_code = $(R.id.et_register_code);
        et_register_password = $(R.id.et_register_password);
        et_register_invite_code = $(R.id.et_register_invite_code);
        btn_register_code = $(R.id.btn_register_code);
        btn_register_code.setOnClickListener(this);
        btn_register_confirm = $(R.id.btn_register_confirm);
        btn_register_confirm.setOnClickListener(this);

        progressBar = new ProgressBar(this);
    }

    @Override
    public void onClick(View v) {
        username = et_register_username.getText().toString();
        phone = et_register_phone.getText().toString().trim().replace(" ","");
        code = et_register_code.getText().toString();
        password = et_register_password.getText().toString();
        inviteCode = et_register_invite_code.getText().toString();

       switch (v.getId()){
           case R.id.ib_back:
               finish();
               break;
           case R.id.btn_register_code://点击发送验证码
                if (TextUtils.isEmpty(phone))
                {
                    JUtils.Toast("请输入手机号");
                    et_register_phone.setShakeAnimation();
                    return;
                }
                if (!StringUtil.isPhoneNumberValid(phone))
                {
                    JUtils.Toast("手机号格式不正确");
                    et_register_phone.setShakeAnimation();
                    return;
                }
               if (!isNetConnected())
               {
                   JUtils.Toast("当前网络不可用，请检查网络");
                   return;
               }
               timer.start();
               final SMSManager smsManager = new SMSManager(this);
               //获取验证码
               smsManager.getSMS(phone, new Callback() {
                   @Override
                   public void success() {
                       JUtils.Toast("验证码发送成功！");
                       smsManager.uninit();
                   }

                   @Override
                   public void error(Throwable error) {
                       JUtils.Toast("验证码发送失败！"+error);
                       timer.onFinish();
                       smsManager.uninit();
                   }
               });

               break;
           case R.id.btn_register_confirm://点击注册
                if (TextUtils.isEmpty(username))
                {
                    JUtils.Toast("请输入用户名");
                    et_register_username.setShakeAnimation();
                    return;
                }
               if (username.length() < 5)
               {
                   JUtils.Toast("用户名至少为5位哦");
                   et_register_username.setShakeAnimation();
                   return;
               }
               if (TextUtils.isEmpty(phone))
               {
                   JUtils.Toast("请输入手机号");
                   et_register_phone.setShakeAnimation();
                   return;
               }
               if (!StringUtil.isPhoneNumberValid(phone))
               {
                   JUtils.Toast("手机号格式不正确");
                   et_register_phone.setShakeAnimation();
                   return;
               }
               if (TextUtils.isEmpty(code))
               {
                   JUtils.Toast("请输入验证码！");
                   et_register_code.setShakeAnimation();
                   return;
               }
               if (TextUtils.isEmpty(password))
               {
                   JUtils.Toast("请输入密码！");
                   et_register_password.setShakeAnimation();
                   return;
               }
               if (!isNetConnected())
               {
                   JUtils.Toast("当前网络不可用，请检查网络");
                   return;
               }
               //用户注册
               presenter.register(username,phone,password,code);
               break;
       }
    }

    CountDownTimer timer = new CountDownTimer(60000,1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            btn_register_code.setClickable(false);
            btn_register_code.setText("重新获取("+millisUntilFinished/1000+")");
        }

        @Override
        public void onFinish() {
            btn_register_code.setClickable(true);
            btn_register_code.setText("重新获取");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void registerSuccess(String message) {
        Log.e(TAG, "registerSuccess: "+message);
        //只负责显示数据
        JUtils.Toast("注册成功！"+message);
        clearView();
        handler.sendEmptyMessageDelayed(LOGIN_DELAY,1000);
    }

    private void clearView() {
        et_register_username.setText("");
        et_register_password.setText("");
        et_register_phone.setText("");
        et_register_code.setText("");
        et_register_invite_code.setText("");
    }

    @Override
    public void registerFail(String message) {
        JUtils.Toast("注册失败！"+message);
    }
}
