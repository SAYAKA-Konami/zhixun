package com.macro.mall.tiny.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2024-03-17
 */
@Getter
@Setter
@Schema(name = "Group", description = "")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @Schema(description = "小组长的ID")
    private Long leaderId;

    @Schema(description = "归属的团队ID")
    private Long teamId;

    private Date createTime;

    private Date updateTime;
}
