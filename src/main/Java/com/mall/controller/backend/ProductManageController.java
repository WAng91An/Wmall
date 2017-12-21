package com.mall.controller.backend;

import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.pojo.Product;
import com.mall.pojo.User;
import com.mall.service.IProductService;
import com.mall.service.IUserService;
import com.mall.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 王乾 on 2017/12/19.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;

    /**
     *增加或者更新产品
     * @param session
     * @param product
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要登陆管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //增加产品接口
            return iProductService.saveOrUpdateProduct(product);
        }else{
            return  ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 更改商品的销售状态
     * @param session
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要登陆管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //产品上下架
            return iProductService.setSaleStatus(productId,status);
        }else{
            return  ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 获取商品详细信息
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要登陆管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
           //获取商品详细信息
            return  iProductService.manageProductDetail(productId);
        }else{
            return  ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 后台查询分页商品list
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")  int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要登陆管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
           //to do
            return  iProductService.getProductList(pageNum,pageSize);
        }else{
            return  ServerResponse.createByErrorMessage("无权限操作");
        }
    }


}
