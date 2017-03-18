package com.km.base;

import java.util.List;

/**
 * 运行时权限接口
 */

public interface RuntimePermissionListener {

    /**
     * 允许所请求的全部权限
     */
    void onRuntimePermissionGranted();

    /**
     * 拒绝所请求的部分或全部权限
     */
    void onRuntimePermissionDenied(List<String> deniedPermission);
}