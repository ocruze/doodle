<template>
  <div>
    <Header></Header>
    <h1>This page is used to create a new doodle</h1>
    <form>
        <input type="text" placeholder="Title" v-model="form.title" />
        <input type="text" placeholder="Place" v-model="form.place" />
        <div>
            <label for="example-datepicker">Choose a date</label>
            <b-form-datepicker id="example-datepicker" v-model="dateValue" class="mb-2" locale="fr"></b-form-datepicker>
            <p>Value: '{{ dateValue }}'</p>
        </div>
        <b-form-timepicker v-model="startTime" locale="en"></b-form-timepicker>
    <div class="mt-2">Value: '{{ startTime }}'</div>
        <b-form-timepicker v-model="endTime" locale="en"></b-form-timepicker>
    <div class="mt-2">Value: '{{ endTime }}'</div>
        <b-button
          type="submit"
          @submit.prevent="submit"
          @click.prevent="submit"
        >
          Save
        </b-button>
      </form>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import axios from "axios";

export default {
    components: { Header },
    name: "Doodle",
  data() {
    return {
      form: {
        title: "",
        place: "",
        propositions: [],
      },
      dateValue: "",
      startTime: "", 
      endTime: "", 
    };
  },
  computed: {},
  methods: {
    submit() {
        this.form.propositions.push({date: this.dateValue, start: this.startTime, finish: this.endTime})
        console.log(this.form);
        axios.post(
            '/doodle/create',
            this.form
        ).then(() => {
          this.$toast.success("Creation successful!");
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
};

</script>
