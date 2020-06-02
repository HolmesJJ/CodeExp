package com.example.codeexp.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;
import androidx.databinding.Observable.OnPropertyChangedCallback;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.codeexp.listener.OnMultiClickListener;

import java.util.HashMap;
import java.util.Map;

public class ListenerUtils {

    private static Map<Integer, OnPropertyChangedCallback> sCallbackMap = new HashMap<>();

    public static void addSingalOnPropertyChangeCallback(final BaseObservable baseObservable,
                                                         final Callback callback) {

        if (baseObservable == null) {
            return;
        }
        OnPropertyChangedCallback onPropertyChangedCallback = sCallbackMap.get(baseObservable.hashCode());

        if (onPropertyChangedCallback != null) {
            baseObservable.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
        OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {

            @Override
            public void onPropertyChanged(Observable observable, int i) {

                if (observable == null) {
                    return;
                }
                if (callback != null) {
                    callback.callback(observable, i);
                }

            }
        };
        baseObservable.addOnPropertyChangedCallback(propertyChangedCallback);
        sCallbackMap.put(baseObservable.hashCode(), propertyChangedCallback);
    }

    public static void addSingalOnPropertyChangeCallback(ObservableBoolean observableBoolean,
                                                         final BooleanCallback callback) {

        if (observableBoolean == null) {
            return;
        }
        OnPropertyChangedCallback onPropertyChangedCallback = sCallbackMap.get(observableBoolean.hashCode());

        if (onPropertyChangedCallback != null) {
            observableBoolean.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }

        OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {

            @Override
            public void onPropertyChanged(Observable observable, int i) {

                if (observable == null || !(observable instanceof ObservableBoolean)) {
                    return;
                }
                if (callback != null) {
                    callback.callback(observable, i, ((ObservableBoolean) observable).get());
                }

            }
        };
        observableBoolean.addOnPropertyChangedCallback(propertyChangedCallback);
        sCallbackMap.put(observableBoolean.hashCode(), propertyChangedCallback);
    }

    public static void addSingalOnPropertyChangeCallback(ObservableField<String> observable,
                                                         final StringCallback callback) {

        if (observable == null) {
            return;
        }
        OnPropertyChangedCallback onPropertyChangedCallback = sCallbackMap.get(observable.hashCode());

        if (onPropertyChangedCallback != null) {
            observable.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
        OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {

            @Override
            public void onPropertyChanged(Observable observable, int i) {

                if (observable == null || !(observable instanceof ObservableField)) {
                    return;
                }
                if (callback != null) {
                    callback.callback(observable, i, ((ObservableField<String>) observable).get());
                }

            }
        };
        observable.addOnPropertyChangedCallback(propertyChangedCallback);
        sCallbackMap.put(observable.hashCode(), propertyChangedCallback);
    }

    public static void addOnPropertyChangeCallback(final BaseObservable baseObservable, final
    Callback callback) {

        if (baseObservable == null) {
            return;
        }
        OnPropertyChangedCallback propertyChangedCallback = new OnPropertyChangedCallback() {

            @Override
            public void onPropertyChanged(Observable observable, int i) {

                if (baseObservable == null) {
                    return;
                }
                if (callback != null) {
                    callback.callback(observable, i);
                }

            }
        };
        baseObservable.addOnPropertyChangedCallback(propertyChangedCallback);
    }

    public static void addTextChangeListener(EditText editText, final TextChange textChange) {

        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && textChange != null) {
                    textChange.textChange(s.toString());
                }

            }
        });
    }

    public static void setOnClickListener(View view, OnMultiClickListener listener) {

        if (view == null) {
            return;
        }
        view.setOnClickListener(listener);
    }

    public static void setOnClickListener(View view, View.OnClickListener listener) {

        if (view == null) {
            return;
        }
        view.setOnClickListener(listener);
    }

    public static void setCheckChangeListener(CompoundButton view, CompoundButton.OnCheckedChangeListener
            listener) {

        if (view == null) {
            return;
        }
        view.setOnCheckedChangeListener(listener);
    }

    public static void setFocusChangeListener(View view, View.OnFocusChangeListener
            listener) {

        if (view == null) {
            return;
        }
        view.setOnFocusChangeListener(listener);
    }

    public static void remove(ObservableBoolean observableBoolean) {

        if (sCallbackMap != null) {
            sCallbackMap.remove(observableBoolean.hashCode());
        }
    }

    public interface TextChange {

        void textChange(String s);
    }

    public interface Callback {

        void callback(Observable observable, int i);
    }

    public interface BooleanCallback {

        void callback(Observable observable, int i, boolean value);
    }

    public interface StringCallback {

        void callback(Observable observable, int i, String value);
    }
}
