package com.bow.spring.springmvc.customize;

/**
 * @author vv
 * @since 2017/2/3.
 */
@MyController
public class CustomizedController {

    @MyRequestMapping("/customize")
    @MyResponseBody
    public ResponseData customize(@MyRequestBody RequestData requestData) {
        System.out.println(requestData);
        ResponseData responseData = new ResponseData();
        responseData.setData(requestData.getData());
        return responseData;
    }
}
