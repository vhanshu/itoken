package com.common.common.hystrix;

import com.common.common.constants.HttpStatusConstants;
import com.common.common.dto.BaseResult;
import com.common.common.utils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * @author vhans
 */
public class Fallback {

    /**
     * 502错误
     * @return
     */
    public static String badGateway(){
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult
                .Error(String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
