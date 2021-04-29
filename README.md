# Internet Checker

## This library to check internet connection on android using java language.

### How To Use:
1. Add maven to build.gradle all projects `maven { url 'https://jitpack.io' }`
2. Add dependencies to build.gradle module `implementation 'com.github.MohamedSlama:internetchecker:1.2'`
3. Add internet permissions:<br>
  -` <uses-permission android:name="android.permission.INTERNET"/> ` <br>
  -` <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/> `
4. Done !!

### How To implement:
1. Simply call ` new CheckInternet(getApplicationContext(), MainActivity.this).execute(); `
2. Note add ` implements AsyncResponse ` to your ` MainActivity `
3. It will require you to Override ` processFinish(Boolean output) `
4. Done !!

### Samples:
1. Check internet and change status bar color:
```java
@Override
    public void processFinish(Boolean output) {
        if (output) {
            Log.d("TAG", "Internet status is connected");
            getWindow().setStatusBarColor(Color.GREEN);
        } else {
            Log.d("TAG", "Internet status is disconnected");
            getWindow().setStatusBarColor(Color.RED);
        }
    }
```

2. Check internet continuously:
```java
handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    new CheckInternet(getApplicationContext(), MainActivity.this)
                            .execute();
                    handler.postDelayed(this, 1000);
                } catch (ExceptionInInitializerError e) {
                    e.printStackTrace();
                }
            }
        });
```
