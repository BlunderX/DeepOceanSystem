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
  background-color: darkgray;
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
  border-radius: 15px;
  box-shadow: #c6c6c6 0 0 5px 1px;
}
.img{
  width: 65%;
  height: 100%;
  background-image: url("@/assets/1753795171982.jpg");
  background-size: cover;
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
  box-shadow: white inset 0 0 15px 15px;
}
.form{
  width: 35%;
  height: 100%;
  background: #ffffff;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  border-radius: 15px;

}
</style>