package com.miaosha.project.dao;

import com.miaosha.project.dataobject.UserDO;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    int insert(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    int insertSelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    UserDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    int updateByPrimaryKeySelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Jul 05 14:01:08 CST 2022
     */
    int updateByPrimaryKey(UserDO row);
}