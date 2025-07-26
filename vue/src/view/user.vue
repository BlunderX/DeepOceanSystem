<script setup lang="javascript">

export default {
  data() {
    return {

    }
  },
  mounted() {

  }
}

</script>

<template>
  <div class="page">
    <div class="container">
      <div class="img">

      </div>
      <div class="form">
        <RouterView/>
      </div>
    </div>

  </div>
</template>

<style scoped>
.page{
  background: #5e5c5c;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container {
  width: 65vw;
  height: 60vh;
  background-color: white;
  display: flex;
}
.img{
  width: 65%;
  height: 100%;
  background: #ededa6;
}
.form{
  width: 35%;
  height: 100%;
  background: #ffffff;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
</style>