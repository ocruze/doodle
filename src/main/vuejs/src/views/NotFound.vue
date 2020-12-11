<template>
  <div>
    <Header></Header>
    <h2>Sorry the page you're looking for does not exist</h2>
    <router-link to="/"
      ><h3>Go back to home <font-awesome-icon icon="home" /></h3
    ></router-link>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
export default {
  name: "NotFound",
  components: { Header },
};
</script>

<style lang="scss" scoped>
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
