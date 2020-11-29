<template>
  <div class="container">
    <div class="login">
      <img src="@/assets/logo.svg" alt="logo" />
      <h4>Login to your account</h4>
      <input type="username" placeholder="Username" />
      <input type="password" placeholder="Password" />
      <button>Login</button>

      <router-link to="/recover">Forgot your password?</router-link>
      <router-link to="/register"
        >Don't have an account? Register here</router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {};
  },
  computed: {},
  methods: {},
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;

  min-height: 100vh;
}

.login {
  width: 400px;
}

h4 {
  margin: 0;
  line-height: 34px;
  font-size: 24px;
  text-align: center;
  color: #ffffff;
}

input {
  background: rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-sizing: border-box;
  border-radius: 4px;
  height: 60px;
  width: 100%;
  font-size: 20px;
  color: white;
  padding-left: 20px;
  margin-top: 20px;

  &::placeholder {
    color: rgba(255, 255, 255, 0.63);
  }
}

button {
  background: #56ccf2;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
  border: none;
  height: 60px;
  width: 100%;
  font-size: 20px;
  color: white;
  margin-top: 20px;
  margin-bottom: 30px;
}

a {
  line-height: 25px;
  font-size: 16px;
  text-align: center;
  color: rgba(255, 255, 255, 0.671);
  text-decoration: none;
  display: block;
  margin-bottom: 10px;
}
</style>
