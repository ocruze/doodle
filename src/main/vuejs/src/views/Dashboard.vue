<template>
  <div class="dashboard">
    <Header />
    <div class="container">
      <b-list-group class="row col-10">
        <b-list-group-item v-for="doodle in doodles" :key="doodle.id">
          <router-link :to="`/doodle/${doodle.id}`">
            <h5>{{ doodle.title }}</h5>
          </router-link>
        </b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";
import axios from "axios";

export default {
  name: "Dashboard",
  components: {
    Header,
  },
  data() {
    return {
      doodles: [],
    };
  },
  computed: {},
  methods: {
    loadData: function () {
      axios
        .get("/doodle/")
        .then((res) => {
          this.doodles = res.data;
        })
        .catch((error) => {
          if (error.response) {
            this.$toast.error(error.response.data.message);
          } else {
            this.$toast.error(
              "Oops, something went wrong while connecting to server"
            );
          }
        });
    },
  },
  beforeMount() {
    this.loadData();
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  color: black;

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
  text-decoration: none;
  display: block;
  margin-bottom: 10px;
}

.list {
  justify-content: left;
}
</style>

