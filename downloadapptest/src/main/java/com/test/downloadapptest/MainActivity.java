package com.test.downloadapptest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maning.updatelibrary.InstallUtils;

public class MainActivity extends AppCompatActivity {

    //最新APK的下载地址
    public static final String APK_URL = "https://xinhuagolden.oss-cn-hzfinance.aliyuncs.com/apk/ucxinxiliu03/xhjd.apk";
    //下载后的APK的命名
    public static final String APK_NAME = "update";
    public static String TAG ="MainActivity";
    private Context context;
    private static final int INSTALL_PACKAGES_REQUESTCODE = 100;
    private static final int GET_UNKNOWN_APP_SOURCES = 200;
    private ProgressBar mNumberProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        context = this;
        mNumberProgressBar = findViewById(R.id.pro);
        textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
//                if (Build.VERSION.SDK_INT >= 26) {
//                    boolean b = getPackageManager().canRequestPackageInstalls();
//                    if (b) {
//                        //下载
//                        new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
//                            @Override
//                            public void onStart() {
//                                Log.i(TAG, "InstallUtils---onStart");
//                                mNumberProgressBar.setProgress(0);
//                            }
//
//                            @Override
//                            public void onComplete(String path) {
//                                Log.i(TAG, "InstallUtils---onComplete:" + path);
//                                InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
//                                    @Override
//                                    public void onComplete() {
//                                        Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onFail(Exception e) {
//                                        Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                                mNumberProgressBar.setProgress(100);
//                            }
//
//                            @Override
//                            public void onLoading(long total, long current) {
//                                Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
//                                mNumberProgressBar.setProgress((int) (current * 100 / total));
//                            }
//
//                            @Override
//                            public void onFail(Exception e) {
//                                Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
//                            }
//
//                        }).downloadAPK();
//                    } else {
//                        //请求安装未知应用来源的权限
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_PACKAGES_REQUESTCODE);
//                    }
//                } else {
//                    //下载
//                    new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
//                        @Override
//                        public void onStart() {
//                            Log.i(TAG, "InstallUtils---onStart");
//                            mNumberProgressBar.setProgress(0);
//                        }
//
//                        @Override
//                        public void onComplete(String path) {
//                            Log.i(TAG, "InstallUtils---onComplete:" + path);
//                            InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
//                                @Override
//                                public void onComplete() {
//                                    Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onFail(Exception e) {
//                                    Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            mNumberProgressBar.setProgress(100);
//                        }
//
//                        @Override
//                        public void onLoading(long total, long current) {
//                            Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
//                            mNumberProgressBar.setProgress((int) (current * 100 / total));
//                        }
//
//                        @Override
//                        public void onFail(Exception e) {
//                            Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
//                        }
//
//                    }).downloadAPK();
//                }
//            }
//        });
                                            boolean haveInstallPermission;
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                haveInstallPermission = getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {//没有权限
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                    builder.setTitle("安装应用需要打开未知来源权限，请去设置中开启权限");
                                                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                startInstallPermissionSettingActivity();
                                                            }
                                                        }
                                                    });
                                                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    });
                                                    builder.create().show();
                                                    return;
                                                }
                                            }
                                            //有权限，开始安装应用程序
                                            //下载
                    new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
                        @Override
                        public void onStart() {
                            mNumberProgressBar.setProgress(0);
                        }

                        @Override
                        public void onComplete(String path) {
                            InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
                                @Override
                                public void onComplete() {
                                    Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFail(Exception e) {
                                    Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            mNumberProgressBar.setProgress(100);
                        }

                        @Override
                        public void onLoading(long total, long current) {
                            mNumberProgressBar.setProgress((int) (current * 100 / total));
                        }

                        @Override
                        public void onFail(Exception e) {
                        }

                    }).downloadAPK();
                                        }
                                    });



//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case INSTALL_PACKAGES_REQUESTCODE:
//                //有注册权限且用户允许安装
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    //下载
//                    new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
//                        @Override
//                        public void onStart() {
//                            mNumberProgressBar.setProgress(0);
//                        }
//
//                        @Override
//                        public void onComplete(String path) {
//                            InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
//                                @Override
//                                public void onComplete() {
//                                    Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onFail(Exception e) {
//                                    Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            mNumberProgressBar.setProgress(100);
//                        }
//
//                        @Override
//                        public void onLoading(long total, long current) {
//                            mNumberProgressBar.setProgress((int) (current * 100 / total));
//                        }
//
//                        @Override
//                        public void onFail(Exception e) {
//                        }
//
//                    }).downloadAPK();
//                } else {
//                    //将用户引导至安装未知应用界面。
//                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
//                    startActivityForResult(intent, GET_UNKNOWN_APP_SOURCES);
//                }
//                break;
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        Uri packageURI = Uri.parse("package:" + getPackageName());
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, 10086);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 &&  resultCode == RESULT_OK) {
            //下载
                    new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
                        @Override
                        public void onStart() {
                            mNumberProgressBar.setProgress(0);
                        }

                        @Override
                        public void onComplete(String path) {
                            InstallUtils.installAPK(context, path, new InstallUtils.InstallCallBack() {
                                @Override
                                public void onComplete() {
                                    Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFail(Exception e) {
                                    Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            mNumberProgressBar.setProgress(100);
                        }

                        @Override
                        public void onLoading(long total, long current) {
                            mNumberProgressBar.setProgress((int) (current * 100 / total));
                        }

                        @Override
                        public void onFail(Exception e) {
                        }

                    }).downloadAPK();
        }
    }

}
