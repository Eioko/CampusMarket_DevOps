<template>
  <div class="container">
    <el-card shadow="hover" class="user-card">
      <!-- 头像上传 -->
      <div class="form-item">
        <label>头像</label>
        <el-upload class="avatar-uploader" action="http://localhost:31451/api/campus-product-sys/v1.0/file/upload"
          :show-file-list="false" :on-success="handleAvatarSuccess">
          <img v-if="userAvatar" :src="userAvatar" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>

      <!-- 昵称 -->
      <div class="form-item">
        <label>昵称</label>
        <el-input v-model="userInfo.userName" placeholder="请输入昵称" />
      </div>

      <!-- 邮箱 -->
      <div class="form-item">
        <label>邮箱</label>
        <el-input v-model="userInfo.userEmail" placeholder="请输入邮箱" />
      </div>

      <!-- 账号状态 -->
      <div class="info-item">
        <p class="label">
          账号状态
          <el-tooltip effect="dark" content="一经封号，不可登录，不可使用系统功能" placement="right">
            <i class="el-icon-info"></i>
          </el-tooltip>
        </p>
        <div :class="['status-tag', !userInfo.isLogin ? 'normal' : 'banned']">
          <i :class="!userInfo.isLogin ? 'el-icon-circle-check' : 'el-icon-warning-outline'"></i>
          {{ !userInfo.isLogin ? '正常' : '已被封禁' }}
        </div>
      </div>

      <!-- 留言状态 -->
      <div class="info-item">
        <p class="label">
          留言状态
          <el-tooltip effect="dark" content="禁言后，互动功能受限" placement="right">
            <i class="el-icon-info"></i>
          </el-tooltip>
        </p>
        <div :class="['status-tag', !userInfo.isWord ? 'normal' : 'banned']">
          <i :class="!userInfo.isWord ? 'el-icon-circle-check' : 'el-icon-warning-outline'"></i>
          {{ !userInfo.isWord ? '正常' : '已被封禁' }}
        </div>
      </div>

      <!-- 按钮 -->
      <div class="action-area">
        <el-button type="primary" size="medium" @click="postInfo" round>立即修改</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  name: "Self",
  data() {
    return {
      userInfo: {},
      userAvatar: '',
    }
  },
  created() {
    this.auth();
  },
  methods: {
    // 提交个人信息修改
    async postInfo() {
      const { userName, userEmail } = this.userInfo;
      const userUpdateDTO = {
        userAvatar: this.userAvatar,
        userName,
        userEmail
      }
      const { data } = await this.$axios.put('/user/update', userUpdateDTO);
      this.$notify({
        position: 'buttom-right',
        duration: 1000,
        title: '编辑个人信息',
        message: data.code === 200 ? '编辑成功' : '编辑失败',
        type: data.code === 200 ? 'success' : 'error'
      });
      // 返回首页
      if (data.code === 200) {
        this.auth();
      }
    },
    // 头像上传
    handleAvatarSuccess(res, file) {
      this.$notify({
        duration: 1500,
        title: '头像上传',
        message: res.code === 200 ? '上传成功' : '上传失败',
        type: res.code === 200 ? 'success' : 'error'
      });
      // 上传成功则更新用户头像
      if (res.code === 200) {
        this.userAvatar = res.data;
      }
    },
    // Token 检验 ,取得用户信息
    async auth() {
      const { data } = await this.$axios.get('/user/auth');
      if (data.code !== 200) { // Token校验异常
        this.$router.push('/');
      } else {
        this.userInfo = data.data;
        this.userAvatar = data.data.userAvatar;
      }
    },
  }
};
</script>
<style scoped lang="scss">
.container {
  display: flex;
  justify-content: center;
  padding: 30px;
  //   background-color: #f5f7fa;
}

.user-card {
  width: 100%;
  max-width: 520px;
  padding: 20px;
  border-radius: 12px;
  background-color: white;
}

.form-item {
  margin-bottom: 20px;

  label {
    font-size: 14px;
    font-weight: 500;
    color: #333;
    display: block;
    margin-bottom: 8px;
  }
}

.status-tag {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  font-size: 14px;
  font-weight: 500;
  padding: 6px 12px;
  border-radius: 20px;

  &.normal {
    color: #409eff;
    background-color: #eaf4ff;
  }

  &.banned {
    color: #e63f31;
    background-color: #ffeceb;
    text-decoration: underline dashed;
  }
}

.avatar-uploader {
  .avatar {
    width: 88px;
    height: 88px;
    border-radius: 50%;
    display: block;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #409EFF;
    width: 88px;
    height: 88px;
    border: 1px dashed #409EFF;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
}

// .status-item {
//   display: flex;
//   flex-direction: column;
//   gap: 8px;

//   .el-tag {
//     font-size: 13px;
//     padding: 5px 10px;
//   }
// }

.action-area {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
