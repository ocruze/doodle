<template>
  <div>
    <Header></Header>
    <b-container v-if="loading">
      <b-spinner label="Loading..."></b-spinner>
    </b-container>
    <b-container v-else>
      <div>
        <h2>{{ doodle.title }}</h2>
      </div>

      <div class="mb-5">
        <p>organized by {{ doodle.organizer.username }}</p>
      </div>

      <div class="mb-5">
        <div>
          <font-awesome-icon icon="map-marker-alt" />
          <p>{{ doodle.place }}</p>
        </div>
      </div>

      <div>
        <b-table
          responsive
          striped
          hover
          bordered
          sticky-header
          head-variant="dark"
          :fields="fields"
          :items="doodle.propositions"
        >
          <template #cell(date)="row">
            <p>{{ row.item.date }}</p>
          </template>

          <template #cell(start)="row">
            <p>{{ row.item.start }}</p>
          </template>

          <template #cell(finish)="row">
            <p>{{ row.item.finish }}</p>
          </template>

          <template #cell(going)="row">
            <p>{{ row.item.going.length }}</p>

            <b-button size="sm" @click="vote(row.item.id, 'yes')" class="mr-1">
              vote
            </b-button>
          </template>

          <template #cell(not_going)="row">
            <p>{{ row.item.notGoing.length }}</p>

            <b-button size="sm" @click="vote(row.item.id, 'no')" class="mr-1">
              vote
            </b-button>
          </template>
        </b-table>
      </div>
    </b-container>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import axios from "axios";

export default {
  name: "DoodlePage",
  components: {
    Header,
  },
  data() {
    return {
      idDoodle: this.$route.params.idDoodle,
      fields: ["date", "start", "finish", "going", "not_going"],
      doodle: {},
      loading: true,
    };
  },
  mounted() {
    this.getDoodle();
  },
  methods: {
    async getDoodle() {
      await axios
        .get("/doodle/" + this.idDoodle)
        .then((response) => {
          this.doodle = response.data;
          this.loading = false;
        })
        .catch((error) => {
          if (error.response) {
            this.$toast.error(error.response.data.message);
          } else {
            this.$toast.error(
              "Oops, something went wrong while connecting to server"
            );
          }
          this.$router.replace({ name: "Dashboard" });
        });
    },

    async vote(idProp, going) {
      await axios
        .patch("/prop/" + idProp + "/vote/" + going)
        .then(() => {
          this.$toast.success("success");
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

      this.getDoodle();
    },
  },
};
</script>

<style lang="scss" scoped>
</style>