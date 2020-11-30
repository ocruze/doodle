<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="scss">
body {
  height: 100vh;
  background: $blue-red-gradient;
  margin: 0;
}

h1 {
  @include heading-1;
}

h2 {
  @include heading-2;
}

h3 {
  @include heading-3;
}

h4 {
  @include heading-4;
}

p {
  @include large-text-bold($green);
}

#app {
  font-family: $default-font-family;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: $white;
}
</style>
