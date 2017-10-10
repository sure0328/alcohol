package com.sinolease.base.core;

import com.alibaba.fastjson.JSONObject;
import com.sinolease.base.entity.ErrorCode;
import com.sinolease.base.entity.ResponseObj;
import com.sinolease.base.util.StringUtils;

/**
 * Created by AlbertLy on 2016/11/15.
 */
public class BaseController {

    protected ResponseObj getSuccessResponse(Object data) {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setErrno(ErrorCode.SUCCESS);
        responseObj.setSuccessmsg("操作成功");
        responseObj.setErrmsg(StringUtils.EMPTY);
        responseObj.setData(data);
        return responseObj;
    }

    protected ResponseObj getErrorResponse(int errorCode, String errorMessage) {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setErrno(errorCode);
        responseObj.setSuccessmsg(StringUtils.EMPTY);
        responseObj.setErrmsg(errorMessage);
        responseObj.setData(null);
        return responseObj;
    }

    protected JSONObject getRedirectJson(String url) {
        JSONObject jsonObjreturn = new JSONObject();
        jsonObjreturn.put("redirectUrl", url);
        return jsonObjreturn;
    }

    protected ResponseObj getSysErrorResponse() {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setErrno(ErrorCode.SERVICE_ERROR);
        responseObj.setSuccessmsg(StringUtils.EMPTY);
        responseObj.setErrmsg("系统异常");
        responseObj.setData(null);
        return responseObj;
    }
}
