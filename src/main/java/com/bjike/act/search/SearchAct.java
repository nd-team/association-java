package com.bjike.act.search;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.search.SearchDTO;
import com.bjike.ser.search.SearchSer;
import com.bjike.vo.search.FindVO;
import com.bjike.vo.search.SearchUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台找人
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 11:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("search")
public class SearchAct {
    @Autowired
    private SearchSer searchSer;

    /**
     * 同意找人
     *
     * @param id id
     * @return class SearchUserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("/agree/{id}")
    public Result agree(@PathVariable String id) throws ActException {
        try {
            SearchUserVO vo = searchSer.agree(id);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *@version v1
     * @param name 搜索关键字
     * @return class FindVO
     * @throws ActException
     */
    @GetMapping("/search/{name}")
    public Result search(@PathVariable String name) throws ActException {
        try {
            return ActResult.initialize(searchSer.search(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 认识他
     *
     * @param id id
     * @return class FindVO
     * @throws ActException
     */
    @PostMapping("/know/{id}")
    public Result know(@PathVariable String id) throws ActException {
        try {
            FindVO vo = searchSer.know(id);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 拒绝被认识
     *
     * @param id id
     * @throws ActException
     */
    @PutMapping("/reject/{id}")
    public Result reject(@PathVariable String id) throws ActException {
        try {
            searchSer.reject(id);
            return new ActResult("拒绝成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 被找人列表
     *
     * @param dto dto
     * @return class FindVO
     * @throws ActException
     */
    @GetMapping("/list")
    public Result list(SearchDTO dto) throws ActException {
        try {
            return ActResult.initialize(searchSer.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
