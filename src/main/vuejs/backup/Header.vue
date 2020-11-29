<template>
  <div id="nav">
    <b-navbar>
      <div class="nav-1">
        <router-link to="/"
          ><b-img
            left
            width="50px"
            src="@/assets/logo.svg"
            fluid
            alt="logo"
          ></b-img
        ></router-link>
        <router-link to="/">Home</router-link>
        <router-link to="/about">About</router-link>
      </div>
      <a @click="logout"
        >Logout <img src="@/assets/logout-icon.svg" alt="logout icon"
      /></a>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "Header",
  methods: {
    logout() {},
  },
};
</script>

<style lang="scss" scoped>
#nav {
  padding: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: $super-dark-blue;
  box-sizing: border-box;
  width: 100%;
  padding: 15px 15%;

  a {
    font-weight: bold;
    color: $dark-gray;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

.nav-1 {
  display: flex;
  align-items: center;

  a {
    margin-left: 30px;
    margin-right: 20px;
  }

  b-img {
    margin-right: 20px;
  }
}
</style>
