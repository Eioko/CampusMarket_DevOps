<template>
  <div class="login-page">
    <div class="login-card">
      <h2 class="title">校园跳蚤交易平台</h2>
      <input v-model="act" class="input" placeholder="请输入账号" />
      <input v-model="pwd" class="input" type="password" placeholder="请输入密码" />
      <button class="login-btn" @click="login">立即登录</button>
      <p class="tip">
        还没有账号？
        <span class="link" @click="toDoRegister">注册新用户</span>
      </p>
    </div>
  </div>
</template>

<script>
const DELAY_TIME = 1300;
import request from "@/utils/request.js";
import { setToken } from "@/utils/storage.js";
import md5 from 'js-md5';
import Logo from '@/components/Logo.vue';
export default {
  name: "Login",
  components: { Logo },
  data() {
    return {
      act: '',
      pwd: '',
      colorLogo: 'rgb(38,38,38)',
    }
  },
  methods: {
    // 跳转注册页面
    toDoRegister() {
      this.$router.push('/register');
    },
    async login() {
      if (!this.act || !this.pwd) {
        this.$swal.fire({
          title: '填写校验',
          text: '账号或密码不能为空',
          icon: 'error',
          showConfirmButton: false,
          timer: DELAY_TIME,
        });
        return;
      }
      const hashedPwd = md5(md5(this.pwd));
      const paramDTO = { userAccount: this.act, userPwd: hashedPwd };
      try {
        const { data } = await request.post(`/user/login`, paramDTO);
        if (data.code !== 200) {
          this.$swal.fire({
            title: '登录失败',
            text: data.msg,
            icon: 'error',
            showConfirmButton: false,
            timer: DELAY_TIME,
          });
          return;
        }
        setToken(data.data.token);
        // 根据角色延迟跳转
        setTimeout(() => {
          const { role } = data.data;
          this.navigateToRole(role);
        }, DELAY_TIME);
      } catch (error) {
        console.error('登录请求错误:', error);
        this.$message.error('登录请求出错，请重试！');
      }
    },
    navigateToRole(role) {
      switch (role) {
        case 1:
          this.$router.push('/admin');
          break;
        case 2:
          console.log("用户角色：", role);
          this.$router.push('/user');
          break;
        default:
          console.warn('未知的角色类型:', role);
          break;
      }
    },
  }
};
</script>

<style scoped lang="scss">
* {
  box-sizing: border-box;
  user-select: none;
}

.login-page {
  width: 100%;
  height: 100vh;
  background: linear-gradient(to right, #74b9ff, #a29bfe);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 400px;
  background: #ffffff;
  border-radius: 16px;
  padding: 40px 30px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
  text-align: center;
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.title {
  font-size: 24px;
  color: #2d3436;
  font-weight: 700;
  margin-bottom: 10px;
}

.input {
  width: 100%;
  padding: 14px 16px;
  font-size: 16px;
  border: 1px solid #dfe6e9;
  border-radius: 8px;
  background-color: #f0f6ff;
  transition: border-color 0.3s ease;
}

.input:focus {
  outline: none;
  border-color: #4a90e2;
  background-color: #ecf4ff;
}

.login-btn {
  width: 100%;
  padding: 12px 0;
  background-color: #4a90e2;
  color: #fff;
  font-size: 16px;
  font-weight: 600;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-btn:hover {
  background-color: #3c7edc;
}

.tip {
  font-size: 14px;
  color: #636e72;
  margin-top: 10px;

  .link {
    color: #0984e3;
    margin-left: 6px;
    cursor: pointer;
    font-weight: 600;
  }

  .link:hover {
    text-decoration: underline;
  }
}
</style>
