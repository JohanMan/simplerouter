package com.johan.library.simplerouter;

import android.net.Uri;

/**
 * Created by johan on 2017/8/11.
 */

public class UriQuery {

    private Uri uri;

    public UriQuery(Uri uri) {
        this.uri = uri;
    }

    public int queryIntValue(String key) {
        return queryIntValue(key, 0);
    }

    public int queryIntValue(String key, int defaultValue) {
        try {
            return Integer.parseInt(uri.getQueryParameter(key));
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public long queryLongValue(String key) {
        return queryLongValue(key, 0);
    }

    public long queryLongValue(String key, long defaultValue) {
        try {
            return Long.parseLong(uri.getQueryParameter(key));
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public float queryFloatValue(String key) {
        return queryFloatValue(key, 0);
    }

    public float queryFloatValue(String key, float defaultValue) {
        try {
            return Float.parseFloat(uri.getQueryParameter(key));
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public double queryDoubleValue(String key) {
        return queryDoubleValue(key, 0);
    }

    public double queryDoubleValue(String key, double defaultValue) {
        try {
            return Double.parseDouble(uri.getQueryParameter(key));
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public boolean queryBooleanValue(String key) {
        return queryBooleanValue(key, false);
    }

    public boolean queryBooleanValue(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(uri.getQueryParameter(key));
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    public String queryStringValue(String key) {
        return queryStringValue(key, null);
    }

    public String queryStringValue(String key, String defaultValue) {
        String value = uri.getQueryParameter(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

}
