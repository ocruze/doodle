<template>
  <div>
    <Header></Header>
    <h3>Create a new doodle</h3>
    <form>
      <input type="text" placeholder="Title" v-model="form.title" />
      <input type="text" placeholder="Place" v-model="form.place" />

      <b-modal
        id="modal-1"
        title="Add new date proposition"
        @ok="addProposition"
      >
        <div>
          <label for="example-datepicker">Choose a date</label>
          <b-form-datepicker
            id="example-datepicker"
            v-model="dateValue"
            class="mb-2"
          ></b-form-datepicker>
        </div>
        <div class="mt-2">Value: '{{ dateValue }}'</div>
        <b-form-timepicker v-model="startTime"></b-form-timepicker>
        <div class="mt-2">Value: '{{ startTime }}'</div>
        <b-form-timepicker v-model="endTime"></b-form-timepicker>
        <div class="mt-2">Value: '{{ endTime }}'</div>
      </b-modal>

      <b-button variant="primary" v-b-modal.modal-1> Add proposition </b-button>
      <b-button
        variant="primary"
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
      console.log(this.form);
      axios
        .post("/doodle/create", this.form)
        .then(() => {
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
    addProposition() {
      this.form.propositions.push({
        date: this.dateValue,
        start: this.startTime,
        finish: this.endTime,
      });
    },
  },
};
</script>
