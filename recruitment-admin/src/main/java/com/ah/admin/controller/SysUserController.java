package com.ah.admin.controller;


import com.ah.admin.service.ISysUserService;
import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.api.domain.SysUser;
import com.ah.common.annotation.InnerAuth;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:54
 */
@RestController
@RequestMapping("/sys/use")
public class SysUserController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysUserService sysUserService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysUser 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public AjaxResult selectAll(Page<SysUser> page, SysUser sysUser) {
        return AjaxResult.success(sysUserService.page(page, new QueryWrapper<>(sysUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(sysUserService.getById(id));
    }

    @InnerAuth
    @GetMapping("/infoByName/{username}")
    public R<LoginUser> infoByName(@PathVariable("username") String username) {
        SysUser sysUser = sysUserService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("用户名或密码错误");
        }
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        return R.ok(sysUserVo);
    }

    @InnerAuth
    @GetMapping("/infoByPhone/{phone}")
    public R<LoginUser> infoByPhone(@PathVariable("phone") String phone) {
        SysUser sysUser = sysUserService.selectUserByPhone(phone);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("当前手机号未注册");
        }
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        return R.ok(sysUserVo);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysUser sysUser) {
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return AjaxResult.success(sysUserService.save(sysUser));
    }

    /**
     * 修改数据
     *
     * @param sysUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysUser sysUser) {
        return AjaxResult.success(sysUserService.updateById(sysUser));
    }

    @PutMapping("/resetPwd")
    public AjaxResult resetPassword(@RequestBody SysUser sysUser) {
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return AjaxResult.success(sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(sysUserService.removeByIds(idList));
    }
}

