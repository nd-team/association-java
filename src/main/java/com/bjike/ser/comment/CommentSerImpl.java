package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.clazz.NumberUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.dto.comment.LikesDTO;
import com.bjike.dto.comment.PictureDTO;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.entity.comment.Likes;
import com.bjike.entity.comment.Picture;
import com.bjike.entity.comment.Shop;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.UserSer;
import com.bjike.to.comment.CommentTO;
import com.bjike.type.comment.ScoreType;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CommentSerImpl extends ServiceImpl<Comment, CommentDTO> implements CommentSer {
    @Autowired
    private ShopSer shopSer;
    @Autowired
    private PictureSer pictureSer;
    @Autowired
    private LikesSer likesSer;
    @Autowired
    private UserSer userSer;

    @Transactional
    @Override
    public Comment add(CommentTO to, List<File> files) throws SerException {
        try {
            Comment comment = BeanCopy.copyProperties(to, Comment.class);
            User user = UserUtil.currentUser(false);
            ;
            comment.setUser(user);
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.getConditions().add(Restrict.eq("pointId", to.getPointId()));
            Shop shop = shopSer.findOne(shopDTO);
            if (null != shop) {
                comment.setShop(shop);

            } else {
                shop = new Shop();
                shop.setLatitude(to.getLatitude());
                shop.setLongitude(to.getLongitude());
                shop.setName(to.getShopName());
                shop.setAddress(to.getAddress());
                shop.setPointId(to.getPointId());
                shopSer.add(shop);
                comment.setShop(shop);
            }
            super.save(comment);
            if (null != files) {
                uploadImg(comment.getId(), files);
            }
            return comment;

        } catch (SerException e) {
            if (null != files) {
                for (File file : files) {
                    file.delete();
                }
            }
            throw new SerException(e.getMessage());
        }


    }

    @Override
    public List<CommentVO> list(CommentDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("shop.pointId", dto.getPointId()));
        dto.getSorts().add("likes");
        List<Comment> comments = super.findByPage(dto);
        List<CommentVO> vos = BeanCopy.copyProperties(comments, CommentVO.class);
        String userId = UserUtil.currentUserID();
        if (null != vos) {
            for (CommentVO comment : vos) {
                comment.setAlreadyLikes(alreadyLike(userId));
                String[] images = getImages(comment.getId());
                User user = userSer.findById(userId);
                comment.setNickname(user.getNickname());
                comment.setHeadPath(user.getHeadPath());
                comment.setImages(images);
            }
        }

        return vos;
    }

    @Override
    public Long count(String pointId) throws SerException {
        if (StringUtils.isBlank(pointId)) {
            throw new SerException("坐标id不能为空");
        }
        CommentDTO dto = new CommentDTO();
        dto.getConditions().add(Restrict.eq("shop.pointId", pointId));
        return super.count(dto);
    }

    @Transactional
    @Override
    public void like(String commentId) throws SerException {
        User user = UserUtil.currentUser(false);
        Comment comment = super.findById(commentId);
        if (null != comment) {
            LikesDTO dto = new LikesDTO();
            dto.getConditions().add(Restrict.eq("user.id", user.getId()));
            if (likesSer.findByCis(dto).size() == 0) {
                comment.setLikes(comment.getLikes() != null ? (comment.getLikes() + 1) : 1);
                super.update(comment);
                Likes likes = new Likes();
                likes.setComment(comment);
                likes.setUser(user);
                likesSer.save(likes);
            } else {
                throw new SerException("你已经点过赞了");
            }
        } else {
            throw new SerException("该评论不存在或已被删除!");
        }
    }

    @Override
    public void cancelLike(String commentId) throws SerException {
        String userId = UserUtil.currentUserID();
        Comment comment = super.findById(commentId);
        LikesDTO dto = new LikesDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        List<Likes> likes = likesSer.findByCis(dto);
        likesSer.remove(likes);
        if (null != comment && null != likes && likes.size() > 0) {
            comment.setLikes(comment.getLikes() != null ? (comment.getLikes() - 1) : 0);
            super.update(comment);
        } else {
            throw new SerException("该评论不存在或已被删除!");
        }
    }

    @Override
    public ScoreType score(String poindId) throws SerException {
        String sql = "select  IFNULL(avg(score_type),0) from comment a,shop b where a.shop_id=b.id and b.point_id='" + poindId + "'";
        List<Object> objects = super.findBySql(sql);
        Double rs = Double.valueOf(String.valueOf(objects.get(0)));
        int code = (int) NumberUtil.decimal(rs);
        return ScoreType.getCode(code);
    }

    @Transactional
    @Override
    public void uploadImg(String commentId, List<File> files) throws SerException {
        Comment comment = super.findById(commentId);
        List<Picture> pictures = new ArrayList<>(files.size());
        for (File file : files) {
            String path = FileUtil.getDbPath(file.getPath());
            Picture picture = new Picture();
            picture.setComment(comment);
            picture.setPath(path);
            pictures.add(picture);
        }
        pictureSer.save(pictures);
    }


    @Transactional
    @Override
    public void remove(String id) throws SerException {
        PictureDTO dto = new PictureDTO();
        dto.getConditions().add(Restrict.eq("comment.id", id));
        List<Picture> pictures = pictureSer.findByCis(dto);
        for (Picture picture : pictures) {
            File file = new File(FileUtil.getRealPath(picture.getPath()));
            if (file.exists()) {
                file.delete();
            }
        }
        pictureSer.remove(pictures);
        likesSer.executeSql("delete from " + getTableName(Likes.class) + " where comment_id='" + id + "'");
        super.remove(id);
    }

    @Override
    public CommentDetailsVO details(String commentId) throws SerException {
        User user = UserUtil.currentUser();
        Comment comment = super.findById(commentId);
        if (null != comment) {
            CommentDetailsVO vo = BeanCopy.copyProperties(comment, CommentDetailsVO.class);
            vo.setImages(getImages(commentId));
            vo.setAlreadyLikes(alreadyLike(user.getId()));
            vo.setHeadPath(user.getHeadPath());
            vo.setNickname(user.getNickname());
            return vo;
        }
        throw new SerException("该商品不存在或已被删除");
    }

    private Boolean alreadyLike(String userId) throws SerException {
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.getConditions().add(Restrict.eq("user.id", userId));
        List<Likes> likes = likesSer.findByCis(likesDTO);
        return likes.size() > 0;
    }

    private String[] getImages(String commentId) throws SerException {
        PictureDTO dto = new PictureDTO();
        dto.getConditions().add(Restrict.eq("comment.id", commentId));
        List<Picture> pictures = pictureSer.findByCis(dto);
        String[] images = new String[pictures.size()];
        for (int i = 0; i < pictures.size(); i++) {
            images[i] = pictures.get(i).getPath();
        }
        return images;
    }


}
