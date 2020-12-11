<template>
  <div class="home">
    <Header />

    <b-container fluid="sm">
      <h1 class="mt-3">Doodle</h1>
      <h4>Plan your best friend's surprise birthday party 🎂</h4>
      <h4>or important business meetings easily</h4>
      <router-link to="/new-doodle">
        <b-button variant="primary"><h4>New Doodle</h4></b-button>
      </router-link>
      <b-img
        src="@/assets/Hero.svg"
        fluid
        width="1000px"
        alt="hero image"
      ></b-img>
    </b-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

export default {
  name: "Home",
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
</style>