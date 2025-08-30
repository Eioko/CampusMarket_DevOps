<template>
  <div class="container">
    <div class="sidebar">
      <div class="logo-box">
        <Logo name="跳蚤市场" />
      </div>
      <div class="nav-links">
        <span @click="handleRouteSelect('/product')">商品</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/myProduct')">我的商品</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/mySave')">我的收藏</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/star')">我的关注</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/myView')">足迹</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/chatTable')">聊天</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/orders')">订单</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/message')">通知</span>
        <span v-if="loginStatus" @click="handleRouteSelect('/post-product')">发布商品</span>
        <span v-if="!loginStatus" @click="loginOperation">登录 | 注册</span>
        <div v-else @click="handleRouteSelect('/space?userId=' + userInfo.id)">
          <img class="avatar" :src="userInfo.userAvatar" />
        </div>
      </div>
    </div>

    <div class="main-content">
      <!-- 搜索栏 -->
      <div class="search-bar">
        <div class="word-search">
          <div class="item">
            <input type="text" placeholder="搜索商品" v-model="key">
            <i class="el-icon-search" @click="fetch"></i>
          </div>
        </div>
      </div>

      <!-- 登录信息 -->
      <!-- <div class="info" v-if="loginStatus">
        <img :src="userInfo.userAvatar" />
        <div class="user-info">
          <div class="title1">
            <span class="title">{{ userInfo.userName }}</span>
          </div>
          <span class="poin" v-for="(info, index) in productInfoList" :key="index">
            {{ info.name }}·{{ info.count }}
          </span>
          <div class="date">上一次登录时间： {{ userInfo.lastLoginTime }}</div>
          <div class="date">注册于： {{ userInfo.createTime }}</div>
        </div>
      </div> -->

      <!-- 路由内容 -->
      <div class="router-view-wrapper">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import Logo from "@/components/Logo";
import { getToken, setUserInfo, setSearchKey } from "@/utils/storage";
export default {
  name: "Home",
  components: {
    Logo,
  },
  data() {
    return {
      selfPath: { name: '个人中心', path: '/mySelf' },
      userRoutes: [],
      key: null,
      dialogOperaion: false,
      loginStatus: false, // 默认未登录
      userInfo: {},
      searchPath: '/search',
      productInfoList: []
    };
  },
  created() {
    this.loadLoginStatus();
    this.handleRouteSelect('/product')
  },
  methods: {
    queryProductInfo() {
      this.$axios.post(`/product/queryProductInfo`, {}).then(res => {
        const { data } = res;
        if (data.code === 200) {
          this.productInfoList = data.data;
        }
        this.loginStatus = data.code === 200;
      }).catch(error => {
        console.log("商品指标查询异常：", error);
      });
    },
    // 跳转登录页
    loginOperation() {
      this.$router.push('/login');
    },
    // 搜索
    fetch() {
      setSearchKey(this.key);
      this.handleRouteSelect(this.searchPath);
    },
    // 路由切换
    handleRouteSelect(path) {
      if (this.$router.currentRoute.fullPath !== path) {
        this.$router.push(path);
      }
    },
    //判断用户是否已经登录
    loadLoginStatus() {
      const token = getToken();
      // 没登录
      if (token === null) {
        this.loginStatus = false;
        return;
      }
      this.auth();
    },
    // token检验
    auth() {
      this.$axios.get(`/user/auth`).then(res => {
        const { data } = res;
        if (data.code === 200) {
          // 存储用户信息
          setUserInfo(data.data);
          this.userInfo = data.data;
          this.queryProductInfo();
        }
        this.loginStatus = data.code === 200;
      }).catch(error => {
        console.log("token检验异常：", error);
      });
    },
  }
};
</script>
<style scoped lang="scss">
.container {
  display: flex;
  height: 100vh;
  background-color: #EFF6FF; // 主体背景浅蓝
}

// 左侧导航栏
.sidebar {
  width: 200px;
  background-color: #1E3A8A; // 深蓝色
  color: #E0F2FE;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;

  .logo-box {
    margin-bottom: 30px;
  }

  .nav-links {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 16px;

    span {
      cursor: pointer;
      padding: 10px;
      font-size: 14px;
      color: #BFDBFE; // 字体浅蓝
      transition: color 0.3s, background-color 0.3s;
      border-radius: 8px;

      &:hover {
        color: #fff;
        background-color: #2563EB; // hover亮蓝色
      }
    }

    .avatar {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      border: 2px solid #fff;
      cursor: pointer;
      margin-top: 20px;

      &:hover {
        border-color: #93C5FD;
      }
    }

    .active {
      background-color: #3B82F6;
      color: #fff;
    }
  }
}

// 主内容区
.main-content {
  flex: 1;
  padding: 30px;
  overflow-y: auto;

  .search-bar {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }

  .info {
    display: flex;
    align-items: center;
    gap: 30px;
    background-color: #ffffff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);

    img {
      width: 90px;
      height: 90px;
      border-radius: 50%;
      border: 3px solid #BFDBFE;
    }

    .user-info {
      .title1 {
        font-size: 24px;
        color: #1E3A8A;
        display: flex;
        align-items: center;
        gap: 12px;

      }

      .poin {
        font-size: 12px;
        color: #fff;
        background-color: #3B82F6;
        padding: 4px 8px;
        border-radius: 20px;
      }

      .date {
        font-size: 13px;
        color: #64748B;
        margin-top: 8px;
      }
    }
  }

  .router-view-wrapper {
    margin-top: 30px;
  }
}

// 搜索栏
.word-search {
  .item {
    background-color: #DBEAFE;
    border-radius: 20px;
    display: flex;
    align-items: center;
    border: 1px solid #93C5FD;

    input {
      border: none;
      background-color: transparent;
      padding: 8px 10px;
      font-size: 14px;
      outline: none;
    }

    i {
      cursor: pointer;
      padding: 6px 10px;
      border-radius: 0 20px 20px 0;
      background-color: #93C5FD;
      color: #fff;
    }

    i:hover {
      background-color: #60A5FA;
    }
  }
}
</style>