package com.miaosha.project.dao;

import com.miaosha.project.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    int insert(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    int insertSelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    PromoDO selectByPrimaryKey(Integer id);
    PromoDO selectByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    int updateByPrimaryKeySelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     *  Fri Jul 15 17:33:16 CST 2022
     */
    int updateByPrimaryKey(PromoDO row);
}