<template>
  <div>
    <b-navbar id="b-navbar" toggleable="lg">
      <b-navbar-brand>
        <router-link to="/"
          ><b-img
            left
            width="50px"
            src="@/assets/logo.svg"
            fluid
            alt="logo"
          ></b-img
        ></router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/dashboard">Dashboard</router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/about">About</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="authenticated">
          <b-nav-item>
            <router-link to="/new_doodle">
              New Doodle
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <a @click.prevent="logout"
              >Logout <font-awesome-icon icon="sign-out-alt" />
            </a>
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item>
            <router-link to="/login"
              >Login <font-awesome-icon icon="sign-in-alt"
            /></router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/register"
              >Register <font-awesome-icon icon="user-plus"
            /></router-link>
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "Header",
  computed: {
    ...mapGetters({
      authenticated: "auth/authenticated",
      user: "auth/user",
    }),
  },

  methods: {
    ...mapActions({
      logoutAction: "auth/logout",
    }),

    logout() {
      this.logoutAction()
        .then(() => {
          if (this.$route.name != "Home") {
            this.$router.replace({
              name: "Home",
            });
          }
        })
        .then(() => {
          this.$toast.success("Logout successful");
        });
    },
  },
};
</script>

<style lang="scss">
#b-navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: $transparent;
  box-sizing: border-box;
  width: 100%;
  padding: 0px 12%;

  a {
    font-weight: bold;
    color: $dark-gray;
    text-decoration: none;
    font-size: 1.1rem;

    &.router-link-exact-active {
      color: $white;
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
