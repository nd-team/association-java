package com.bjike.ser.search;

import com.bjike.common.exception.SerException;
import com.bjike.dto.search.SearchDTO;
import com.bjike.entity.search.Search;
import com.bjike.ser.Ser;
import com.bjike.vo.search.FindVO;
import com.bjike.vo.search.SearchUserVO;

import java.util.List;

/**
 * 后台找人
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-05 13:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface SearchSer extends Ser<Search, SearchDTO> {
    /**
     * 同意找人
     *
     * @param id
     * @return
     * @throws SerException
     */
    SearchUserVO agree(String id) throws SerException;

    /**
     * 搜索
     *
     * @param name
     * @return
     * @throws SerException
     */
    List<FindVO> search(String name) throws SerException;

    /**
     * 认识他
     *
     * @param id
     * @return
     * @throws SerException
     */
    FindVO know(String id) throws SerException;

    /**
     * 拒绝被认识
     *
     * @param id id
     * @throws SerException
     */
    void reject(String id) throws SerException;

    /**
     * 被找人列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FindVO> list(SearchDTO dto) throws SerException;
}
