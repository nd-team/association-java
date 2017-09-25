package com.bjike.act.addressBook;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.addressBook.AddressBookDTO;
import com.bjike.ser.addressBook.AddressBookSer;
import com.bjike.to.addressBook.AddressBookTO;
import com.bjike.vo.addressBook.AddressBookVO;
import com.bjike.vo.addressBook.SearchVO;
import com.bjike.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通讯录
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-31 11:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("addressBook/address/book")
public class AddressBookAct {
    @Autowired
    private AddressBookSer addressBookSer;

    /**
     * 搜索通讯录
     *
     * @param name 姓名
     * @return class SearchVO
     * @throws ActException
     */
    @GetMapping("/search/{name}")
    public Result search(@PathVariable String name) throws ActException {
        try {
            List<SearchVO> list=addressBookSer.search(name);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索全部
     *
     * @param name 姓名
     * @return class SearchVO
     * @throws ActException
     */
    @GetMapping("/find/{name}")
    public Result find(@PathVariable String name) throws ActException {
        try {
            List<SearchVO> list=addressBookSer.find(name);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 可能认识的人
     *
     * @return class SearchVO
     * @throws ActException
     */
    @GetMapping("/may/know")
    public Result mayKnow() throws ActException {
        try {
            List<SearchVO> map=addressBookSer.mayKnow();
            return ActResult.initialize(map);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加通讯录
     *
     * @param to to
     * @throws ActException
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) AddressBookTO to, BindingResult result) throws ActException {
        try {
            addressBookSer.add(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑备注
     *
     * @param bookId 通讯录id
     * @param remark 备注
     * @throws ActException
     */
    @PutMapping("/edit/{bookId}/{remark}")
    public Result edit(@PathVariable String bookId, @PathVariable String remark) throws ActException {
        try {
            addressBookSer.edit(bookId, remark);
            return new ActResult("编辑备注成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除通讯录
     *
     * @param id id
     * @throws ActException
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            addressBookSer.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通讯录列表
     *
     * @param dto dto
     * @return class AddressBookVO
     * @throws ActException
     */
    @GetMapping("/list")
    public Result list(AddressBookDTO dto) throws ActException {
        try {
            List<AddressBookVO> list=addressBookSer.list(dto);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
