<template>
  <div>
    <button @click='tiaozhuan'>tiaozhuan</button>
    <router-link to='/resume'>123</router-link>
  </div>
</template>

<script setup>


import { useRouter } from 'vue-router'
const router = useRouter();
function tiaozhuan() {
  router.push('/resume')
}

</script>

<style scoped></style>
